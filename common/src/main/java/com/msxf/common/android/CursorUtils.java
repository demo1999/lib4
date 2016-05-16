package com.msxf.common.android;

import android.database.Cursor;

/**
 * @author RobX (robxyy@gmail.com)
 */
public final class CursorUtils {
  private CursorUtils() {
    // No instances.
  }

  public static void closeQuietly(Cursor cursor) {
    try {
      cursor.close();
    } catch (Exception ignore) {
      // ignore
    }
  }
}
