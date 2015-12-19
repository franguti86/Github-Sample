package net.franguti.githubsampleapp.domain;

import dagger.Module;
import dagger.Provides;
import net.franguti.githubsampleapp.dependencyinjection.Named;
import net.franguti.githubsampleapp.domain.interactors.SearchRepositoriesInteractorTest;

/**
 * Dagger module for unit testing on domain layer.
 */
@Module(
    includes = {
        DomainModule.class
    },
    injects = {
        SearchRepositoriesInteractorTest.class
    },
    overrides = true
)
public class TestDomainModule {

    private String githubEndpoint;

    public TestDomainModule(String githubEndpoint) {
        this.githubEndpoint = githubEndpoint;
    }

    @Provides @Named("github_endpoint") String provideGithubEndpoint() {
        return githubEndpoint;
    }

}
