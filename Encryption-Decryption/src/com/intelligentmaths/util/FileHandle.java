/**
 * 
 */
package com.intelligentmaths.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * @author Salil Chattopadhyay
 *
 */
public class FileHandle {

	public List<String> readTextFileByLines(File inputFileName)
			throws IOException {

		FileInputStream inputStream = new FileInputStream(inputFileName);

		BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream));

		String strLine;

		List<String> lines = new ArrayList<String>();

		while ((strLine = br.readLine()) != null) {

			lines.add(strLine);

		}

		br.close();

		inputStream.close();

		return lines;

	}

	public void writeToTextFile(File outputFileName, String str)
			throws IOException {

		FileOutputStream outputStream = new FileOutputStream(outputFileName,
				true);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				outputStream));

		bw.write(str);

		bw.newLine();

		bw.flush();

		bw.close();

		outputStream.close();

	}

	public void doEncrypt(String key, File normalInputFile,
			String textBeforeEncryption, File encryptedFile)
			throws IOException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		List<String> xList = readTextFileByLines(normalInputFile);

		int numLines = xList.size();

		for (int k = 0; k < numLines; k++) {

			// check if the line contains that selected text after which
			// encryption should happen

			if (xList.get(k).contains(textBeforeEncryption)) {
				// determine the position of the selected text within the string
				// everything including the selected text will remain normal
				// only text after the selected text will be encrypted

				int positionSelectedText = xList.get(k).indexOf(
						textBeforeEncryption);

				int positionBeforeEncryption = textBeforeEncryption.length();

				int positionForEncryption = xList.get(k).length();

				String normalPartOfString = xList.get(k).substring(
						positionSelectedText, positionBeforeEncryption);

				String encryptPartOfString = xList.get(k).substring(
						positionBeforeEncryption, positionForEncryption);

				CrypticOperations crypto = new CrypticOperations();

				String cryptString = crypto.encrypt(key, encryptPartOfString);

				String combinedCrypto = normalPartOfString + cryptString;

				writeToTextFile(encryptedFile, combinedCrypto);

			}

			else {
				writeToTextFile(encryptedFile, xList.get(k));
			}

		}

	}

	public void doDecrypt(String key, File encryptedFile,
			String textBeforeDecryption, File normalOutputFile)
			throws IOException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		List<String> xList = readTextFileByLines(encryptedFile);

		int numLines = xList.size();

		for (int k = 0; k < numLines; k++) {

			// check if the line contains that selected text after which
			// encryption should happen

			if (xList.get(k).contains(textBeforeDecryption)) {
				// determine the position of the selected text within the string
				// everything including the selected text will remain normal
				// only text after the selected text will be decrypted

				int positionSelectedText = xList.get(k).indexOf(
						textBeforeDecryption);

				int positionBeforeEncryption = textBeforeDecryption.length();

				int positionForEncryption = xList.get(k).length();

				String normalPartOfString = xList.get(k).substring(
						positionSelectedText, positionBeforeEncryption);

				String encryptPartOfString = xList.get(k).substring(
						positionBeforeEncryption, positionForEncryption);

				CrypticOperations crypto = new CrypticOperations();

				String cryptString = crypto.decrypt(key, encryptPartOfString);

				String combinedCrypto = normalPartOfString + cryptString;

				writeToTextFile(normalOutputFile, combinedCrypto);

			}

			else {
				writeToTextFile(normalOutputFile, xList.get(k));
			}

		}

	}

}
