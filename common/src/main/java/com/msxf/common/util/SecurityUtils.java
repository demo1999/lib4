package com.msxf.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 加密工具类
 *
 * @author RobX (robxyy@gmail.com)
 */
public final class SecurityUtils {
  private SecurityUtils() {
    // No instances.
  }

  /**
   * 返回 MD5 加密的结果
   *
   * @param text 内容
   * @throws NoSuchAlgorithmException
   * @throws UnsupportedEncodingException
   */
  public static String encryptMD5(String text)
      throws NoSuchAlgorithmException, UnsupportedEncodingException {
    MessageDigest digest = MessageDigest.getInstance("MD5");
    byte[] hashes = digest.digest(text.getBytes("UTF-8"));
    StringBuilder sb = new StringBuilder();
    for (byte hash : hashes) {
      if ((0xff & hash) < 0x10) {
        sb.append('0').append(Integer.toHexString((0xff & hash)));
      } else {
        sb.append(Integer.toHexString(0xff & hash));
      }
    }
    return sb.toString();
  }

  /**
   * 返回 SHA-256 加密的结果
   *
   * @param text 内容
   * @throws NoSuchAlgorithmException
   * @throws UnsupportedEncodingException
   */
  public static String encryptSHA256(String text)
      throws NoSuchAlgorithmException, UnsupportedEncodingException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hashes = digest.digest(text.getBytes());
    StringBuilder sb = new StringBuilder();
    for (byte hash : hashes) {
      String hex = Integer.toHexString(0xff & hash);
      if (hex.length() == 1) sb.append('0');
      sb.append(hex);
    }
    return sb.toString();
  }

  /**
   * 返回 DES 加密的结果
   *
   * @param desKey DES密匙
   * @param text 内容
   */
  public static byte[] encryptDES(String desKey, String text) {
    try {
      Cipher des = Cipher.getInstance("DES");
      DESKeySpec desKeySpec = new DESKeySpec(desKey.getBytes());
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
      des.init(Cipher.ENCRYPT_MODE, secretKey);
      return des.doFinal(text.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 返回 DES 解密的结果
   *
   * @param desKey DES密码
   * @param text 内容
   */
  public static String decryptDES(String desKey, byte[] text) {
    try {
      Cipher des = Cipher.getInstance("DES");
      DESKeySpec desKeySpec = new DESKeySpec(desKey.getBytes());
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
      des.init(Cipher.DECRYPT_MODE, secretKey);
      byte[] utf8 = des.doFinal(text);
      return new String(utf8, "UTF8");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 返回 RSA 加密的结果
   *
   * @param publicKey 公匙
   * @param text 内容
   */
  public static byte[] encryptRSA(Key publicKey, String text) {
    try {
      Cipher rsa = Cipher.getInstance("RSA");
      rsa.init(Cipher.ENCRYPT_MODE, publicKey);
      return rsa.doFinal(text.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 返回 RSA 解密的结果
   *
   * @param privateKey 私匙
   * @param text 内容
   */
  public static String decryptRSA(Key privateKey, byte[] text) {
    try {
      Cipher rsa = Cipher.getInstance("RSA");
      rsa.init(Cipher.DECRYPT_MODE, privateKey);
      byte[] utf8 = rsa.doFinal(text);
      return new String(utf8, "UTF8");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 根据文件路径返回公匙
   *
   * @param filePath 文件路径
   * @throws IOException
   */
  public static PublicKey readPublicKey(String filePath) throws IOException {
    FileInputStream fis = new FileInputStream(new File(filePath));
    return readPublicKey(fis);
  }

  /**
   * 根据输入流返回公匙
   *
   * @param input 输入流
   * @throws IOException
   */
  public static PublicKey readPublicKey(InputStream input) throws IOException {
    final ByteArrayOutputStream output = new ByteArrayOutputStream();
    int n = 0;
    final byte[] buffer = new byte[1024 * 4];
    while (-1 != (n = input.read(buffer))) {
      output.write(buffer, 0, n);
    }
    byte[] keyBytes = output.toByteArray();
    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
    try {
      KeyFactory kf = KeyFactory.getInstance("RSA");
      return kf.generatePublic(spec);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 根据文件路径返回私匙
   *
   * @param filePath 文件路径
   * @throws IOException
   */
  public static PrivateKey readPrivateKey(String filePath) throws IOException {
    FileInputStream fis = new FileInputStream(new File(filePath));
    return readPrivateKey(fis);
  }

  /**
   * 根据输入流返回私匙
   *
   * @param input 输入流
   * @throws IOException
   */
  public static PrivateKey readPrivateKey(InputStream input) throws IOException {
    final ByteArrayOutputStream output = new ByteArrayOutputStream();
    int n = 0;
    final byte[] buffer = new byte[1024 * 4];
    while (-1 != (n = input.read(buffer))) {
      output.write(buffer, 0, n);
    }
    byte[] keyBytes = output.toByteArray();
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
    try {
      KeyFactory kf = KeyFactory.getInstance("RSA");
      return kf.generatePrivate(spec);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return null;
  }
}
