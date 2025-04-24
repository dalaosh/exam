package com.mc.escommon.SmUtils.ringsignutils;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.math.ec.custom.gm.SM2P256V1Curve;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BestRingSignerConnect {


    public static String generateSignature(byte[] m, List<ECPoint> L, ECPrivateKeyParameters d, int pi, ECPoint G){
        ECMultiplier var1 = createBasePointMultiplier();
        //****************************计算签名标签***************************
        ECPoint r=newEncodeECPublicKeyParametersList(L);//R
        ECPoint qpi=var1.multiply(r, d.getD()).normalize();//Qpi=dpi*r
        System.out.println(d.getParameters().getN());
        //****************************产生随机数***************************
        //构造变量c
        int length=L.size();
        List<BigInteger> C = new ArrayList<>();
        List<BigInteger> S = new ArrayList<>();
        //生成随机数k
        BigInteger k;
        k=redom(d);
        BigInteger kpi=k;
        //*****************************计算c[1](pi=pi)********************************
        // 编码列表L
        ECPoint encodedL = newEncodeECPublicKeyParametersList(L);
        // 计算SM3哈希值
        ECPoint g = var1.multiply(G, kpi).normalize();//G*kpi
        ECPoint kr=var1.multiply(r,kpi).normalize();
        byte[] hash = calculateSM3Hash(encodedL,qpi, m, g,kr);
        BigInteger h=new BigInteger(hash);
        // 打印哈希结果
        String hashHex = Hex.toHexString(hash);
        System.out.println("SM3 哈希结果: " + hashHex);
        for(int i=0;i<pi;i++){
            C.add(BigInteger.ZERO);
        }
        C.add(pi,h);
        for(int i=0;i<pi;i++){
            S.add(BigInteger.ZERO);
        }
        //C.set(0, "2".getBytes());
//**********************************计算循坏****************************************
        for(int i=pi;i<length;i++){
            BigInteger h2;
            S.add(i, BigInteger.ZERO);
            while (true)
            {
                k = redom(d);
                S.set(i, k);
                //v
                ECPoint var6_1 = var1.multiply(G, S.get(i)).normalize();//G*S
                ECPoint var7_1 = var1.multiply(L.get(i), (S.get(i).add(C.get(i)))).normalize();//p*(S+C)
                ECPoint var8_1 = var6_1.add(var7_1);

                //w
                ECPoint var6_2 = var1.multiply(r,S.get(i)).normalize();//R*S
                ECPoint var7_2 = var1.multiply(qpi, (S.get(i).add(C.get(i)))).normalize();//q*(S+C)
                ECPoint var8_2 = var6_2.add(var7_2);
                byte[] h1 = calculateSM3Hash(encodedL,qpi, m, var8_1,var8_2);
                h2 = new BigInteger(h1);
                int h3 = h2.compareTo(BigInteger.ZERO);
                if(h3>0){
                    break;
                }
            }
//            System.out.println("C"+i+":"+C.get(i)+"  "+"S"+i+":"+S.get(i));
            C.add(i+1,h2);
        }

        C.set(0,C.get(length));
        for(int i=0;i<pi-1;i++) {
            BigInteger h2;
            while (true) {
                k = redom(d);
                S.set(i, k);
                //v
                ECPoint var6_1 = var1.multiply(G, S.get(i)).normalize();//G*S
                ECPoint var7_1 = var1.multiply(L.get(i), (S.get(i).add(C.get(i)))).normalize();//p*(S+C)
                ECPoint var8_1 = var6_1.add(var7_1);

                //w
                ECPoint var6_2 = var1.multiply(r,S.get(i)).normalize();//R*S
                ECPoint var7_2 = var1.multiply(qpi, (S.get(i).add(C.get(i)))).normalize();//p*(S+C)
                ECPoint var8_2 = var6_2.add(var7_2);
                byte[] h1 = calculateSM3Hash(encodedL,qpi, m, var8_1,var8_2);
                h2 = new BigInteger(h1);
                int h3 = h2.compareTo(BigInteger.ZERO);
                if(h3>0){
                    break;
                }
            }
            C.set(i+1,h2);
        }
        //**********************************计算s[pi-1](pi=pi)**************************
        BigInteger var5;
        do {
            BigInteger var2 = d.getParameters().getN();
            BigInteger var3 = BigIntegers.modOddInverse(var2, d.getD().add(BigInteger.ONE));
            BigInteger var4 = kpi.subtract(C.get(pi-1).multiply(d.getD())).mod(var2);
            var5 = var3.multiply(var4).mod(var2);

        }while (var5.equals(BigInteger.ZERO));
        S.set(pi-1,var5);
        S.add(C.get(0));
//        System.out.println("**********************c的式子best  begin****************");
//        for(int i=0;i<=length;i++){
//            System.out.println("C"+i+":"+C.get(i)+"  "+"S"+i+":"+S.get(i));
//        }
//        System.out.println("**********************end****************");
        // 将 ECPoint 转换为字节数组
        byte[] encoded = qpi.getEncoded(false);
//        System.out.println("pqi:"+ Arrays.toString(encoded));
        // 将字节数组转换为 BigInteger
        BigInteger bqpi = new BigInteger(1, encoded);
        S.add(bqpi);
        String key=merge(S);
        try {
            if(verifySignature(m,L,key,G)){
                System.out.println("检测签名是否合理:true");
                return key;
            }
            else {
                return "检测签名错误";
            }
        }catch(Throwable e){
            System.out.println("签名不合理，将重新生成签名");
            return generateSignature(m,L,d,pi,G);
        }
        // 获取 ECCurve 对象（即 qpi 使用的曲线）
//        ECCurve usedCurve = qpi.getCurve();

        // 打印曲线信息
//        System.out.println("Curve used by qpi: " + usedCurve.getClass().getName());
    }


    public static boolean verifySignature(byte[] m, List<ECPoint> L, String key, ECPoint G){
        List<BigInteger> S=split(key);
        int length=L.size();
        List<BigInteger> C = new ArrayList<>();
        C.add(S.get(length));
        ECMultiplier var1 = createBasePointMultiplier();
        //****************************计算签名标签***************************
        ECPoint r=newEncodeECPublicKeyParametersList(L);
        // 使用 SM2P256V1Curve 曲线
        ECCurve curve = new SM2P256V1Curve();

        // 示例：已转换为 BigInteger 的值
        BigInteger bqpi = S.get(length+1); // 这里填入之前生成的 bqpi 值

        // Step 1: 将 BigInteger 转换回字节数组
        byte[] encodedRestored = bqpi.toByteArray();
        System.out.println("qpi:"+Arrays.toString(encodedRestored));
        // Step 3: 使用 SM2 曲线解码字节数组为 ECPoint
        ECPoint qpi = curve.decodePoint(encodedRestored);

        //****************************计算签名标签***************************
        ECPoint encodedL = newEncodeECPublicKeyParametersList(L);
        for (int i=0;i<length;i++){
            //v
            ECPoint var6_1 = var1.multiply(G, S.get(i)).normalize();//G*S
            ECPoint var7_1 = var1.multiply(L.get(i), (S.get(i).add(C.get(i)))).normalize();//p*(S+C)
            ECPoint var8_1 = var6_1.add(var7_1);

            //w
            ECPoint var6_2 = var1.multiply(r,S.get(i)).normalize();//R*S
            ECPoint var7_2 = var1.multiply(qpi, (S.get(i).add(C.get(i)))).normalize();//p*(S+C)
            ECPoint var8_2 = var6_2.add(var7_2);
            byte[] h1 = calculateSM3Hash(encodedL,qpi, m, var8_1,var8_2);
            BigInteger h2=new BigInteger(h1);
            C.add(i+1,h2);

        }
//        System.out.println("**********************c的式子best  begin****************");
//        for(int i=0;i<=length;i++){
//            System.out.println("C"+i+":"+C.get(i)+"  "+"S"+i+":"+S.get(i));
//        }
//        System.out.println("**********************end****************");
        return Objects.equals(C.get(0), C.get(length));
    }

    public static String merge(List<BigInteger> M) {
        // 将 List M 合并到字符串 L
        StringBuilder sb = new StringBuilder();
        String delimiter = ",";
        for (BigInteger num : M) {
            sb.append(num.toString()).append(delimiter);
        }
        // 去除最后一个分隔符
        if (sb.length() > 0) {
            sb.setLength(sb.length() - delimiter.length());
        }
        String L = sb.toString();
        System.out.println("L:"+L);
        return L;
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

    //生成随机数
    public static BigInteger redom(ECPrivateKeyParameters privateKeyParameters){
        // 创建 SecureRandom 对象
        SecureRandom secureRandom = new SecureRandom();
        // 生成一个随机 BigInteger，确保大于0
        BigInteger randomBigInteger;
        do {
            randomBigInteger = new BigInteger(privateKeyParameters.getParameters().getN().bitLength() - 1, secureRandom);
        } while (randomBigInteger.compareTo(BigInteger.ZERO) <= 0);
        return randomBigInteger;
    }

    //对公钥编码
    private static byte[] encodeECPublicKeyParametersList(List<ECPoint> L) {
        ECPoint sum = null;
        for (ECPoint publicKey : L) {
            if (sum == null) {
                sum = publicKey;
            } else {
                sum = sum.add(publicKey);
            }
        }
        if (sum != null) {
            return sum.getEncoded(false);
        }
        return new byte[0];
    }
    private static ECPoint newEncodeECPublicKeyParametersList(List<ECPoint> points){
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("The point list must not be null or empty.");
        }

        // 初始化为第一个点
        ECPoint result = points.get(0);

        // 将列表中剩余的所有点依次相加
        for (int i = 1; i < points.size(); i++) {
            result = result.add(points.get(i));
        }

        // 归一化结果（可选，视需要而定）
        return result.normalize();
    }
    private static byte[] calculateSM3Hash(ECPoint encodedL, ECPoint qpi, byte[] m, ECPoint g, ECPoint kr) {
        SM3Digest digest = new SM3Digest();

        // 将所有数据转换为字节数组并更新到SM3中
        byte[] encodedLBytes = encodedL.getEncoded(false);
        digest.update(encodedLBytes, 0, encodedLBytes.length);

        byte[] qpiBytes = qpi.getEncoded(false);
        digest.update(qpiBytes, 0, qpiBytes.length);

        digest.update(m, 0, m.length);

        byte[] gBytes = g.getEncoded(false);
        digest.update(gBytes, 0, gBytes.length);

        byte[] krBytes = kr.getEncoded(false);
        digest.update(krBytes, 0, krBytes.length);

        // 计算哈希值
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);

        return hash;
    }

    protected static ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}

