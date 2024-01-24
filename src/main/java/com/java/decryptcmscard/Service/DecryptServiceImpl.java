package com.java.decryptcmscard.Service;

import com.java.decryptcmscard.Service.Utility.OTPServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class DecryptServiceImpl {
    private static final String ENCALGO = "AES/ECB/PKCS5PADDING";


    private static final String secretKeyStr = "ASDFGHJASHJKLQWEASDFGHJASHJKLQWE"; // 16-byte secret key
    // TODO: CONVERTING THE KEY INTO BYTES ARRAY
    private static final byte[] secretKey = secretKeyStr.getBytes();
    //

    @Autowired
    OTPServiceimpl otpServiceimpl;

    public String decryptEncryptedOTP(String encryptedOtp) throws Exception {

        SecretKey decryptedSecretKey = otpServiceimpl.decodeSecretKey("5Nh/+VoSNgXo8E91afXP3w==");
        return otpServiceimpl.decrypt(decryptedSecretKey, encryptedOtp);
    }

    public String decrypt(String data) throws Exception {
        Map<String, String> decryptionResponse;
        decryptionResponse = new HashMap<>();
        byte[] decryptedData = Base64.getDecoder().decode(data);
        Cipher cipher = null;
        byte[] decrypted = null;
        try {
            cipher = Cipher.getInstance(ENCALGO);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            decrypted = cipher.doFinal(decryptedData);
        } catch (Exception e) {
            throw new Exception();
        }
        return new String(decrypted);
    }
}
