package com.msxf.common.android;

import android.content.Context;
import android.util.TypedValue;

/**
 * 像数与屏幕密度转换工具类
 *
 * @author RobX (robxyy@gmail.com)
 */
public final class DensityUtils {
  private DensityUtils() {
    // No instances.
  }

  /**
   * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
   */
  public static int dipToPx(Context context, float dpValue) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
        context.getResources().getDisplayMetrics());
  }

  /**
   * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
   */
  public static int pxToDip(Context context, float pxValue) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pxValue,
        context.getResources().getDisplayMetrics());
  }
}
