package net.franguti.githubsampleapp.domain;

import dagger.Module;
import net.franguti.githubsampleapp.api.ApiModule;

/**
 * Dagger module created to provide every domain dependencies as interactors.
 */
@Module(
    includes = ApiModule.class,
    library = true,
    complete = false
)
public class DomainModule {

}
