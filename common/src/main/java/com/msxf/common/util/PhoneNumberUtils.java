package com.msxf.common.util;

/**
 * @author RobX (robxyy@gmail.com)
 */
public final class PhoneNumberUtils {
  private PhoneNumberUtils() {
    // No instances.
  }

  public static boolean isValid(String string) {
    if (Strings.isBlank(string)) {
      return false;
    }
    if (string.length() == 11) {
      if (string.charAt(0) == '1') {
        return Strings.isNumeric(string);
      }
    }
    return false;
  }
}
