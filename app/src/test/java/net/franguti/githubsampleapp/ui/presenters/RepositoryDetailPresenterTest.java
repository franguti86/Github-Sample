package net.franguti.githubsampleapp.ui.presenters;

import dagger.ObjectGraph;
import java.io.IOException;
import javax.inject.Inject;
import net.franguti.githubsampleapp.domain.interactors.ContributorsInteractor;
import net.franguti.githubsampleapp.domain.interactors.IssuesInteractor;
import net.franguti.githubsampleapp.entities.Issue;
import net.franguti.githubsampleapp.entities.Repository;
import net.franguti.githubsampleapp.entities.User;
import net.franguti.githubsampleapp.ui.Navigator;
import net.franguti.githubsampleapp.ui.TestUIModule;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepositoryDetailPresenterTest {

  @Inject RepositoryDetailPresenter presenter;
  @Inject ContributorsInteractor mockContributorsInteractor;
  @Inject IssuesInteractor mockIssuesItentactor;
  @Inject Navigator mockNavigator;

  private RepositoryDetailView mockView;

  @Before public void setUp() throws IOException {
    ObjectGraph objectGraph = ObjectGraph.create(new TestUIModule());
    objectGraph.inject(this);

    mockView = mock(RepositoryDetailView.class);
  }

  @Test
  public void givenAPresenter_WhenStart_ThenShouldShowDetailOfRepository() {
    presenter.setView(mockView);
    presenter.start(any(Repository.class));
    verify(mockView).showRepositoryDetailInfo(any(Repository.class));
  }

  @Test
  public void givenAPresenter_WhenStart_ThenShouldShowContributorsProgressbar() {
    presenter.setView(mockView);
    presenter.start(any(Repository.class));
    verify(mockView).showContributorsProgressbar();
  }

  @Test
  public void givenAPresenter_WhenStart_ThenShouldShowIssuesProgressbar() {
    presenter.setView(mockView);
    presenter.start(any(Repository.class));
    verify(mockView).showIssuesProgressbar();
  }

  @Test
  public void givenAPresenter_WhenStart_ThenShouldRetrieveContributors() {
    presenter.setView(mockView);
    presenter.start(any(Repository.class));
    verify(mockContributorsInteractor).retrieveThreeTopContributorsByRepository(
        any(Repository.class), any(ContributorsInteractor.Callback.class));
  }

  @Test
  public void givenAPresenter_WhenStart_ThenShouldRetrieveIssues() {
    presenter.setView(mockView);
    presenter.start(any(Repository.class));
    verify(mockIssuesItentactor).retrieveThreeNewestIssuesByRepository(any(Repository.class),
        any(IssuesInteractor.Callback.class));
  }

  @Test
  public void givenAPresenter_WhenPerformShare_ThenShouldCallNavigatorShare() {
    Repository mockRepository = mock(Repository.class);
    when(mockRepository.getHtmlUrl()).thenReturn("html");
    String content = "content";
    presenter.setView(mockView);
    presenter.start(mockRepository);
    presenter.performShareRepository(content);
    verify(mockNavigator).shareContent(any(String.class));
  }

  @Test
  public void givenAPresenter_WhenRetrieveContributorsSuccess_ThenShouldHideContributorsProgressbar() {
    presenter.setView(mockView);
    presenter.onRetrieveContributorsSuccess(new User[0]);
    verify(mockView).hideContributorsProgressbar();
  }

  @Test
  public void givenAPresenter_WhenRetrieveContributorsSuccess_ThenShouldShowContributors() {
    User[] contributors = new User[0];
    presenter.setView(mockView);
    presenter.onRetrieveContributorsSuccess(contributors);
    verify(mockView).showContributors(eq(contributors));
  }

  @Test
  public void givenAPresenter_WhenRetrieveContributorsError_ThenShouldHideContributorsProgressbar() {
    presenter.setView(mockView);
    presenter.onRetrieveContributorsError();
    verify(mockView).hideContributorsProgressbar();
  }

  @Test
  public void givenAPresenter_WhenRetrieveContributorsError_ThenShouldShowContributorsError() {
    presenter.setView(mockView);
    presenter.onRetrieveContributorsError();
    verify(mockView).showContributorsError();
  }

  @Test
  public void givenAPresenter_WhenRetrieveIssuesSuccess_ThenShouldHideIssuesProgressbar() {
    presenter.setView(mockView);
    presenter.onRetrieveIssuesSuccess(new Issue[0]);
    verify(mockView).hideIssuesProgressbar();
  }

  @Test
  public void givenAPresenter_WhenRetrieveIssuesSuccess_ThenShouldShowIssues() {
    Issue[] issues = new Issue[0];
    presenter.setView(mockView);
    presenter.onRetrieveIssuesSuccess(issues);
    verify(mockView).showIssues(eq(issues));
  }

  @Test
  public void givenAPresenter_WhenRetrieveIssuesError_ThenShouldHideIssuesProgressbar() {
    presenter.setView(mockView);
    presenter.onRetrieveIssuesError();
    verify(mockView).hideIssuesProgressbar();
  }

  @Test
  public void givenAPresenter_WhenRetrieveIssuesError_ThenShouldShowIssuesError() {
    presenter.setView(mockView);
    presenter.onRetrieveIssuesError();
    verify(mockView).showIssuesError();
  }

}
