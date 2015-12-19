package net.franguti.githubsampleapp.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;
import dagger.Module;
import dagger.Provides;
import net.franguti.githubsampleapp.GithubApplication;
import net.franguti.githubsampleapp.domain.DomainModule;

/**
 * Dagger module for the main graph.
 */
@Module(
    includes = {
        DomainModule.class
    },
    injects = {
        GithubApplication.class
    },
    library = true) public class RootModule {

  private final Context context;

  public RootModule(Context context) {
    this.context = context;
  }

  @Provides Context provideApplicationContext() {
    return context;
  }

  @Provides LayoutInflater provideLayoutInflater() {
    return LayoutInflater.from(context);
  }

}
