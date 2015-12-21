package net.franguti.githubsampleapp.ui.presenters;

import javax.inject.Inject;
import net.franguti.githubsampleapp.domain.interactors.SearchRepositoriesInteractor;
import net.franguti.githubsampleapp.entities.Repository;
import net.franguti.githubsampleapp.entities.SearchResult;
import net.franguti.githubsampleapp.ui.Navigator;

public class RepositoryListPresenter implements SearchRepositoriesInteractor.Callback {

  private final SearchRepositoriesInteractor searchRepositoriesInteractor;
  private final Navigator navigator;
  private RepositoryListView view;
  private Repository[] repositories;

  @Inject public RepositoryListPresenter(SearchRepositoriesInteractor searchRepositoriesInteractor, Navigator navigator) {
    this.searchRepositoriesInteractor = searchRepositoriesInteractor;
    this.navigator = navigator;
  }

  public void setView(RepositoryListView view) {
    this.view = view;
  }

  public void start() {
    view.hideSearchLanguageField();
    if (repositories == null) view.showSearchTutorial();
  }

  public void performShowSearchField() {
    if (!view.isSearchLanguageFieldDisplayed()) {
      view.showSearchLanguageField();
    } else {
      String language = view.getSearchLanguageFieldString();
      if (language.length() > 0) {
        performSearchRepository(language);
      }
      view.hideSearchLanguageField();
    }
  }

  public void performSearchRepository(String language) {
    view.hideSearchLanguageField();
    view.hideSearchTutorial();
    view.showRepositories(new Repository[0]);
    view.showProgressBar();
    searchRepositoriesInteractor.searchPopularRepositoriesByLanguage(language, this);
  }

  @Override public void onSearchRepositoriesSuccess(SearchResult searchResult) {
    this.repositories = searchResult.getRepositories();
    view.hideProgressBar();
    view.showRepositories(searchResult.getRepositories());
  }

  @Override public void onSearchRepositoriesError() {
    view.hideProgressBar();
    view.showSearchError();
    view.showSearchTutorial();
  }

  public void performShowRepositoryDetail(Repository repository) {
    navigator.openRepositoryDetail(repository);
  }
}
