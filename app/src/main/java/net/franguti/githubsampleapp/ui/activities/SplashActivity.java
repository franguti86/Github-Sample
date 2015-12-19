package net.franguti.githubsampleapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import net.franguti.githubsampleapp.R;

public class SplashActivity extends AppCompatActivity {

  private static final long TIMEOUT_SPLASH = 3 * 1000;

  @Bind(R.id.logo_view) ImageView logoView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    ButterKnife.bind(this);

    startProgressBarAnimation();
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        startMainActivity();
      }
    }, TIMEOUT_SPLASH);
  }

  private void startProgressBarAnimation() {
    Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_splash_scaling);
    logoView.startAnimation(logoAnimation);
  }

  private void startMainActivity() {
    Intent intent = new Intent(this, ScrollingActivity.class);
    startActivity(intent);
    finish();
  }

}
