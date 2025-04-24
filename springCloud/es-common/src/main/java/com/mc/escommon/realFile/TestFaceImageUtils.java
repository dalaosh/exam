package com.mc.escommon.realFile;

import cn.hutool.core.codec.Base64;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@Configuration
public class TestFaceImageUtils implements WebMvcConfigurer {

    // 修改点1：调整基础URL路径
    private static final String LOCAL_STORAGE_PATH;
    private static final String HTTP_BASE_URL = "http://localhost:9998/files/testFace/";

    static {
        // 修改点2：调整本地存储路径
        String projectRoot = System.getProperty("user.dir");
        LOCAL_STORAGE_PATH = Paths.get(projectRoot, "files", "testFace")  // 修改目录名称
                .toAbsolutePath()
                .toString()
                .replace("\\", File.separator);

        // 自动创建存储目录
        File storageDir = new File(LOCAL_STORAGE_PATH);
        if (!storageDir.exists() && !storageDir.mkdirs()) {
            throw new RuntimeException("无法创建存储目录: " + LOCAL_STORAGE_PATH);
        }
    }

    private static final String[] ALLOWED_EXTENSIONS = {"png", "jpg", "jpeg", "gif"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 修改点3：调整资源映射路径
        registry.addResourceHandler("/files/testFace/**")  // 修改路径匹配规则
                .addResourceLocations("file:" + LOCAL_STORAGE_PATH + File.separator)
                .setCachePeriod(3600)
                .resourceChain(true);
    }

    /**
     * 保存Base64图片并返回访问URL
     */
    public String saveBase64Image(String base64Data) throws IOException {
        String pureBase64 = cleanBase64Prefix(base64Data);
        byte[] imageBytes = Base64.decode(pureBase64);

        String extension = detectImageFormat(imageBytes);
        validateImageFormat(extension);

        String savePath = generateStoragePath(extension);
        writeToFile(savePath, imageBytes);

        return buildFullUrl(savePath);
    }

    // 以下方法保持不变
    private static String cleanBase64Prefix(String base64Data) {
        if (base64Data == null) return "";
        int commaIndex = base64Data.indexOf(',');
        return commaIndex > 0 ? base64Data.substring(commaIndex + 1) : base64Data;
    }

    private static String detectImageFormat(byte[] imageData) {
        if (imageData.length < 4) return "unknown";

        int header = ((imageData[0] & 0xFF) << 24) |
                ((imageData[1] & 0xFF) << 16) |
                ((imageData[2] & 0xFF) << 8) |
                (imageData[3] & 0xFF);

        switch (header) {
            case 0x89504E47: return "png";
            case 0xFFD8FFE0: return "jpg";
            case 0x47494638: return "gif";
            default: return "unknown";
        }
    }

    private static void validateImageFormat(String extension) {
        for (String allowed : ALLOWED_EXTENSIONS) {
            if (allowed.equalsIgnoreCase(extension)) {
                return;
            }
        }
        throw new IllegalArgumentException("不支持的图片格式: " + extension);
    }

    private String generateStoragePath(String extension) {
        String filename = UUID.randomUUID() + "." + extension;
        return Paths.get(LOCAL_STORAGE_PATH, filename).toString();
    }

    private static void writeToFile(String path, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(data);
        }
    }

    private String buildFullUrl(String absolutePath) {
        String filename = Paths.get(absolutePath).getFileName().toString();
        return HTTP_BASE_URL + filename;
    }
}