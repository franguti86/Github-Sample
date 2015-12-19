package net.franguti.githubsampleapp.ui.presenters;

import javax.inject.Inject;
import net.franguti.githubsampleapp.domain.interactors.SearchRepositoriesInteractor;
import net.franguti.githubsampleapp.entities.Repository;
import net.franguti.githubsampleapp.entities.SearchResult;

public class RepositoryListPresenter implements SearchRepositoriesInteractor.Callback {

  private final SearchRepositoriesInteractor searchRepositoriesInteractor;
  private RepositoryListView view;

  @Inject public RepositoryListPresenter(SearchRepositoriesInteractor searchRepositoriesInteractor) {
    this.searchRepositoriesInteractor = searchRepositoriesInteractor;
  }

  public void setView(RepositoryListView view) {
    this.view = view;
  }

  public void start() {
    view.hideSearchLanguageField();
  }

  public void performShowSearchField() {
    if (!view.isSearchLanguageFieldDisplayed()) {
      view.showSearchLanguageField();
    } else {
      view.hideSearchLanguageField();
    }
  }

  public void performSearchRepository(String language) {
    view.hideSearchLanguageField();
    view.showRepositories(new Repository[0]);
    view.showProgressBar();
    searchRepositoriesInteractor.searchPopularRepositoriesByLanguage(language, this);
  }

  @Override public void onSearchRepositoriesSuccess(SearchResult searchResult) {
    view.hideProgressBar();
    view.showRepositories(searchResult.getRepositories());
  }

  @Override public void onSearchRepositoriesError() {
    view.hideProgressBar();
    view.showSearchError();
  }

}
