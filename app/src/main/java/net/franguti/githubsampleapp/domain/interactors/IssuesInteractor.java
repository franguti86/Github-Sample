package net.franguti.githubsampleapp.domain.interactors;

import net.franguti.githubsampleapp.entities.Issue;
import net.franguti.githubsampleapp.entities.Repository;

public interface IssuesInteractor {

  interface Callback {
    void onRetrieveIssuesSuccess(Issue[] issues);
    void onRetrieveIssuesError();
  }

  void retrieveThreeNewestIssuesByRepository(Repository repository, Callback callback);

}
