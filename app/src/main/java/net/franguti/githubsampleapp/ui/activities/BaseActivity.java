package net.franguti.githubsampleapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import dagger.ObjectGraph;
import java.util.ArrayList;
import java.util.List;
import net.franguti.githubsampleapp.GithubApplication;
import net.franguti.githubsampleapp.dependencyinjection.ActivityModule;

/**
 * Base activity created to be extended by every activity in this application. This class provides
 * dependency injection configuration, ButterKnife Android library configuration and some methods
 * common to every activity.
 */
public abstract class BaseActivity extends AppCompatActivity {

  private ObjectGraph activityScopeGraph;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injectDependencies();
    injectViews();
  }

  /**
   * Method used to resolve dependencies provided by Dagger modules. Inject an object to provide
   * every @Inject annotation contained.
   *
   * @param object to inject.
   */
  public void inject(Object object) {
    activityScopeGraph.inject(object);
  }

  /**
   * Get a list of Dagger modules with Activity scope needed to this Activity.
   *
   * @return modules with new dependencies to provide.
   */
  protected abstract List<Object> getModules();

  /**
   * Create a new Dagger ObjectGraph to add new dependencies using a plus operation and inject the
   * declared one in the activity. This new graph will be destroyed once the activity lifecycle
   * finish.
   *
   * This is the key of how to use Activity scope dependency injection.
   */
  private void injectDependencies() {
    GithubApplication githubApplication = (GithubApplication) getApplication();
    List<Object> activityScopeModules = getModules();
    if (activityScopeModules == null) activityScopeModules = new ArrayList<>(1);
    activityScopeModules.add(new ActivityModule(this));
    activityScopeGraph = githubApplication.plus(activityScopeModules);
    inject(this);
  }

  /**
   * Replace every field annotated with ButterKnife annotations like @InjectView with the proper
   * value.
   */
  private void injectViews() {
    ButterKnife.bind(this);
  }
}
