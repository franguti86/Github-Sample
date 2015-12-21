package net.franguti.githubsampleapp.ui;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import net.franguti.githubsampleapp.ui.activities.RepositoryDetailActivity;
import net.franguti.githubsampleapp.ui.activities.RepositoryListActivity;
import net.franguti.githubsampleapp.ui.activities.SplashActivity;
import net.franguti.githubsampleapp.ui.fragments.RepositoryListFragment;
import net.franguti.githubsampleapp.utils.FontManager;

/**
 * Dagger module for the UI layer.
 */
@Module(
    injects = {
        SplashActivity.class, RepositoryListActivity.class, RepositoryListFragment.class,
        RepositoryDetailActivity.class
    },
    complete = false)
public class UIModule {

    @Provides FontManager provideFontManager(Context context) {
        return new FontManager(context);
    }

}
