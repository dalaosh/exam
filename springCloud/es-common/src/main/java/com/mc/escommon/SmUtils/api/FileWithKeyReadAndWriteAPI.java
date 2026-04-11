package com.mc.escommon.SmUtils.api;

import com.mc.escommon.SmUtils.jwtutils.cert.CertSNAllocator;
import com.mc.escommon.SmUtils.jwtutils.cert.CommonUtil;
import com.mc.escommon.SmUtils.jwtutils.cert.RandomSNAllocator;
import com.mc.escommon.SmUtils.jwtutils.cert.SM2CertUtil;
import com.mc.escommon.SmUtils.jwtutils.cert.SM2PublicKey;
import com.mc.escommon.SmUtils.jwtutils.cert.SM2X509CertMaker;
import com.mc.escommon.SmUtils.jwtutils.cert.exception.InvalidX500NameException;
import com.mc.escommon.SmUtils.smutils.BCECUtil;
import com.mc.escommon.SmUtils.smutils.FileUtil;
import com.mc.escommon.SmUtils.smutils.SM2Util;
import com.mc.escommon.path.SpringCloudPathResolver;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

/**
 * 使用文件对公私钥进行存储与读取。
 */
public class FileWithKeyReadAndWriteAPI {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String filePath = SpringCloudPathResolver.resolveSm2Dir();

    private static String Country = "CN";
    private static String Organization = "com.mc";
    private static String Organizational = "com.mc";
    private static String CommonName1 = "example.org";
    private static String CommonName2 = "MC Root CA";
    private static String EmailAddress = "626692428@qq.com";

    public static InputStream streamCer;
    public static InputStream streamPri;
    private static BCECPublicKey publicKey;
    private static BCECPrivateKey privateKey;

    public static boolean WriteKeyToFile(String url, String country, String organization, String organizational,
                                         String commonName1, String commonName2, String emailAddress) {
        Country = country;
        Organization = organization;
        Organizational = organizational;
        CommonName1 = commonName1;
        CommonName2 = commonName2;
        EmailAddress = emailAddress;
        return WriteKeyToFile(url);
    }

    public static boolean WriteKeyToFile(String url) {
        try {
            String normalizedUrl = normalizeUrl(url);
            KeyPair subKP = SM2Util.generateKeyPair();
            X500Name subDN = buildSubjectDN();
            SM2PublicKey sm2SubPub = new SM2PublicKey(subKP.getPublic().getAlgorithm(),
                    (BCECPublicKey) subKP.getPublic());
            byte[] csr = CommonUtil.createCSR(subDN, sm2SubPub, subKP.getPrivate(),
                    SM2X509CertMaker.SIGN_ALGO_SM3WITHSM2).getEncoded();

            String targetDir = filePath + normalizedUrl + "/";
            if (!cn.hutool.core.io.FileUtil.isDirectory(targetDir)) {
                cn.hutool.core.io.FileUtil.mkdir(targetDir);
            }

            savePriKey(targetDir + "jwt.sm2.pri", (BCECPrivateKey) subKP.getPrivate(),
                    (BCECPublicKey) subKP.getPublic());
            SM2X509CertMaker certMaker = buildCertMaker();
            X509Certificate cert = certMaker.makeSSLEndEntityCert(csr);
            FileUtil.writeFile(targetDir + "jwt.sm2.cer", cert.getEncoded());
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

    public static X500Name buildSubjectDN() {
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.C, Country);
        builder.addRDN(BCStyle.O, Organization);
        builder.addRDN(BCStyle.OU, Organizational);
        builder.addRDN(BCStyle.CN, CommonName1);
        builder.addRDN(BCStyle.EmailAddress, EmailAddress);
        return builder.build();
    }

    public static X500Name buildRootCADN() {
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.C, Country);
        builder.addRDN(BCStyle.O, Organization);
        builder.addRDN(BCStyle.OU, Organizational);
        builder.addRDN(BCStyle.CN, CommonName2);
        return builder.build();
    }

    public static SM2X509CertMaker buildCertMaker() throws InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, NoSuchProviderException, InvalidX500NameException {
        X500Name issuerName = buildRootCADN();
        KeyPair issKP = SM2Util.generateKeyPair();
        long certExpire = 2L * 365 * 24 * 60 * 60 * 1000;
        CertSNAllocator snAllocator = new RandomSNAllocator();
        return new SM2X509CertMaker(issKP, certExpire, issuerName, snAllocator);
    }

    public static void readKey(String url) throws CertificateException, NoSuchProviderException,
            NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        Security.addProvider(new BouncyCastleProvider());
        streamCer = null;
        streamPri = null;
        publicKey = null;
        privateKey = null;

        String normalizedUrl = normalizeUrl(url);
        File certFile = new File(filePath + normalizedUrl + "/jwt.sm2.cer");
        File priFile = new File(filePath + normalizedUrl + "/jwt.sm2.pri");

        if (!certFile.isFile()) {
            throw new FileNotFoundException("SM2 certificate file not found: " + certFile.getAbsolutePath());
        }
        if (!priFile.isFile()) {
            throw new FileNotFoundException("SM2 private key file not found: " + priFile.getAbsolutePath());
        }

        try (InputStream certStream = new FileInputStream(certFile);
             InputStream priStream = new FileInputStream(priFile)) {
            X509Certificate cert = SM2CertUtil.getX509Certificate(certStream);
            if (cert == null) {
                throw new CertificateException("Unable to parse SM2 certificate: " + certFile.getAbsolutePath());
            }

            byte[] priKeyData = priStream.readAllBytes();
            if (priKeyData.length == 0) {
                throw new IOException("SM2 private key file is empty: " + priFile.getAbsolutePath());
            }

            publicKey = SM2CertUtil.getBCECPublicKey(cert);
            privateKey = BCECUtil.convertSEC1ToBCECPrivateKey(priKeyData);
        }
    }

    public static BCECPublicKey getPublicKey() {
        return publicKey;
    }

    public static BCECPrivateKey getPrivateKey() {
        return privateKey;
    }

    private static String normalizeUrl(String url) {
        String safeUrl = Objects.requireNonNull(url, "key url must not be null").replace("\\", "/");
        String[] segments = safeUrl.split("/");
        StringBuilder builder = new StringBuilder();
        for (String segment : segments) {
            if (segment == null) {
                continue;
            }
            String trimmed = segment.trim();
            if (trimmed.isEmpty()) {
                continue;
            }
            if (builder.length() > 0) {
                builder.append("/");
            }
            builder.append(trimmed);
        }
        if (builder.length() == 0) {
            throw new IllegalArgumentException("key url must not be blank");
        }
        return builder.toString();
    }
}
