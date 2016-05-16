package com.msxf.common.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/**
 * 屏幕尺寸工具类
 *
 * @author RobX (robxyy@gmail.com)
 */
public final class MeasureUtils {
  private MeasureUtils() {
    // No instances.
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2) public static Point getDisplay(Context context) {
    final Point out = new Point();
    WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = manager.getDefaultDisplay();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
      display.getSize(out);
    } else {
      out.x = display.getWidth();
      out.y = display.getHeight();
    }
    return out;
  }

  /**
   * 计算最适尺寸
   *
   * @param context 引用
   * @param originImageSize 原始图片大小
   * @param originDisplayWith 设计图宽度
   */
  public static Point completeImageSize(Context context, Point originImageSize,
      int originDisplayWith) {
    final Point out = getDisplay(context);
    out.x = out.x * originImageSize.x / originDisplayWith;
    out.y = out.x * originImageSize.y / originImageSize.x;
    return out;
  }

  /**
   * 获取状态栏高度
   */
  public static int getStatusBarHeight(Activity activity) {
    Rect frame = new Rect();
    activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
    return frame.top;
  }
}
