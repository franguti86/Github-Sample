package net.franguti.githubsampleapp.domain.interactors;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import dagger.ObjectGraph;
import java.io.File;
import java.io.IOException;
import javax.inject.Inject;
import net.franguti.githubsampleapp.FileUtils;
import net.franguti.githubsampleapp.domain.TestDomainModule;
import net.franguti.githubsampleapp.entities.SearchResult;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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

  @Test public void givenARightJson_WhenSearchRepositories_ThenShouldReturnSearchResult() throws IOException {
    File file = FileUtils.getFileFromPath(this, "./search_repositories.json");
    String searchJson = FileUtils.readFileAsString(file);
    mockWebServer.enqueue(new MockResponse().setBody(searchJson));

    searchRepositoriesInteractor.searchPopularRepositoriesByLanguage("java",
        new SearchRepositoriesInteractor.Callback() {
          @Override public void onSearchRepositoriesSuccess(SearchResult searchResult) {
            assertThat("Assertion fail, no search result!",
                searchResult.getRepositories().length > 0);
          }

          @Override public void onSearchRepositoriesError() {
            fail("Fail on searching!");
          }
        });
  }

  @Test public void givenAWrongJson_WhenSearchRepositories_ThenShouldCallError() throws IOException {
    mockWebServer.enqueue(new MockResponse().setBody("[,"));

    SearchRepositoriesInteractor.Callback mockCallback = mock(SearchRepositoriesInteractor.Callback.class);
    searchRepositoriesInteractor.searchPopularRepositoriesByLanguage("java", mockCallback);
    verify(mockCallback).onSearchRepositoriesError();
    verifyNoMoreInteractions(mockCallback);
  }

}
