package intelligentmaths.co.uk.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Salil Chattopadhyay
 *
 */

public class CrypticOperations {

	private static final String ALGORITHM = "AES";

	private static final String TRANSFORMATION = "AES";

	private static final String UNICODE_FORMAT = "UTF8";

	public String encrypt(String key, String str) throws UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		String encRetStr = null;

		// encode the string into bytes using UTF-8

		byte[] utf8 = str.getBytes(UNICODE_FORMAT);

		Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);

		Cipher ecipher = Cipher.getInstance(TRANSFORMATION);

		ecipher.init(Cipher.ENCRYPT_MODE, secretKey);

		// Encrypt

		byte[] enc = ecipher.doFinal(utf8);

		// encode bytes to base64 to get a string

		// encRetStr = new sun.misc.BASE64Encoder().encode(enc);
		encRetStr = java.util.Base64.getEncoder().encodeToString(enc);

		return encRetStr;
	}

	public String decrypt(String key, String str) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {

		String decRetStr = null;

		// decode base64 to get bytes

		// byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
		byte[] dec = java.util.Base64.getDecoder().decode(str);

		Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);

		Cipher dcipher = Cipher.getInstance(TRANSFORMATION);

		dcipher.init(Cipher.DECRYPT_MODE, secretKey);

		// Decrypt

		byte[] utf8 = dcipher.doFinal(dec);

		// decode using UTF-8

		decRetStr = new String(utf8, UNICODE_FORMAT);

		return decRetStr;
	}

}
