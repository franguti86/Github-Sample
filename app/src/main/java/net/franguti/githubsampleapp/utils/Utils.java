package net.franguti.githubsampleapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class Utils {

  public static Point getScreenSize(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size;
  }

  public static float dpTopx(Context context, float dpiValue) {
    Resources r = context.getResources();
    float px =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpiValue, r.getDisplayMetrics());
    return px;
  }

  public static boolean isTablet(Context context) {
    return (context.getResources().getConfiguration().screenLayout
        & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
  }

  public static int getOrientation(Context context) {
    return context.getResources().getConfiguration().orientation;
  }

  public static boolean isDeviceInPortraitOrientation(Context context) {
    return getOrientation(context) == Configuration.ORIENTATION_PORTRAIT;
  }

  public static boolean isDeviceInLandscapeOrientation(Context context) {
    return isDeviceInPortraitOrientation(context) ? false : true;
  }

  public static void hideSoftKeyboard(Activity activity) {
    View currentViewFocused = activity.getCurrentFocus();
    if(currentViewFocused != null) {
      InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
      inputManager.hideSoftInputFromWindow(currentViewFocused.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
  }
}
