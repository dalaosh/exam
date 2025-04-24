package com.mc.escommon.SmUtils.api;



import com.mc.escommon.SmUtils.jwtutils.cert.*;
import com.mc.escommon.SmUtils.jwtutils.cert.exception.InvalidX500NameException;
import com.mc.escommon.SmUtils.smutils.BCECUtil;
import com.mc.escommon.SmUtils.smutils.FileUtil;
import com.mc.escommon.SmUtils.smutils.SM2Util;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

/**
 * @version 1.0
 * @Author machao
 * @Date 2024/12/23 13:54
 * @注释
 * 该接口实现使用文件对公钥和私钥进行存储
 * 对公钥和私钥进行存储
 * 从文档中读取公钥和私钥
 */
public class FileWithKeyReadAndWriteAPI {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    private static final String filePath = System.getProperty("user.dir") + "/files/sm2File/";

    private static String Country="CN";
    private static String Organization="com.mc";
    private static String Organizational="com.mc";
    private static String CommonName1="example.org";
    private static String CommonName2="MC Root CA";
    private static String EmailAddress="626692428@qq.com";
    public static boolean WriteKeyToFile(String url, String country, String organization, String organizational, String commonName1,String commonName2, String emailAddress){
        Country=country;
        Organization=organization;
        Organizational=organizational;
        CommonName1=commonName1;
        CommonName2=commonName2;
        EmailAddress=emailAddress;
        return WriteKeyToFile(url);
    }

    public static boolean WriteKeyToFile(String url) {
        try {
            KeyPair subKP = SM2Util.generateKeyPair();
            X500Name subDN = buildSubjectDN();
            SM2PublicKey sm2SubPub = new SM2PublicKey(subKP.getPublic().getAlgorithm(),
                    (BCECPublicKey) subKP.getPublic());
            byte[] csr = CommonUtil.createCSR(subDN, sm2SubPub, subKP.getPrivate(),
                    SM2X509CertMaker.SIGN_ALGO_SM3WITHSM2).getEncoded();
            //如果没有文件夹则生成
            if (!cn.hutool.core.io.FileUtil.isDirectory(filePath+url+"/")) {
                cn.hutool.core.io.FileUtil.mkdir(filePath+url+"/");
            }
            savePriKey(filePath+url+"/jwt.sm2.pri", (BCECPrivateKey) subKP.getPrivate(),
                    (BCECPublicKey) subKP.getPublic());
            SM2X509CertMaker certMaker = buildCertMaker();
            X509Certificate cert = certMaker.makeSSLEndEntityCert(csr);
            FileUtil.writeFile(filePath+url+"/jwt.sm2.cer", cert.getEncoded());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail();
        }
        return false;
    }

    public static void savePriKey(String filePath, BCECPrivateKey priKey, BCECPublicKey pubKey) throws IOException {
        ECPrivateKeyParameters priKeyParam = BCECUtil.convertPrivateKeyToParameters(priKey);
        ECPublicKeyParameters pubKeyParam = BCECUtil.convertPublicKeyToParameters(pubKey);
        byte[] derPriKey = BCECUtil.convertECPrivateKeyToSEC1(priKeyParam, pubKeyParam);
        FileUtil.writeFile(filePath, derPriKey);
    }

    // 构建一个用于表示证书主题的 X500Name 对象
    public static X500Name buildSubjectDN() {
        // 创建一个 X500NameBuilder 实例，用于构建 X500Name
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        // 添加国家（Country）字段，"CN" 是国家代码（中国）
        builder.addRDN(BCStyle.C, Country);
        // 添加组织（Organization）字段，"com.mc" 是组织名
        builder.addRDN(BCStyle.O, Organization);
        // 添加组织单位（Organizational Unit）字段，"com.mc" 是组织单位名
        builder.addRDN(BCStyle.OU, Organizational);
        // 添加常见名称（Common Name）字段，"example.org" 是该证书的常见名称
        builder.addRDN(BCStyle.CN, CommonName1);
        // 添加电子邮件地址字段，"abc@example.org" 是电子邮件地址
        builder.addRDN(BCStyle.EmailAddress, EmailAddress);
        // 返回构建好的 X500Name 对象
        return builder.build();
    }

    // 构建一个用于表示根 CA 证书的 X500Name 对象
    public static X500Name buildRootCADN() {
        // 创建一个 X500NameBuilder 实例，用于构建 X500Name
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        // 添加国家（Country）字段，"CN" 是国家代码（中国）
        builder.addRDN(BCStyle.C, Country);
        // 添加组织（Organization）字段，"com.mc" 是组织名
        builder.addRDN(BCStyle.O, Organization);
        // 添加组织单位（Organizational Unit）字段，"com.mc" 是组织单位名
        builder.addRDN(BCStyle.OU, Organizational);
        // 添加常见名称（Common Name）字段，"MC Root CA" 是根证书颁发机构的常见名称
        builder.addRDN(BCStyle.CN, CommonName2);
        // 返回构建好的 X500Name 对象
        return builder.build();
    }

    public static SM2X509CertMaker buildCertMaker() throws InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, NoSuchProviderException, InvalidX500NameException {
        X500Name issuerName = buildRootCADN();
        KeyPair issKP = SM2Util.generateKeyPair();
        long certExpire = 2L * 365 * 24 * 60 * 60 * 1000; // 2年
        CertSNAllocator snAllocator = new RandomSNAllocator(); // 实际应用中可能需要使用数据库来保证证书序列号的唯一性。
        return new SM2X509CertMaker(issKP, certExpire, issuerName, snAllocator);
    }

    /////////////////////////////////////////////////////////////////////////////


    public static InputStream streamCer;
    public static InputStream streamPri;
    private static BCECPublicKey publicKey;
    private static BCECPrivateKey privateKey;
    public static void readKey(String url) throws CertificateException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        Security.addProvider(new BouncyCastleProvider());
        X509Certificate cert;
        try {
            File filecer = new File(filePath+url+"/jwt.sm2.cer");
            streamCer = new FileInputStream(filecer);
            // 执行你的操作，使用 inputStream
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File filepri = new File(filePath+url+"/jwt.sm2.pri");
            streamPri = new FileInputStream(filepri);
            // 执行你的操作，使用 inputStream
        } catch (IOException e) {
            e.printStackTrace();
        }

        int streamPriLen = Objects.requireNonNull(streamPri).available();
        cert = SM2CertUtil.getX509Certificate(streamCer);
        while (cert==null){
            try {
                File filecer = new File(filePath+url+"/jwt.sm2.cer");
                streamCer = new FileInputStream(filecer);
                // 执行你的操作，使用 inputStream
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                File filepri = new File(filePath+url+"/jwt.sm2.pri");
                streamPri = new FileInputStream(filepri);
                // 执行你的操作，使用 inputStream
            } catch (IOException e) {
                e.printStackTrace();
            }
            streamPriLen = Objects.requireNonNull(streamPri).available();
            cert = SM2CertUtil.getX509Certificate(streamCer);
        }
        byte[] priKeyData = new byte[streamPriLen];
        streamPri.read(priKeyData);
        // 从证书中获取公钥，从私钥文件中获取私钥
        publicKey = SM2CertUtil.getBCECPublicKey(cert);
        privateKey = BCECUtil.convertSEC1ToBCECPrivateKey(priKeyData);
    }

    public static BCECPublicKey getPublicKey() {
        return publicKey;
    }

    public static BCECPrivateKey getPrivateKey() {
        return privateKey;
    }

}
