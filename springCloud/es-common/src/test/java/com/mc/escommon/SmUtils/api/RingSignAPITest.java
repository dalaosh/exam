package com.mc.escommon.SmUtils.api;

import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author machao
 * @Date 2024/12/23 18:24
 * @注释
 */
class RingSignAPITest {
    private static String data="hello";
    private static List<BCECPublicKey> publicKeys;
    private static BCECPrivateKey privateKey;
    private static int pi;

    public static void keys(int i){
        while (publicKeys.size()<i){
            try {
                Sm2API.getkey();
                BCECPublicKey bcPubKey = Sm2API.getBcPubKey();
                BCECPrivateKey bcPriKey = Sm2API.getBcPriKey();
                publicKeys.add(bcPubKey);
                if(publicKeys.size()==i/2){
                    privateKey=bcPriKey;
                    pi=i/2;
                }
            } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        //生成公钥对
        publicKeys=new ArrayList<>();
        keys(10);

        String ringSign =RingSignAPI.generate(data,publicKeys,privateKey,pi);
        boolean result = RingSignAPI.verifySign(data,publicKeys,ringSign);

        System.out.println("**********************环签名**********************");
        System.out.println("环签名："+ringSign);
        System.out.println("验签结果："+result);
        System.out.println("*****************************************************");
    }

}