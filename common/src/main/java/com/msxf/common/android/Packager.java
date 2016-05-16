package com.msxf.common.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import static com.msxf.common.base.Preconditions.checkNotNull;

/**
 * Package 工具类
 *
 * @author RobX (robxyy@gmail.com)
 */
public final class Packager {
  private Packager() {
    // No instances.
  }

  /**
   * 当且仅当已经安装才返回true,反之返回false.
   *
   * @param context 上下文引用
   * @param packageName 包名
   */
  public static boolean hasInstalled(@NonNull Context context, String packageName) {
    checkNotNull(context, "context == null");
    boolean installed = false;
    try {
      PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
      installed = info != null;
    } catch (PackageManager.NameNotFoundException e) {
      // ignore
    }
    return installed;
  }
}
