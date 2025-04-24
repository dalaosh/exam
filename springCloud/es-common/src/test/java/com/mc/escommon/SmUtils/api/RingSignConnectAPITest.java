package com.mc.escommon.SmUtils.api;

import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @version 1.0
 * @Author machao
 * @Date 2024/12/23 18:02
 * @注释
 */
class RingSignConnectAPITest {
    private static String data1="hello";
    private static String data2="world";
    private static List<BCECPublicKey> publicKeys;
    private static BCECPrivateKey privateKey;
    private static BCECPublicKey publicKey;
    private static int pi;

    public static void keys(int i){
        while (publicKeys.size()<i){
            try {
                Sm2API.getkey();
                BCECPublicKey bcPubKey = Sm2API.getBcPubKey();
                BCECPrivateKey bcecPriKey = Sm2API.getBcPriKey();
                publicKeys.add(bcPubKey);
                if(publicKeys.size()==i/2){
                    privateKey=bcecPriKey;
                    publicKey=bcPubKey;
                    pi=i/2;
                    System.out.println("++++++++++++++++");
                    System.out.println(publicKeys.size());
                    System.out.println(pi);
                    System.out.println(i);
                    System.out.println("++++++++++++++++");
                }
            } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
                throw new RuntimeException(e);
            }

        }
    }


    public static void main(String[] args) {
        //生成公钥对
        publicKeys=new ArrayList<>();
        keys(11);
        String prk=Sm2API.pri2str(privateKey);
        String puk=Sm2API.pub2str(publicKey);
        for (int i = 0; i < publicKeys.size(); i++) {
            String puk2=Sm2API.pub2str(publicKeys.get(i));
            if(Objects.equals(puk, puk2)){
                System.out.println("++++++++++++++++");
                System.out.println(pi);
                System.out.println(i);
                System.out.println("++++++++++++++++");
            }
        }
        String ringSign1 =RingSignConnectAPI.generate(data1,publicKeys,privateKey,pi);
        boolean result1 = RingSignConnectAPI.verifySign(data1,publicKeys,ringSign1);

        String ringSign2 =RingSignConnectAPI.generate(data2,publicKeys,privateKey,pi);
        boolean result2 = RingSignConnectAPI.verifySign(data2,publicKeys,ringSign2);

        Object connect=RingSignConnectAPI.connect(data1,data2,ringSign1,ringSign2,publicKeys);

        System.out.println("**********************环签名**********************");
        System.out.println("第一个环签名："+ringSign1);
        System.out.println("第一个验签结果："+result1);
        System.out.println("第二个环签名："+ringSign2);
        System.out.println("第二个验签结果："+result2);
        System.out.println("可链接性："+connect);
        System.out.println("*****************************************************");
    }
}