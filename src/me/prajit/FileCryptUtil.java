package me.prajit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class FileCryptUtil {
	
	private static final String ALGORITHM = "AES";

	public static void encryptFile(String secret, String inputFilePath, String outputFilePath)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, IOException {
		doCrypto(Cipher.ENCRYPT_MODE, secret, inputFilePath, outputFilePath);
	}

	public static void decryptFile(String secret, String inputFilePath, String outputFilePath)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, IOException {
		doCrypto(Cipher.DECRYPT_MODE, secret, inputFilePath, outputFilePath);
	}

	private static void doCrypto(int cipherMode, String secret, String inputFilePath, String outputFilePath)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, IOException {
		SecretKeySpec key = new SecretKeySpec(secret.getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(cipherMode, key);
		try (InputStream inputStream = new FileInputStream(new File(inputFilePath));
				OutputStream outputStream = new FileOutputStream(new File(outputFilePath))) {
			outputStream.write(cipher.doFinal(inputStream.readAllBytes()));
		} catch (IOException e) {
			throw new IOException(e);
		}
	}
}
