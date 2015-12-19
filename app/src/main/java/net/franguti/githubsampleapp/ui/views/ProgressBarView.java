package net.franguti.githubsampleapp.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import net.franguti.githubsampleapp.R;

/**
 * Custom Progress Bar. Use {@link #start()} and {@link #stop()} to start and stop respectively
 * the rotating animation.
 */
public class ProgressBarView extends ImageView {

  private static final String TAG = ProgressBarView.class.getSimpleName();

  private Animation rotateAnimation;

  public ProgressBarView(Context context) {
    super(context);
    initialise(context);
  }

  public ProgressBarView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialise(context);
  }

  public ProgressBarView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initialise(context);
  }

  private void initialise(Context context) {
    setImageResource(R.drawable.ic_progress_bar);
    setVisibility(View.INVISIBLE);
    rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_animation);
  }

  public void start() {
    setVisibility(View.VISIBLE);
    startAnimation(rotateAnimation);
  }

  public void stop() {
    setVisibility(View.INVISIBLE);
    clearAnimation();
  }
}