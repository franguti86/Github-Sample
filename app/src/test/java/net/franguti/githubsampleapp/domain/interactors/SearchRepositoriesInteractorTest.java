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
import net.franguti.githubsampleapp.entities.SearchResult;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchRepositoriesInteractorTest {

  @Inject SearchRepositoriesInteractor searchRepositoriesInteractor;

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
    File file = FileUtils.getFileFromPath(this, "./search_repositories.json");
    String searchJson = FileUtils.readFileAsString(file);
    assertThat("Assertion error reading file!", searchJson != null && searchJson.length() > 0);
  }

  @Test(timeout = 100) public void givenARightJson_WhenSearchRepositories_ThenShouldReturnSearchResult() throws IOException {
    File file = FileUtils.getFileFromPath(this, "./search_repositories.json");
    String searchJson = FileUtils.readFileAsString(file);
    mockWebServer.enqueue(new MockResponse().setBody(searchJson));

    final AtomicBoolean testDone = new AtomicBoolean(false);
    searchRepositoriesInteractor.searchPopularRepositoriesByLanguage("java",
        new SearchRepositoriesInteractor.Callback() {
          @Override public void onSearchRepositoriesSuccess(SearchResult searchResult) {
            assertThat("Assertion fail, no search result!",
                searchResult.getRepositories().length > 0);
            testDone.set(true);
          }

          @Override public void onSearchRepositoriesError() {
            fail("Fail on searching!");
          }
        });
    while(!testDone.get());
  }

}
