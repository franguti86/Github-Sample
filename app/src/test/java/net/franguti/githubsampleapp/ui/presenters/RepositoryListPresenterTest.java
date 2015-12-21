package net.franguti.githubsampleapp.ui.presenters;

import dagger.ObjectGraph;
import java.io.IOException;
import javax.inject.Inject;
import net.franguti.githubsampleapp.domain.interactors.SearchRepositoriesInteractor;
import net.franguti.githubsampleapp.entities.Repository;
import net.franguti.githubsampleapp.entities.SearchResult;
import net.franguti.githubsampleapp.ui.Navigator;
import net.franguti.githubsampleapp.ui.TestUIModule;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepositoryListPresenterTest {

  @Inject RepositoryListPresenter presenter;
  @Inject SearchRepositoriesInteractor mockSearchRepositoriesInteractor;
  @Inject Navigator mockNavigator;

  private RepositoryListView mockView;

  @Before public void setUp() throws IOException {
    ObjectGraph objectGraph = ObjectGraph.create(new TestUIModule());
    objectGraph.inject(this);

    mockView = mock(RepositoryListView.class);
  }

  @Test public void givenAPresenter_WhenStart_ThenShouldShowSearchTutorial() {
    presenter.setView(mockView);
    presenter.start();
    verify(mockView).showSearchTutorial();
  }

  @Test public void givenAPresenter_WhenPerformShowSearchFieldAndFieldIsNotDisplayed_ThenShouldShowSearchField() {
    when(mockView.isSearchLanguageFieldDisplayed()).thenReturn(false);
    presenter.setView(mockView);
    presenter.performShowSearchField();
    verify(mockView).showSearchLanguageField();
  }

  @Test public void givenAPresenter_WhenPerformShowSearchFieldAndFieldIsDisplayed_ThenShouldCallSearchRepositoriesInteractor() {
    String language = "java";
    when(mockView.isSearchLanguageFieldDisplayed()).thenReturn(true);
    when(mockView.getSearchLanguageFieldString()).thenReturn(language);
    presenter.setView(mockView);
    presenter.performShowSearchField();
    verify(mockSearchRepositoriesInteractor).searchPopularRepositoriesByLanguage(eq(language),
        any(SearchRepositoriesInteractor.Callback.class));
  }

  @Test
  public void givenAPresenter_WhenPerformSearchRepository_ThenShouldHideSearchLanguageField() {
    presenter.setView(mockView);
    presenter.performSearchRepository("java");
    verify(mockView).hideSearchLanguageField();
  }

  @Test
  public void givenAPresenter_WhenPerformSearchRepository_ThenShouldShowProgressbar() {
    presenter.setView(mockView);
    presenter.performSearchRepository("java");
    verify(mockView).showProgressBar();
  }

  @Test
  public void givenAPresenter_WhenPerformSearchRepository_ThenShouldHideSearchTutorial() {
    presenter.setView(mockView);
    presenter.performSearchRepository("java");
    verify(mockView).hideSearchTutorial();
  }

  @Test
  public void givenAPresenter_WhenPerformSearchRepository_ThenShouldCallSearchRepositoriesInteractor() {
    String language = "java";
    presenter.setView(mockView);
    presenter.performSearchRepository(language);
    verify(mockSearchRepositoriesInteractor).searchPopularRepositoriesByLanguage(eq(language),
        any(SearchRepositoriesInteractor.Callback.class));
  }

  @Test
  public void givenAPresenter_WhenPerformShowRepositoryDetail_ThenShouldCallNavigatorOpenRepositoryDetail() {
    Repository repository = new Repository();
    presenter.setView(mockView);
    presenter.performShowRepositoryDetail(repository);
    verify(mockNavigator).openRepositoryDetail(eq(repository));
  }

  @Test
  public void givenAPresenter_WhenSearchRepositoriesSuccess_ThenShouldHideProgressbar() {
    Repository[] repositories = new Repository[0];
    SearchResult mockSearchResult = mock(SearchResult.class);
    when(mockSearchResult.getRepositories()).thenReturn(repositories);
    presenter.setView(mockView);
    presenter.onSearchRepositoriesSuccess(mockSearchResult);
    verify(mockView).hideProgressBar();
  }

  @Test
  public void givenAPresenter_WhenSearchRepositoriesSuccess_ThenShouldShowSearchResult() {
    Repository[] repositories = new Repository[0];
    SearchResult mockSearchResult = mock(SearchResult.class);
    when(mockSearchResult.getRepositories()).thenReturn(repositories);
    presenter.setView(mockView);
    presenter.onSearchRepositoriesSuccess(mockSearchResult);
    verify(mockView).showRepositories(eq(repositories));
  }

  @Test
  public void givenAPresenter_WhenSearchRepositoriesError_ThenShouldHideProgressbar() {
    presenter.setView(mockView);
    presenter.onSearchRepositoriesError();
    verify(mockView).hideProgressBar();
  }

  @Test
  public void givenAPresenter_WhenSearchRepositoriesError_ThenShouldShowSearchError() {
    presenter.setView(mockView);
    presenter.onSearchRepositoriesError();
    verify(mockView).showSearchError();
  }

  @Test
  public void givenAPresenter_WhenSearchRepositoriesError_ThenShouldShowSearchTutorial() {
    presenter.setView(mockView);
    presenter.onSearchRepositoriesError();
    verify(mockView).showSearchTutorial();
  }

}
