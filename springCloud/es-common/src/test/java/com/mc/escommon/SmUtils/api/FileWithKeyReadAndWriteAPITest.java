package com.mc.escommon.SmUtils.api;

import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

/**
 * @version 1.0
 * @Author machao
 * @Date 2024/12/23 15:23
 * @注释
 */
class FileWithKeyReadAndWriteAPITest {
    public static void main(String[] args) {
        String url="二班/冯婷婷";
        //将密钥存入文件
        boolean b = FileWithKeyReadAndWriteAPI.WriteKeyToFile(url);

        System.out.println("**********************文件是否创建**********************");
        System.out.println("文件是否创建："+b);
        System.out.println("*****************************************************");

        //从文件读密钥
        try {
            FileWithKeyReadAndWriteAPI.readKey(url);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new RuntimeException(e);
        }

        BCECPrivateKey privateKey=FileWithKeyReadAndWriteAPI.getPrivateKey();
        BCECPublicKey publicKey=FileWithKeyReadAndWriteAPI.getPublicKey();
        System.out.println("**********************公私钥读取**********************");
        System.out.println("私钥："+privateKey);
        System.out.println("公钥："+publicKey);
        System.out.println("*****************************************************");
    }

}