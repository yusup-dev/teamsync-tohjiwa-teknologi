package com.tohjiwa.teamsync.server.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class JavaSecurityPemUtils {
    public static RSAPrivateKey readPKCS8PrivateKey(String filePath) throws GeneralSecurityException, IOException {
        PathUtils pathUtils = new PathUtils();
        var pt = pathUtils.getPath(filePath);
        if(pt.isEmpty()) {
            throw new RuntimeException("Failed load file");
        }

        var file = new File(pt.get());

        String key = Files.readString(file.toPath(), Charset.defaultCharset());

        String privateKeyPEM = key
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "");

        byte[] encoded = Base64.decodeBase64(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }


    public static RSAPublicKey readX509PublicKey(String filePath) throws GeneralSecurityException, IOException {
        PathUtils pathUtils = new PathUtils();
        var pt = pathUtils.getPath(filePath);
        if(pt.isEmpty()) {
            throw new RuntimeException("Failed load file");
        }

        var file = new File(pt.get());

        String key = Files.readString(file.toPath(), Charset.defaultCharset());

        String publicKeyPEM = key
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PUBLIC KEY-----", "");

        byte[] encoded = Base64.decodeBase64(publicKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }
}
