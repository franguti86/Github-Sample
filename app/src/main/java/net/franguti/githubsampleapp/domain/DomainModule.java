package net.franguti.githubsampleapp.domain;

import dagger.Module;
import dagger.Provides;
import net.franguti.githubsampleapp.api.ApiModule;
import net.franguti.githubsampleapp.domain.interactors.ContributorsInteractor;
import net.franguti.githubsampleapp.domain.interactors.ContributorsInteractorImpl;
import net.franguti.githubsampleapp.domain.interactors.IssuesInteractor;
import net.franguti.githubsampleapp.domain.interactors.IssuesInteractorImpl;
import net.franguti.githubsampleapp.domain.interactors.SearchRepositoriesInteractor;
import net.franguti.githubsampleapp.domain.interactors.SearchRepositoriesInteractorImpl;

/**
 * Dagger module created to provide every domain dependencies as interactors.
 */
@Module(
    includes = ApiModule.class,
    library = true,
    complete = false) public class DomainModule {

  @Provides SearchRepositoriesInteractor provideSearchRepositoriesInteractor(
      SearchRepositoriesInteractorImpl searchRepositoriesInteractor) {
    return searchRepositoriesInteractor;
  }

  @Provides ContributorsInteractor provideContributorsInteractor(
      ContributorsInteractorImpl contributorsInteractor) {
    return contributorsInteractor;
  }

  @Provides IssuesInteractor provideIssuesInteractor(IssuesInteractorImpl issuesInteractor) {
    return issuesInteractor;
  }
}
