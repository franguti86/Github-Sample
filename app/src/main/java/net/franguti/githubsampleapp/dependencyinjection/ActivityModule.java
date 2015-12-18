package net.franguti.githubsampleapp.dependencyinjection;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger module created to provide some common activity scope depdendencies as @ActivityContext.
 * This module is going to be added to the graph generated for every activity while the activity
 * creation lifecycle.
 */
@Module(library = true)
public class ActivityModule {

  private final FragmentActivity activity;

  public ActivityModule(FragmentActivity activity) {
    this.activity = activity;
  }

  @ActivityContext @Provides Context provideActivityContext() {
    return activity;
  }

}
