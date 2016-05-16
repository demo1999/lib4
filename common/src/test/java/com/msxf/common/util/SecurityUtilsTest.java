package com.msxf.common.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author RobX (robxyy@gmail.com)
 */
public class SecurityUtilsTest {

  @Test public void testEncryptMD5() {
    try {
      assertEquals("5d41402abc4b2a76b9719d911017c592", SecurityUtils.encryptMD5("hello"));
      assertEquals("e0e49ef52bcd35f2b7ebb9613ae21ef9", SecurityUtils.encryptMD5("嘛，daf"));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  @Test public void testEncryptSHA256() {
    try {
      assertEquals("2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824",
          SecurityUtils.encryptSHA256("hello"));
      assertEquals("ae4c390ef0cae6d58df2ffd06936bf0224f9de9589a4a41f204aa507ec898db5",
          SecurityUtils.encryptSHA256("嘛，daf"));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  @Test public void testEncryptDESAndDecryptDES() {
    String key = "589a4a41f204aa507ec898d";
    String v1 = "hello";
    String v2 = "嘛，daf";
    assertEquals(v1, SecurityUtils.decryptDES(key, SecurityUtils.encryptDES(key, v1)));
    assertEquals(v2, SecurityUtils.decryptDES(key, SecurityUtils.encryptDES(key, v2)));
  }
}
