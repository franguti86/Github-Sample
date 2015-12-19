package net.franguti.githubsampleapp.ui.presenters;

import javax.inject.Inject;
import net.franguti.githubsampleapp.domain.interactors.ContributorsInteractor;
import net.franguti.githubsampleapp.domain.interactors.IssuesInteractor;
import net.franguti.githubsampleapp.entities.Issue;
import net.franguti.githubsampleapp.entities.Repository;
import net.franguti.githubsampleapp.entities.User;
import net.franguti.githubsampleapp.ui.Navigator;

public class RepositoryDetailPresenter implements ContributorsInteractor.Callback, IssuesInteractor.Callback {

  private final ContributorsInteractor contributorsInteractor;
  private final IssuesInteractor issuesInteractor;
  private final Navigator navigator;
  private RepositoryDetailView view;
  private Repository repository;
  private User[] contributors;
  private Issue[] issues;

  @Inject public RepositoryDetailPresenter(ContributorsInteractor contributorsInteractor,
      IssuesInteractor issuesInteractor, Navigator navigator) {
    this.contributorsInteractor = contributorsInteractor;
    this.issuesInteractor = issuesInteractor;
    this.navigator = navigator;
  }

  public void setView(RepositoryDetailView view) {
    this.view = view;
  }

  public void start(Repository repository) {
    this.repository = repository;
    view.showRepositoryDetailInfo(repository);
    if (contributors == null) {
      view.showContributorsProgressbar();
      contributorsInteractor.retrieveThreeTopContributorsByRepository(repository, this);
    }
    if (issues == null) {
      view.showIssuesProgressbar();
      issuesInteractor.retrieveThreeNewestIssuesByRepository(repository, this);
    }
  }

  public void performShareRepository(String shareText) {
    String content = String.format("%s: %s", shareText, repository.getHtmlUrl());
    navigator.shareContent(content);
  }

  @Override public void onRetrieveIssuesSuccess(Issue[] issues) {
    this.issues = issues;
    view.hideIssuesProgressbar();
    view.showIssues(issues);
  }

  @Override public void onRetrieveIssuesError() {
    view.hideIssuesProgressbar();
    view.showIssuesError();
  }

  @Override public void onRetrieveContributorsSuccess(User[] contributors) {
    this.contributors = contributors;
    view.hideContributorsProgressbar();
    view.showContributors(contributors);
  }

  @Override public void onRetrieveContributorsError() {
    view.hideContributorsProgressbar();
    view.showContributorsError();
  }

}
