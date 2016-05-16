package com.msxf.common.util;

import android.support.annotation.NonNull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 字符串工具类
 *
 * @author RobX (robxyy@gmail.com)
 */
public final class Strings {
  private Strings() {
    // No instances.
  }

  public static boolean isBlank(CharSequence string) {
    return (string == null || string.toString().trim().length() == 0);
  }

  public static String valueOrDefault(String string, String defaultString) {
    return isBlank(string) ? defaultString : string;
  }

  public static String truncateAt(String string, int length) {
    return string.length() > length ? string.substring(0, length) : string;
  }

  public static String inputStreamToString(@NonNull InputStream is) throws IOException {
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    int i;
    while ((i = is.read()) != -1) {
      b.write(i);
    }
    return b.toString();
  }

  public static boolean isNumeric(@NonNull String string) {
    for (int i = 0; i < string.length(); i++) {
      if (!Character.isDigit(string.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static boolean allEqual(@NonNull String string, char ch) {
    for (int i = 0; i < string.length(); i++) {
      if (ch != string.charAt(i)) {
        return false;
      }
    }
    return true;
  }
}
