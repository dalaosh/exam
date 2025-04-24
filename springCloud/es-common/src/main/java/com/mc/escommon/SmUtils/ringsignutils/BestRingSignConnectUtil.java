package com.mc.escommon.SmUtils.ringsignutils;


import com.mc.escommon.SmUtils.smutils.BCECUtil;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BestRingSignConnectUtil {
    /**
     * 签名
     * @param data 数据
     * @param publicKeys 公钥集合
     * @param privateKey 私钥
     * @param pi 位置
     * @return 签名结果
     */
    public static String generate(String data, List<BCECPublicKey> publicKeys, BCECPrivateKey privateKey,int pi){
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

        return BestRingSignerConnect.generateSignature(m,L,ecPrivateKeyParameters,pi,G);
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
        boolean r=BestRingSignerConnect.verifySignature(m,L,key,publicKeys.get(0).getParameters().getG());
        System.out.println("++++++++"+data);
        System.out.println("++++++++"+ Arrays.toString(m));
        System.out.println("++++++++"+r);
        System.out.println("++++++++"+key);
        return r;
    }

    /**
     * 验证可链接性
     *
     * @param data1      第一个签名信息
     * @param data2      第二个签名信息
     * @param key1       第一个签名值
     * @param key2       第二个签名值
     * @param publicKeys 环公钥
     * @return
     */
    public static Object connect(String data1, String data2, String key1, String key2, List<BCECPublicKey> publicKeys){
        if(verifySign(data1,publicKeys,key1) &&verifySign(data2,publicKeys,key2)){
            List<BigInteger> S1=split(key1);
            List<BigInteger> S2=split(key2);
            System.out.println("qpi1:"+S1.get(S1.size() - 1));
            System.out.println("qpi2:"+S2.get(S2.size() - 1));
            return Objects.equals(S1.get(S1.size() - 1), S2.get(S2.size() - 1));
        }
        else if(verifySign(data1,publicKeys,key1) || verifySign(data2,publicKeys,key2)){
            if(verifySign(data1,publicKeys,key1)){
                return "第二对验签失败";
            }
            else if(verifySign(data2,publicKeys,key2)){
                return "第一对验签失败";
            }
            else {
                return "两对验签都失败";
            }
        }
        else {
            return "两对验签都失败";
        }
    }
    public static List<BigInteger> split(String str) {
        String delimiter = ",";
        // 拆分字符串到 List<BigInteger>
        List<BigInteger> list = new ArrayList<>();
        String[] elements = str.split(delimiter);
        for (String element : elements) {
            list.add(new BigInteger(element));
        }
        return list;
    }
    // 将十六进制字符串转换为 byte 数组
    private static byte[] hexStringToByteArray(String hexString) {
//        int length = hexString.length();
//        if (length % 2 != 0) {
//            hexString+="0";
//            length+=1;
//        }
//        byte[] byteArray = new byte[length / 2];
//        for (int i = 0; i < length; i += 2) {
//            byteArray[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
//                    + Character.digit(hexString.charAt(i + 1), 16));
//        }
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

