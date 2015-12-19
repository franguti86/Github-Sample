package net.franguti.githubsampleapp.ui;

import android.content.Context;
import android.content.Intent;
import javax.inject.Inject;
import net.franguti.githubsampleapp.dependencyinjection.ActivityContext;
import net.franguti.githubsampleapp.entities.Repository;
import net.franguti.githubsampleapp.ui.activities.RepositoryDetailActivity;
import net.franguti.githubsampleapp.ui.activities.RepositoryListActivity;

/**
 * Class created to handle all the navigation between activities. This class knows how to open
 * every activity in the application and provides to the client code different methods to start
 * activities with the information needed.
 */
public class Navigator {

  private final Context activityContext;

  @Inject
  public Navigator(@ActivityContext Context activityContext) {
    this.activityContext = activityContext;
  }

  public void openRepositoryList() {
    RepositoryListActivity.start(activityContext);
  }

  public void openRepositoryDetail(Repository repository) {
    RepositoryDetailActivity.start(activityContext, repository);
  }

}
