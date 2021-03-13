package me.prajit;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Tester {

	private static final String DIRECTORY = "C:/Users/Jitin/Desktop/test";
	private static final String KEY = "RandomSecret@123";

	public static void main(String[] args) {
		try {
			FileCryptUtil.encryptFile(KEY, DIRECTORY + "/TestFile.jpg", DIRECTORY + "/TestFile.enc");
			System.out.println("File has been successfully encrypted...");
			FileCryptUtil.decryptFile(KEY, DIRECTORY + "/TestFile.enc", DIRECTORY + "/TestFileEnc.jpg");
			System.out.println("File has been successfully decrypted...");
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			System.out.println(e);
		}
	}

}
