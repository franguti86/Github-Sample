package net.franguti.githubsampleapp.entities;

import com.google.gson.annotations.SerializedName;

public class SearchResult {

  @SerializedName("total_count") private int totalCount;
  @SerializedName("incomplete_results") private boolean incompleteResults;
  @SerializedName("items") private Repository[] repositories;

  public SearchResult() { }

  public SearchResult(int totalCount, boolean incompleteResults, Repository[] repositories) {
    this.totalCount = totalCount;
    this.incompleteResults = incompleteResults;
    this.repositories = repositories;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public boolean isIncompleteResults() {
    return incompleteResults;
  }

  public void setIncompleteResults(boolean incompleteResults) {
    this.incompleteResults = incompleteResults;
  }

  public Repository[] getRepositories() {
    return repositories;
  }

  public void setRepositories(Repository[] repositories) {
    this.repositories = repositories;
  }
}
