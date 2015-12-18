package net.franguti.githubsampleapp.ui;

import dagger.Module;
import net.franguti.githubsampleapp.ui.activities.MainActivity;

/**
 * Dagger module for the UI layer.
 */
@Module(
    injects = {
        MainActivity.class
    },
    complete = false)
public class UIModule {

}
