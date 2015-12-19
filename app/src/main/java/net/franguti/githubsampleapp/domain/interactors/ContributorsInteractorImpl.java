package net.franguti.githubsampleapp.domain.interactors;

import java.util.Arrays;
import java.util.Comparator;
import javax.inject.Inject;
import net.franguti.githubsampleapp.api.GithubAPI;
import net.franguti.githubsampleapp.entities.Repository;
import net.franguti.githubsampleapp.entities.User;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ContributorsInteractorImpl implements ContributorsInteractor {

  private final GithubAPI githubAPI;

  @Inject public ContributorsInteractorImpl(GithubAPI githubAPI) {
    this.githubAPI = githubAPI;
  }

  @Override
  public void retrieveThreeTopContributorsByRepository(Repository repository,
      final Callback callback) {
    githubAPI.retrieveContributorsForARepository(repository.getOwner().getLogin(),
        repository.getName(), new retrofit.Callback<User[]>() {
      @Override public void success(User[] users, Response response) {
        User[] contributors = sortAndTakeThreeTopContributors(users);
        callback.onRetrieveContributorsSuccess(contributors);
      }

      @Override public void failure(RetrofitError error) {
        callback.onRetrieveContributorsError();
      }
    });
  }

  private User[] sortAndTakeThreeTopContributors(User[] contributors) {
    Arrays.sort(contributors, new TopContributorsComparator());
    if (contributors.length > 3) {
      return Arrays.copyOf(contributors, 3);
    }
    return contributors;
  }

  static class TopContributorsComparator implements Comparator<User> {

    @Override public int compare(User lhs, User rhs) {
      return lhs.getContributions() < rhs.getContributions() ? -1
          : (lhs.getContributions() == rhs.getContributions() ? 0 : 1);
    }
  }
}
