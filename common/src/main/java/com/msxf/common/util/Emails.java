package com.msxf.common.util;

/**
 * @author RobX (robxyy@gmail.com)
 */
public final class Emails {
  private Emails() {
    // No instances.
  }

  /**
   * 当且仅当为有效的电子邮件地址才返回true,反之返回false.
   */
  public static boolean isValid(String email) {
    if (!Strings.isBlank(email)) {
      // 注意: 有的邮箱提供者没有按照标准,所以在此只验证两部分:
      // 1.含有"@"符号,
      // 2."@"后面部分含有"."符号.
      int len = email.length();
      int firstAt = email.indexOf('@');
      int lastAt = email.lastIndexOf('@');
      int firstDot = email.indexOf('.', lastAt + 1);
      int lastDot = email.lastIndexOf('.');
      return firstAt > 0
          && firstAt == lastAt
          && lastAt + 1 < firstDot
          && firstDot <= lastDot
          && lastDot < len - 1;
    }
    return false;
  }
}
