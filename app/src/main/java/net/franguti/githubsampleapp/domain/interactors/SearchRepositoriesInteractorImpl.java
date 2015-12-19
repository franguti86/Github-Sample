package net.franguti.githubsampleapp.domain.interactors;

import javax.inject.Inject;
import net.franguti.githubsampleapp.api.GithubAPI;
import net.franguti.githubsampleapp.entities.SearchResult;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SearchRepositoriesInteractorImpl implements SearchRepositoriesInteractor {

  private final GithubAPI githubAPI;

  @Inject public SearchRepositoriesInteractorImpl(GithubAPI githubAPI) {
    this.githubAPI = githubAPI;
  }

  @Override public void searchPopularRepositoriesByLanguage(String language, final Callback callback) {
    String query = String.format("language:%s", language);
    githubAPI.searchRepositories(query, GithubAPI.SORT_REPO_STARS, GithubAPI.ORDER_REPO_DESC, new retrofit.Callback<SearchResult>() {
      @Override public void success(SearchResult searchResult, Response response) {
        callback.onSearchRepositoriesSuccess(searchResult);
      }

      @Override public void failure(RetrofitError error) {
        callback.onSearchRepositoriesError();
      }
    });
  }

}
