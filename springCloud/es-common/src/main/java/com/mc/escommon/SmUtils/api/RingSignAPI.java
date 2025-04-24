package com.mc.escommon.SmUtils.api;

import com.mc.escommon.SmUtils.ringsignutils.BestRingSigner;
import com.mc.escommon.SmUtils.smutils.BCECUtil;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * *@Author machao
 * *@Date 2024/12/23 18:22
 * *@注释
 * 该算法使用sm2算法进行环签名
 * 环签名进行签名
 * 对环签名进行验签
 * 验证可链接性
 */
public class RingSignAPI {
    /**
     * 签名
     * @param data 数据
     * @param publicKeys 公钥集合
     * @param privateKey 私钥
     * @param pi 位置
     * @return 签名结果
     */
    public static String generate(String data, List<BCECPublicKey> publicKeys, BCECPrivateKey privateKey, int pi){
        ECPoint G=publicKeys.get(0).getParameters().getG();

        ECPrivateKeyParameters ecPrivateKeyParameters= BCECUtil.convertPrivateKeyToParameters(privateKey);
        byte[] m=hexStringToByteArray(data);
        //计算公钥
        ECMultiplier var1 = createBasePointMultiplier();
        // 将私钥与点 G 相乘得到公钥
        ECPoint ecPoint=var1.multiply(publicKeys.get(0).getParameters().getG(),ecPrivateKeyParameters.getD()).normalize();//公钥

        List<ECPoint> L = new ArrayList<>();
        for (int i=0;i<publicKeys.size();i++){
            ECPublicKeyParameters publicKeyParams = BCECUtil.convertPublicKeyToParameters(publicKeys.get(i));
            L.add(publicKeyParams.getQ());
        }
        return BestRingSigner.generateSignature(m,L,ecPrivateKeyParameters,pi,G);
    }

    /**
     * 验签
     * @param data 数据
     * @param publicKeys 公钥集合
     * @param key 签名值
     * @return 是否验签成功
     */
    public static boolean verifySign(String data, List<BCECPublicKey> publicKeys,String key){
        List<ECPoint> L = new ArrayList<>();
        for (BCECPublicKey publicKey:publicKeys){
            ECPublicKeyParameters publicKeyParams = BCECUtil.convertPublicKeyToParameters(publicKey);
            L.add(publicKeyParams.getQ());
        }
        byte[] m=hexStringToByteArray(data);
        boolean r=BestRingSigner.verifySignature(m,L,key,publicKeys.get(0).getParameters().getG());
        return r;
    }


    // 将十六进制字符串转换为 byte 数组
    private static byte[] hexStringToByteArray(String hexString) {
        byte[] byteArray = new byte[0];
        try {
            byteArray = hexString.getBytes("UTF-8");
            System.out.println(Arrays.toString(byteArray));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    // 将 byte 数组转换为十六进制字符串
    private static String toHexString(byte[] byteArray) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : byteArray) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }

    protected static ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}
