package net.franguti.githubsampleapp.ui.presenters;

import net.franguti.githubsampleapp.entities.Repository;

public interface RepositoryListView {

  void showSearchTutorial();
  void hideSearchTutorial();
  void showSearchLanguageField();
  void hideSearchLanguageField();
  void showRepositories(Repository[] repositories);
  void showProgressBar();
  void hideProgressBar();
  void showSearchError();
  boolean isSearchLanguageFieldDisplayed();
  String getSearchLanguageFieldString();
}
