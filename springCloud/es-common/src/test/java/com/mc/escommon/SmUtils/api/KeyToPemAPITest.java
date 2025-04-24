package com.mc.escommon.SmUtils.api;

import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * @version 1.0
 * @Author machao
 * @Date 2024/12/23 16:08
 * @注释
 */
class KeyToPemAPITest {
    public static void main(String[] args) {
        //读取密钥
        String url="admin/202501161358138280/exam";
        KeyToPemAPI keyToPemAPI1;
        try {
            keyToPemAPI1=new KeyToPemAPI(url);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        List<String> pem1;
        try {
            pem1=keyToPemAPI1.pem();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("**********************pem公私钥**********************");
        System.out.println("私钥：\n"+pem1.get(1));
        System.out.println("公钥：\n"+pem1.get(0));
        System.out.println("*****************************************************");


        //生成的
        //获取公私钥对
        KeyPair key;
        try {
            key = Sm2API.getkey();
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        //读取公钥和私钥
        BCECPrivateKey bcPriKey = Sm2API.getBcPriKey();
        BCECPublicKey bcPubKey = Sm2API.getBcPubKey();
        String point = Sm2API.getPoint();
        System.out.println("**********************公私钥创建**********************");
        System.out.println("私钥："+bcPriKey);
        System.out.println("公钥："+bcPubKey);
        System.out.println("点："+point);
        System.out.println("*****************************************************");

        KeyToPemAPI keyToPemAPI2;
        try {
            keyToPemAPI2=new KeyToPemAPI(Sm2API.pri2str(bcPriKey),Sm2API.pub2str(bcPubKey));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException |
                 NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        List<String> pem2;
        try {
            pem2=keyToPemAPI2.pem();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("**********************pem公私钥**********************");
        System.out.println("私钥：\n"+pem2.get(1));
        System.out.println("公钥：\n"+pem2.get(0));
        System.out.println(keyToPemAPI2.pubToPem(Sm2API.pub2str(bcPubKey)));
        System.out.println("*****************************************************");
    }

}