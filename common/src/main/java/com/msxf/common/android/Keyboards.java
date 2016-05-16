package com.msxf.common.android;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author RobX (robxyy@gmail.com)
 */
public final class Keyboards {
  private Keyboards() {
    // No instances.
  }

  private static InputMethodManager getInputManager(Context paramContext) {
    return (InputMethodManager) paramContext.getSystemService(Context.INPUT_METHOD_SERVICE);
  }

  public static void hideKeyboard(Context paramContext, IBinder iBinder) {
    getInputManager(paramContext).hideSoftInputFromWindow(iBinder, 0);
  }

  public static void hideKeyboard(View view) {
    hideKeyboard(view.getContext(), view.getWindowToken());
  }

  public static void showKeyboard(View view) {
    getInputManager(view.getContext()).showSoftInput(view, 0);
  }
}
