package net.franguti.githubsampleapp.ui;

import dagger.Module;
import dagger.Provides;
import net.franguti.githubsampleapp.domain.DomainModule;
import net.franguti.githubsampleapp.domain.interactors.ContributorsInteractor;
import net.franguti.githubsampleapp.domain.interactors.ContributorsInteractorImpl;
import net.franguti.githubsampleapp.domain.interactors.IssuesInteractor;
import net.franguti.githubsampleapp.domain.interactors.IssuesInteractorImpl;
import net.franguti.githubsampleapp.domain.interactors.SearchRepositoriesInteractor;
import net.franguti.githubsampleapp.domain.interactors.SearchRepositoriesInteractorImpl;
import net.franguti.githubsampleapp.ui.presenters.RepositoryDetailPresenterTest;
import net.franguti.githubsampleapp.ui.presenters.RepositoryListPresenterTest;

import static org.mockito.Mockito.mock;

/**
 * Dagger module for unit testing on UI layer (presenters).
 */
@Module(
    includes = {
        DomainModule.class
    },
    injects = {
        RepositoryListPresenterTest.class, RepositoryDetailPresenterTest.class
    },
    overrides = true,
    library = true
)
public class TestUIModule {

  @Provides SearchRepositoriesInteractor provideSearchRepositoriesInteractor(
      SearchRepositoriesInteractorImpl searchRepositoriesInteractor) {
    return mock(SearchRepositoriesInteractor.class);
  }

  @Provides ContributorsInteractor provideContributorsInteractor(
      ContributorsInteractorImpl contributorsInteractor) {
    return mock(ContributorsInteractor.class);
  }

  @Provides IssuesInteractor provideIssuesInteractor(IssuesInteractorImpl issuesInteractor) {
    return mock(IssuesInteractor.class);
  }
}
