package net.franguti.githubsampleapp.dependencyinjection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * Annotation created to improve Context injection. This annotation is used with @Inject for
 * Context class to return the current Activity context.
 */
@Qualifier @Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
