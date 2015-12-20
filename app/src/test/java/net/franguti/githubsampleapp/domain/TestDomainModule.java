package net.franguti.githubsampleapp.domain;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.Executor;
import net.franguti.githubsampleapp.dependencyinjection.Named;
import net.franguti.githubsampleapp.domain.interactors.ContributorsInteractorTest;
import net.franguti.githubsampleapp.domain.interactors.IssuesInteractorTest;
import net.franguti.githubsampleapp.domain.interactors.SearchRepositoriesInteractorTest;
import retrofit.RestAdapter;
import retrofit.client.Client;

/**
 * Dagger module for unit testing on domain layer.
 */
@Module(
    includes = {
        DomainModule.class
    },
    injects = {
        SearchRepositoriesInteractorTest.class, ContributorsInteractorTest.class,
        IssuesInteractorTest.class
    },
    overrides = true) public class TestDomainModule {

  private String githubEndpoint;

  public TestDomainModule(String githubEndpoint) {
    this.githubEndpoint = githubEndpoint;
  }

  @Provides @Named("github_endpoint") String provideGithubEndpoint() {
    return githubEndpoint;
  }

  @Provides RestAdapter provideRestAdapter(Client client,
      @Named("github_endpoint") String endpoint) {
    // Using synchronous executor to test interactors
    SynchronousExecutor synchronous = new SynchronousExecutor();
    return new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
        .setClient(client)
        .setEndpoint(endpoint)
        .setExecutors(synchronous, synchronous)
        .build();
  }

  private static final class SynchronousExecutor implements Executor {
    @Override public void execute(Runnable r) {
      r.run();
    }
  }
}
