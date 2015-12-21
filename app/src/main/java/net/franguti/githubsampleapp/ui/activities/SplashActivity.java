package net.franguti.githubsampleapp.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import butterknife.Bind;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import net.franguti.githubsampleapp.R;
import net.franguti.githubsampleapp.ui.Navigator;
import net.franguti.githubsampleapp.ui.UIModule;

public class SplashActivity extends BaseActivity {

  private static final long TIMEOUT_SPLASH = 3 * 1000;

  @Inject Navigator navigator;

  @Bind(R.id.logo_view) ImageView logoView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    startProgressBarAnimation();
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        startMainActivity();
      }
    }, TIMEOUT_SPLASH);
  }

  @Override protected int getActivityLayout() {
    return R.layout.activity_splash;
  }

  @Override protected List<Object> getModules() {
    List<Object> modules = new ArrayList<>();
    modules.add(new UIModule());
    return modules;
  }

  private void startProgressBarAnimation() {
    Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_splash_scaling);
    logoView.startAnimation(logoAnimation);
  }

  private void startMainActivity() {
    navigator.openRepositoryList();
    finish();
  }

}
