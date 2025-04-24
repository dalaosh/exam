package com.mc.escommon.SmUtils.api;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

/**
 * @version 1.0
 * @Author machao
 * @Date 2024/12/23 12:38
 * @注释
 */
class Sm2APITest {
    public static void main(String[] args){
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


///////////////////////////////////////////////////////////////////////////
        String priKey=Sm2API.pri2str(bcPriKey);
        String pubKey=Sm2API.pub2str(bcPubKey);
        BCECPrivateKey privateKey;
        BCECPublicKey publicKey;
        try {
            privateKey=Sm2API.str2pri(priKey);
            publicKey=Sm2API.str2pub(pubKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        System.out.println("**********************数据类型转化**********************");
        System.out.println("字符串私钥："+priKey);
        System.out.println("字符串公钥："+pubKey);
        System.out.println("字符串私钥："+privateKey);
        System.out.println("字符串公钥："+publicKey);
        System.out.println("******************************************************");

///////////////////////////////////////////////////////////////////////////
        String encry;
        try {
            encry = Sm2API.encryption(pubKey, "你好");
        } catch (InvalidCipherTextException | NoSuchProviderException | InvalidKeySpecException |
                 NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String encry2;
        try {
            encry2 = Sm2API.encryption(pubKey, "你好");
        } catch (InvalidCipherTextException | NoSuchProviderException | InvalidKeySpecException |
                 NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String decry;
        try {
            decry=Sm2API.decrypt(priKey,encry);
        } catch (InvalidCipherTextException | NoSuchProviderException | InvalidKeySpecException |
                 NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        System.out.println("**********************加密和解密**********************");
        System.out.println("数据加密："+encry);
        System.out.println("数据加密："+encry2);
        System.out.println("数据解密："+decry);
        System.out.println("*****************************************************");

///////////////////////////////////////////////////////////////////////////
        String sign;
        try {
            sign=Sm2API.signaure(priKey,"你好");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException | CryptoException e) {
            throw new RuntimeException(e);
        }
        boolean vis;
        try {
            vis= Sm2API.visa(pubKey, sign, "你好");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        System.out.println("**********************签名和验签**********************");
        System.out.println("数据签名："+sign);
        System.out.println("数据验签："+vis);
        System.out.println("*****************************************************");
    }
}