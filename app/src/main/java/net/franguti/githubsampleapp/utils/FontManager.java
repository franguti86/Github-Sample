package net.franguti.githubsampleapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import javax.inject.Inject;

public class FontManager {

  private final Typeface regularFont;
  private final Typeface italicFont;
  private final Typeface boldFont;
  private final Typeface extraBoldFont;

  @Inject public FontManager(Context context) {
    regularFont   = Typeface.createFromAsset(context.getResources().getAssets(),
        "fonts/Roboto-Light.ttf");
    italicFont    = Typeface.createFromAsset(context.getResources().getAssets(),
        "fonts/Roboto-LightItalic.ttf");
    boldFont      = Typeface.createFromAsset(context.getResources().getAssets(),
        "fonts/Roboto-Regular.ttf");
    extraBoldFont = Typeface.createFromAsset(context.getResources().getAssets(),
        "fonts/Roboto-Bold.ttf");
  }

  public void setFont(View view) {
    if (view instanceof ViewGroup) {
      ViewGroup group = (ViewGroup) view;
      final int totalChildren = group.getChildCount();

      for (int i = 0; i < totalChildren; i++) {
        View childView = group.getChildAt(i);
        setFont(childView);
      }
    } else if (view instanceof TextView) {
      TextView textView = (TextView) view;
      Typeface typeface = textView.getTypeface();

      if (typeface != null && typeface.isBold()) {
        textView.setTypeface(boldFont);
        textView.setTypeface(boldFont, Typeface.BOLD);
      }
      if (typeface != null && typeface.isItalic()) {
        textView.setTypeface(italicFont);
      } else if (textView.getTag() != null
          && textView.getTag().toString().compareTo("EXTRA_BOLD") == 0) {
        textView.setTypeface(extraBoldFont);
      } else {
        textView.setTypeface(regularFont);
      }
    }
  }
}
