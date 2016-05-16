package com.msxf.common.android;

import android.content.SharedPreferences;

/**
 * 枚举类型的 {@link SharedPreferences} 工具类
 *
 * @author RobX (robxyy@gmail.com)
 */
public final class EnumPreferences {
  private EnumPreferences() {
    // No instances.
  }

  public static <T extends Enum> T getEnumValue(SharedPreferences preferences, Class<T> type,
      String key, T defaultValue) {
    String name = preferences.getString(key, null);
    if (name != null) {
      try {
        return type.cast(Enum.valueOf(type, name));
      } catch (IllegalArgumentException ignore) {
        // ignore
      }
    }

    return defaultValue;
  }

  public static void saveEnumValue(SharedPreferences preferences, String key, Enum value) {
    preferences.edit().putString(key, value.name()).apply();
  }
}
