package cn.gnaixeuy.uaaservice.utils;

import com.nimbusds.jose.util.Base64URL;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/>
 * redbook-back
 *
 * @author GnaixEuy
 * @version 1.0
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
public class PKCEUtils {

    public static String codeVerifierGenerator() {
        return Base64URL.encode(UUID.randomUUID().toString()).toString();
    }

    /**
     * @return
     * @author Rommel
     * @date 2023/7/30-21:47
     * @version 1.0
     * @description 生成 code_challenge
     */
    public static String codeChallengeGenerator(String codeVerifier) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] digestCodeVerifier = messageDigest.digest(codeVerifier.getBytes(StandardCharsets.US_ASCII));
        return Base64URL.encode(digestCodeVerifier).toString();
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {

        //生成code_verifier
        String codeVerifier = codeVerifierGenerator();
        //生成code_challenge
        String codeChallenge = codeChallengeGenerator(codeVerifier);

        System.out.println("code_verifier:" + codeVerifier);
        System.out.println("code_challenge:" + codeChallenge);

    }

}