/**
 * 
 */
package com.intelligentmaths;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.intelligentmaths.exception.CryptoException;
import com.intelligentmaths.util.FileHandle;

/**
 * @author Salil Chattopadhyay
 *
 */
public class GenerateDecryptFile {

	// The key compatible with AES algorithm is 16 characters.
	// If the key is more than 16 characters - implement proper padding etc.

	private static final String key = "salil is program";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		File afterSelectedTextEncryptedFile = new File(
				"/home/salcsal/test/docEnc.txt");

		File decryptedFile = new File("/home/salcsal/test/docDecrypt.txt");

		String textBeforeDecryption = "password=";

		FileHandle fileHandler = new FileHandle();

		try {
			System.out
					.println("Going to decrypt the encrypted portion of the file.");

			fileHandler.doDecrypt(key, afterSelectedTextEncryptedFile,
					textBeforeDecryption, decryptedFile);

			System.out
					.println("Generated file after decryption. Please check.");

		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {

			try {
				throw new CryptoException(
						"Error while permorning encrypting/decrypting", e);
			} catch (CryptoException e1) {

				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
