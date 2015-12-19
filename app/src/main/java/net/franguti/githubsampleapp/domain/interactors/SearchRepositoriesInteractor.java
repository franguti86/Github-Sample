package net.franguti.githubsampleapp.domain.interactors;

import net.franguti.githubsampleapp.entities.SearchResult;

public interface SearchRepositoriesInteractor {

  interface Callback {
    void onSearchRepositoriesSuccess(SearchResult searchResult);
    void onSearchRepositoriesError();
  }

  /**
   * RetrieveContactProvider implementation. This interactor uses Retrofit async call to
   * execute the use case outside of the UI Thread. The result will be sent through an event bus
   * on SearchRepositoriesEvent.
   *
   * @param language Language to search.
   */
  void searchPopularRepositoriesByLanguage(String language, Callback callback);

}
