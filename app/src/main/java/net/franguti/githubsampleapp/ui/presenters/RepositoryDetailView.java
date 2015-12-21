package net.franguti.githubsampleapp.ui.presenters;

import net.franguti.githubsampleapp.entities.Issue;
import net.franguti.githubsampleapp.entities.Repository;
import net.franguti.githubsampleapp.entities.User;

public interface RepositoryDetailView {

  void showContributorsProgressbar();
  void hideContributorsProgressbar();
  void showIssuesProgressbar();
  void hideIssuesProgressbar();
  void showRepositoryDetailInfo(Repository repository);
  void showContributors(User[] contributors);
  void showIssues(Issue[] issues);
  void showIssuesError();
  void showContributorsError();

}
