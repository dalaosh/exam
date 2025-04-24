package com.mc.estrustSeal.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.toolkit.ImageInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mc.escommon.entity.dto.FaceUserInfo;
import com.mc.escommon.entity.dto.ProcessInfo;
import com.mc.escommon.entity.statistic.StudentFace;
import com.mc.estrustSeal.mapper.StudentFaceMapper;
import com.mc.estrustSeal.service.StudentFaceService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import com.mc.estrustSeal.factory.FaceEngineFactory;

/**
 * 学生人脸服务实现类，实现了 StudentFaceService 接口，提供了人脸认证信息的增删改查、人脸检测、特征提取、比对等功能。
 * 该类使用了 ArcFace SDK 进行人脸处理，通过线程池和对象池提高性能，并使用 StudentFaceMapper 进行数据库操作。
 */
@Service
public class StudentFaceServiceImpl implements StudentFaceService {
    public final static Logger logger = LoggerFactory.getLogger(StudentFaceServiceImpl.class);

    // 从配置文件中读取 ArcFace SDK 库路径
    @Value("${config.arcface-sdk.sdk-lib-path}")
    public String sdkLibPath;
    // 从配置文件中读取 ArcFace SDK 应用 ID
    @Value("${config.arcface-sdk.app-id}")
    public String appId;
    // 从配置文件中读取 ArcFace SDK 密钥
    @Value("${config.arcface-sdk.sdk-key}")
    public String sdkKey;
    // 从配置文件中读取线程池大小
    @Value("${config.arcface-sdk.thread-pool-size}")
    public Integer threadPoolSize;
    // 相似度通过率阈值，默认为 80
    private Integer passRate = 80;
    // 线程池服务，用于多线程处理
    private ExecutorService executorService;
    // FaceEngine 对象池，用于管理 FaceEngine 对象的获取和释放
    private GenericObjectPool<FaceEngine> faceEngineObjectPool;
    // 自动注入 StudentFaceMapper，用于数据库操作
    @Autowired
    private StudentFaceMapper studentFaceMapper;

    /**
     * 新增人脸认证信息
     *
     * @param studentFace 包含要新增的人脸认证信息的对象
     */
    @Override
    public void add(StudentFace studentFace) {
        // 可以在这里添加一些额外的业务逻辑，比如设置默认值等
        studentFaceMapper.insert(studentFace);
    }

    /**
     * 根据 ID 删除人脸认证信息
     *
     * @param id 要删除的人脸认证信息的 ID
     */
    @Override
    public void deleteById(Integer id) {
        studentFaceMapper.deleteById(id);
    }

    /**
     * 批量删除人脸认证信息
     *
     * @param ids 要删除的人脸认证信息的 ID 列表
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        Integer[] idArray = ids.toArray(new Integer[0]);
        studentFaceMapper.deleteByIds(idArray);
    }

    /**
     * 修改人脸认证信息
     *
     * @param studentFace 包含要修改的人脸认证信息的对象
     */
    @Override
    public void updateById(StudentFace studentFace) {
        studentFaceMapper.updateById(studentFace);
    }

    /**
     * 根据 ID 查询人脸认证信息
     *
     * @param id 要查询的人脸认证信息的 ID
     * @return 包含指定 ID 的人脸认证信息的对象，如果未找到则返回 null
     */
    @Override
    public StudentFace selectById(Integer id) {
        return studentFaceMapper.selectById(id);
    }

    /**
     * 查询所有符合条件的人脸认证信息
     *
     * @param studentFace 包含查询条件的人脸认证信息对象
     * @return 符合条件的人脸认证信息列表，如果没有符合条件的记录则返回空列表
     */
    @Override
    public List<StudentFace> selectAll(StudentFace studentFace) {
        return studentFaceMapper.selectAll(studentFace);
    }

    /**
     * 分页查询人脸认证信息
     *
     * @param pageNum 当前页码
     * @param pageSize 每页显示的记录数
     * @param studentFace 包含查询条件的人脸认证信息对象
     * @return 包含分页信息和查询结果的 PageInfo 对象
     */
    @Override
    public PageInfo<StudentFace> selectPage(int pageNum, int pageSize, StudentFace studentFace) {
        // 开启分页查询
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 5;
        }
        PageHelper.startPage(pageNum, pageSize);

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<StudentFace> list = studentFaceMapper.selectAll(studentFace);
        return PageInfo.of(list);
    }

    /**
     * 初始化方法，在 Bean 初始化完成后执行
     * 初始化线程池、FaceEngine 对象池和相关配置
     */
    @PostConstruct
    public void init() {
        executorService = Executors.newFixedThreadPool(threadPoolSize);
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(threadPoolSize);
        poolConfig.setMaxTotal(threadPoolSize);
        poolConfig.setMinIdle(threadPoolSize);
        poolConfig.setLifo(false);

        // 引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);

        // 功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);

        faceEngineObjectPool = new GenericObjectPool(new FaceEngineFactory(sdkLibPath, appId, sdkKey, engineConfiguration), poolConfig);
    }

    /**
     * 将浮点数乘以 100 并转换为整数
     *
     * @param value 要转换的浮点数
     * @return 转换后的整数
     */
    private int plusHundred(Float value) {
        BigDecimal target = new BigDecimal(value);
        BigDecimal hundred = new BigDecimal(100f);
        return target.multiply(hundred).intValue();
    }

    /**
     * 检测图像中的人脸
     *
     * @param imageInfo 包含图像信息的对象
     * @return 检测到的人脸信息列表，如果发生异常则返回 null
     */
    @Override
    public List<FaceInfo> detectFaces(ImageInfo imageInfo) {
        FaceEngine faceEngine = null;
        try {
            // 获取引擎对象
            faceEngine = faceEngineObjectPool.borrowObject();

            // 人脸检测得到人脸列表
            List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();

            // 人脸检测
            faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
            return faceInfoList;
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (faceEngine != null) {
                // 释放引擎对象
                faceEngineObjectPool.returnObject(faceEngine);
            }
        }
        return null;
    }

    /**
     * 对图像中的人脸进行处理，提取性别和年龄信息
     *
     * @param imageInfo 包含图像信息的对象
     * @return 处理后的人脸信息列表，如果发生异常则返回 null
     */
    @Override
    public List<ProcessInfo> process(ImageInfo imageInfo) {
        FaceEngine faceEngine = null;
        try {
            // 获取引擎对象
            faceEngine = faceEngineObjectPool.borrowObject();
            // 人脸检测得到人脸列表
            List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
            // 人脸检测
            faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
            int processResult = faceEngine.process(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList, FunctionConfiguration.builder().supportAge(true).supportGender(true).build());
            List<ProcessInfo> processInfoList = Lists.newLinkedList();

            List<GenderInfo> genderInfoList = new ArrayList<GenderInfo>();
            // 性别提取
            int genderCode = faceEngine.getGender(genderInfoList);
            // 年龄提取
            List<AgeInfo> ageInfoList = new ArrayList<AgeInfo>();
            int ageCode = faceEngine.getAge(ageInfoList);
            for (int i = 0; i < genderInfoList.size(); i++) {
                ProcessInfo processInfo = new ProcessInfo();
                processInfo.setGender(genderInfoList.get(i).getGender());
                processInfo.setAge(ageInfoList.get(i).getAge());
                processInfoList.add(processInfo);
            }
            return processInfoList;

        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (faceEngine != null) {
                // 释放引擎对象
                faceEngineObjectPool.returnObject(faceEngine);
            }
        }

        return null;
    }

    /**
     * 提取图像中人脸的特征
     *
     * @param imageInfo 包含图像信息的对象
     * @return 提取到的人脸特征的字节数组，如果未检测到人脸或发生异常则返回 null
     * @throws InterruptedException 如果在获取对象池中的对象时线程被中断
     */
    @Override
    public byte[] extractFaceFeature(ImageInfo imageInfo) throws InterruptedException {
        FaceEngine faceEngine = null;
        try {
            // 获取引擎对象
            faceEngine = faceEngineObjectPool.borrowObject();

            // 人脸检测得到人脸列表
            List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();

            // 人脸检测
            int i = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);

            if (CollectionUtil.isNotEmpty(faceInfoList)) {
                FaceFeature faceFeature = new FaceFeature();
                // 提取人脸特征
                faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);

                return faceFeature.getFeatureData();
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (faceEngine != null) {
                // 释放引擎对象
                faceEngineObjectPool.returnObject(faceEngine);
            }
        }

        return null;
    }

    /**
     * 进行人脸比对
     *
     * @param faceFeature 要比对的人脸特征的字节数组
     * @return 与输入人脸特征比对后匹配的人脸用户信息列表
     * @throws InterruptedException 如果在多线程处理过程中线程被中断
     * @throws ExecutionException 如果在多线程处理过程中发生执行异常
     */
    @Override
    public List<FaceUserInfo> compareFaceFeature(byte[] faceFeature) throws InterruptedException, ExecutionException {
        List<FaceUserInfo> resultFaceInfoList = Lists.newLinkedList();// 识别到的人脸列表

        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature);
        List<StudentFace> faceInfoList = studentFaceMapper.selectAll(new StudentFace()); // 从数据库中取出人脸库
        List<List<StudentFace>> faceUserInfoPartList = Lists.partition(faceInfoList, 1000);// 分成 1000 一组，多线程处理
        CompletionService<List<FaceUserInfo>> completionService = new ExecutorCompletionService(executorService);
        for (List<StudentFace> part : faceUserInfoPartList) {
            completionService.submit(new CompareFaceTask(part, targetFaceFeature));
        }
        for (int i = 0; i < faceUserInfoPartList.size(); i++) {
            List<FaceUserInfo> faceUserInfoList = completionService.take().get();
            if (CollectionUtil.isNotEmpty(faceInfoList)) {
                resultFaceInfoList.addAll(faceUserInfoList);
            }
        }
        resultFaceInfoList.sort((h1, h2) -> h2.getSimilarValue().compareTo(h1.getSimilarValue()));// 从大到小排序

        return resultFaceInfoList;
    }

    /**
     * 根据用户 ID 查询人脸认证信息
     *
     * @param userId 用户 ID
     * @return 包含指定用户 ID 的人脸认证信息的对象，如果未找到则返回 null
     */
    @Override
    public StudentFace selectByUserId(Integer userId) {
        return studentFaceMapper.selectByUserId(userId);
    }

    /**
     * 批量更新人脸认证信息
     *
     * @param ids 要更新的人脸认证信息的 ID 列表
     * @param submit 更新的提交信息
     */
    @Override
    public void updateBatch(List<Integer> ids, String submit) {
        for (int id : ids) {
            StudentFace studentFace = new StudentFace();
            studentFace.setId(id);
            studentFace.setIsSubmit(submit);
            studentFaceMapper.updateById(studentFace);
        }
    }

    /**
     * 根据人脸 ID 查询人脸认证信息
     *
     * @param faceId 人脸 ID
     * @return 包含指定人脸 ID 的人脸认证信息的对象，如果未找到则返回 null
     */
    @Override
    public StudentFace selectByFaceId(String faceId) {
        return studentFaceMapper.selectByFaceId(faceId);
    }

    /**
     * 内部类，用于多线程进行人脸比对任务
     */
    private class CompareFaceTask implements Callable<List<FaceUserInfo>> {

        private List<StudentFace> faceUserInfoList;
        private FaceFeature targetFaceFeature;

        public CompareFaceTask(List<StudentFace> faceUserInfoList, FaceFeature targetFaceFeature) {
            this.faceUserInfoList = faceUserInfoList;
            this.targetFaceFeature = targetFaceFeature;
        }

        @Override
        public List<FaceUserInfo> call() throws Exception {
            FaceEngine faceEngine = null;
            List<FaceUserInfo> resultFaceInfoList = Lists.newLinkedList();// 识别到的人脸列表
            try {
                faceEngine = faceEngineObjectPool.borrowObject();
                for (StudentFace faceUserInfo : faceUserInfoList) {
                    FaceFeature sourceFaceFeature = new FaceFeature();
                    sourceFaceFeature.setFeatureData(faceUserInfo.getFaceFeature());
                    FaceSimilar faceSimilar = new FaceSimilar();
                    faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
                    Integer similarValue = plusHundred(faceSimilar.getScore());// 获取相似值
                    if (similarValue > passRate) {// 相似值大于配置预期，加入到识别到人脸的列表

                        FaceUserInfo info = new FaceUserInfo();
                        info.setName(faceUserInfo.getName());
                        info.setFaceId(faceUserInfo.getFaceId());
                        info.setSimilarValue(similarValue);
                        resultFaceInfoList.add(info);
                    }
                }
            } catch (Exception e) {
                logger.error("", e);
            } finally {
                if (faceEngine != null) {
                    faceEngineObjectPool.returnObject(faceEngine);
                }
            }

            return resultFaceInfoList;
        }
    }
}