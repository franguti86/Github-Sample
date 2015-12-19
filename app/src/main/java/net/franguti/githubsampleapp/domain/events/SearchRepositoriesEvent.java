package net.franguti.githubsampleapp.domain.events;

import net.franguti.githubsampleapp.entities.SearchResult;

public class SearchRepositoriesEvent {

  private boolean error = false;
  private final SearchResult searchResult;

  public SearchRepositoriesEvent(boolean error) {
    this(error, null);
  }

  public SearchRepositoriesEvent(SearchResult searchResult) {
    this(false, searchResult);
  }

  public SearchRepositoriesEvent(boolean error, SearchResult searchResult) {
    this.error = error;
    this.searchResult = searchResult;
  }

  public boolean isError() {
    return error;
  }

  public SearchResult getSearchResult() {
    return searchResult;
  }
}
