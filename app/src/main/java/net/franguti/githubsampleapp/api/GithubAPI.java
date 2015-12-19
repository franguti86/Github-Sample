package net.franguti.githubsampleapp.api;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import net.franguti.githubsampleapp.entities.Issue;
import net.franguti.githubsampleapp.entities.SearchResult;
import net.franguti.githubsampleapp.entities.User;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface GithubAPI {

  @StringDef({ SORT_REPO_STARS, SORT_REPO_FORKS, SORT_REPO_UPDATED })
  @Retention(RetentionPolicy.SOURCE)
  @interface SortRepo { }

  @StringDef({ ORDER_REPO_DESC, ORDER_REPO_ASC })
  @Retention(RetentionPolicy.SOURCE)
  @interface OrderRepo { }

  @StringDef({SORT_ISSUE_CREATED, SORT_ISSUE_UPDATED, SORT_ISSUE_COMMENTS})
  @Retention(RetentionPolicy.SOURCE)
  @interface SortIssue {}

  String SORT_REPO_STARS = "stars";
  String SORT_REPO_FORKS = "forks";
  String SORT_REPO_UPDATED = "updated";

  String ORDER_REPO_ASC = "asc";
  String ORDER_REPO_DESC = "desc";

  String SORT_ISSUE_CREATED = "created";
  String SORT_ISSUE_UPDATED = "updated";
  String SORT_ISSUE_COMMENTS = "comments";

  @GET("/search/repositories") void searchRepositories(@Query("q") String query,
      @Query("sort") @SortRepo String sor, @Query("order") @OrderRepo String order,
      Callback<SearchResult> callback);

  @GET("/repos/{owner}/{repo}/contributors") void retrieveContributorsForARepository(
      @Path("owner") String owner, @Path("repo") String repository, Callback<User[]> callback);

  @GET("/repos/{owner}/{repo}/issues") void retrieveIssuesForARepository(
      @Path("owner") String owner, @Path("repo") String repository, @Query("sort") @SortIssue  String sort,
      Callback<Issue[]> callback);
}
