package net.franguti.githubsampleapp.domain.interactors;

import java.util.Arrays;
import javax.inject.Inject;
import net.franguti.githubsampleapp.api.GithubAPI;
import net.franguti.githubsampleapp.entities.Issue;
import net.franguti.githubsampleapp.entities.Repository;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class IssuesInteractorImpl implements IssuesInteractor {

  private final GithubAPI githubAPI;

  @Inject public IssuesInteractorImpl(GithubAPI githubAPI) {
    this.githubAPI = githubAPI;
  }

  @Override public void retrieveThreeNewestIssuesByRepository(Repository repository,
      final Callback callback) {
    githubAPI.retrieveIssuesForARepository(repository.getOwner().getLogin(), repository.getName(),
        GithubAPI.SORT_ISSUE_CREATED, new retrofit.Callback<Issue[]>() {
          @Override public void success(Issue[] issues, Response response) {
            Issue[] newestIssues = takeThreeNewestIssues(issues);
            callback.onRetrieveIssuesSuccess(newestIssues);
          }

          @Override public void failure(RetrofitError error) {
            callback.onRetrieveIssuesError();
          }
        });
  }

  private Issue[] takeThreeNewestIssues(Issue[] issues) {
    if (issues.length > 3) {
      return Arrays.copyOf(issues, 3);
    }
    return issues;
  }
}
