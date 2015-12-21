package net.franguti.githubsampleapp.domain.interactors;

import net.franguti.githubsampleapp.entities.SearchResult;

public interface SearchRepositoriesInteractor {

  interface Callback {
    void onSearchRepositoriesSuccess(SearchResult searchResult);
    void onSearchRepositoriesError();
  }

  void searchPopularRepositoriesByLanguage(String language, Callback callback);

}
