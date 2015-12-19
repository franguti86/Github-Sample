package net.franguti.githubsampleapp.domain.interactors;

import net.franguti.githubsampleapp.entities.Repository;
import net.franguti.githubsampleapp.entities.User;

public interface ContributorsInteractor {

  interface Callback {
    void onRetrieveContributorsSuccess(User[] contributors);
    void onRetrieveContributorsError();
  }

  void retrieveThreeTopContributorsByRepository(Repository repository, Callback callback);

}
