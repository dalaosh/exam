package com.mc.escommon.user;

import cn.hutool.core.util.RandomUtil;
import com.mc.escommon.SmUtils.api.FileWithKeyReadAndWriteAPI;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author machao
 * * @datetime 2025/1/16 10:43
 * * inform
 */
public class Utils {
    public static String getAccount(){
        // 获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = sdf.format(new Date());

        // 生成随机数，范围 000 到 9999
        String random= RandomUtil.randomNumbers(4);

        // 生成账号
        String account = timeStamp + random;
        return account;
    }
}
