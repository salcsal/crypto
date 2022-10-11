package intelligentmaths.co.uk;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import intelligentmaths.co.uk.exception.CryptoException;
import intelligentmaths.co.uk.util.FileHandle;

/**
 * @author Salil Chattopadhyay
 *
 */

public class GenerateFileWithSelectedTextEncryption {

	// The key compatible with AES algorithm is 16 characters.
	// If the key is more than 16 characters - implement proper padding etc.

	private static final String key = "salil is program";

	/**
	 * @param args
	 * 
	 */

	public static void main(String[] args) {

		File normalFile = new File("/home/salcsal/test/doc.txt");

		File afterSelectedTextEncryptedFile = new File("/home/salcsal/test/docEnc.txt");

		String textBeforeEncryption = "password=";

		FileHandle fileHandler = new FileHandle();

		try {
			System.out.println("Going to Encrypt sensitive information in the file.");

			fileHandler.doEncrypt(key, normalFile, textBeforeEncryption, afterSelectedTextEncryptedFile);

			System.out.println("Generated File after encrypting sensitive information. Please check.");

		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {

			try {
				throw new CryptoException("Error while permorning encrypting/decrypting", e);
			} catch (CryptoException e1) {

				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
