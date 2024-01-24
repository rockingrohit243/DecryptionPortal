package com.java.decryptcmscard.Service.Utility;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Random;
@Component
public class OTPServiceimpl {
	
	private static final String ENCALGO = "AES/ECB/PKCS5PADDING";
	//private static final String ENCALGO = "AES/GCM/NoPadding";

	public SecretKey decodeSecretKey(String base64EncodedKey) {
		try {
			byte[] keyBytes = Base64.getDecoder().decode(base64EncodedKey);
			return new SecretKeySpec(keyBytes, "AES");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String decrypt(SecretKey otpSecretKey, String encryptOtp) throws Exception {
		Cipher cipher = Cipher.getInstance(ENCALGO);
		cipher.init(Cipher.DECRYPT_MODE, otpSecretKey);
		byte[] encryptedBytes = Base64.getDecoder().decode(encryptOtp);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		return new String(decryptedBytes);
	}
}
