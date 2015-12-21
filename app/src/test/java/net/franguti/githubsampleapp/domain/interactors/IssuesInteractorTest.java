package net.franguti.githubsampleapp.domain.interactors;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import dagger.ObjectGraph;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import net.franguti.githubsampleapp.FileUtils;
import net.franguti.githubsampleapp.domain.TestDomainModule;
import net.franguti.githubsampleapp.entities.Issue;
import net.franguti.githubsampleapp.entities.Repository;
import net.franguti.githubsampleapp.entities.User;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class IssuesInteractorTest {

  @Inject IssuesInteractor issuesInteractor;

  private MockWebServer mockWebServer;

  @Before public void setUp() throws IOException {
    mockWebServer = new MockWebServer();
    mockWebServer.start();
    String mockWebServerUrl = mockWebServer.getUrl("/").toString();

    ObjectGraph objectGraph = ObjectGraph.create(new TestDomainModule(mockWebServerUrl));
    objectGraph.inject(this);
  }

  @Test
  public void testConfigure() throws IOException {
    File file = FileUtils.getFileFromPath(this, "./issues.json");
    String searchJson = FileUtils.readFileAsString(file);
    assertThat("Assertion error reading file!", searchJson != null && searchJson.length() > 0);
  }

  @Test public void givenARightJson_WhenRetrieveIssues_ThenShouldReturnIssues() throws IOException {
    File file = FileUtils.getFileFromPath(this, "./issues.json");
    String searchJson = FileUtils.readFileAsString(file);
    mockWebServer.enqueue(new MockResponse().setBody(searchJson));

    User owner = new User();
    owner.setLogin("owner");
    Repository repository = new Repository();
    repository.setName("test");
    repository.setOwner(owner);
    issuesInteractor.retrieveThreeNewestIssuesByRepository(repository,
        new IssuesInteractor.Callback() {
          @Override public void onRetrieveIssuesSuccess(Issue[] issues) {
            assertThat("Assertion fail, no issues result!", issues.length > 0);
          }

          @Override public void onRetrieveIssuesError() {
            fail("Fail retrieving contributors!");
          }
        });
  }

  @Test public void givenAWrongJson_WhenRetrieveIssues_ThenShouldCallError() throws IOException {
    mockWebServer.enqueue(new MockResponse().setBody("[,"));

    User owner = new User();
    owner.setLogin("owner");
    Repository repository = new Repository();
    repository.setName("test");
    repository.setOwner(owner);
    IssuesInteractor.Callback mockCallback = mock(IssuesInteractor.Callback.class);
    issuesInteractor.retrieveThreeNewestIssuesByRepository(repository, mockCallback);
    verify(mockCallback).onRetrieveIssuesError();
    verifyNoMoreInteractions(mockCallback);
  }

}
