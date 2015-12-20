package net.franguti.githubsampleapp.ui.presenters;

import dagger.ObjectGraph;
import java.io.IOException;
import net.franguti.githubsampleapp.ui.TestUIModule;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class RepositoryDetailPresenterTest {

  @Before public void setUp() throws IOException {
    ObjectGraph objectGraph = ObjectGraph.create(new TestUIModule());
    objectGraph.inject(this);
  }

  @Test
  public void given_When_Then() {
    assertThat("Testing", true);
  }

}
