package net.franguti.githubsampleapp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import net.franguti.githubsampleapp.R;
import net.franguti.githubsampleapp.entities.Issue;
import net.franguti.githubsampleapp.entities.Repository;
import net.franguti.githubsampleapp.entities.User;
import net.franguti.githubsampleapp.ui.UIModule;
import net.franguti.githubsampleapp.ui.presenters.RepositoryDetailPresenter;
import net.franguti.githubsampleapp.ui.presenters.RepositoryDetailView;
import net.franguti.githubsampleapp.ui.views.ContributorItemView;
import net.franguti.githubsampleapp.ui.views.IssueItemView;
import net.franguti.githubsampleapp.ui.views.ProgressBarView;
import net.franguti.githubsampleapp.utils.FontManager;
import net.franguti.githubsampleapp.utils.Utils;
import org.parceler.Parcel;
import org.parceler.Parcels;

public class RepositoryDetailActivity extends BaseActivity implements RepositoryDetailView {

  public static final String EXTRA_REPOSITORY = "EXTRA_REPOSITORY";

  @Inject RepositoryDetailPresenter presenter;
  @Inject FontManager fontManager;
  @Inject LayoutInflater layoutInflater;

  @Bind(android.R.id.content) View content;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.owner_avatar) ImageView ownerAvatarImageView;
  @Bind(R.id.description_textview) TextView descriptionTextView;
  @Bind(R.id.contributors_progress_barview) ProgressBarView contributorsProgressbar;
  @Bind(R.id.issues_progress_barview) ProgressBarView issuesProgressbar;
  @Bind(R.id.contributors_container) ViewGroup contributorsContainer;
  @Bind(R.id.issues_container) ViewGroup issuesContainer;

  public static void start(Context activityContext, Repository repository) {
    Intent intent = new Intent(activityContext, RepositoryDetailActivity.class);
    Parcelable parcelable = Parcels.wrap(repository);
    intent.putExtra(EXTRA_REPOSITORY, parcelable);
    activityContext.startActivity(intent);
  }

  @Override protected int getActivityLayout() {
    return R.layout.activity_repository_detail;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.setView(this);
    initialiseToolbar();
    initialiseFont();
  }

  private void initialiseToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(getString(R.string.repository_list_search_hint));
  }

  private void initialiseFont() {
    fontManager.setFont(content);
  }

  @Override protected void onStart() {
    super.onStart();
    presenter.start(getRepository());
  }

  @Override protected List<Object> getModules() {
    List<Object> modules = new ArrayList<>();
    modules.add(new UIModule());
    return modules;
  }

  @OnClick(R.id.share_fab) public void onShareFabClick(View v) {
    presenter.performShareRepository(getString(R.string.repository_detail_share_text));
  }

  public Repository getRepository() {
    Parcelable parcelable = getIntent().getExtras().getParcelable(EXTRA_REPOSITORY);
    return Parcels.unwrap(parcelable);
  }

  @Override public void showContributorsProgressbar() {
    contributorsProgressbar.setVisibility(View.VISIBLE);
    contributorsProgressbar.start();
  }

  @Override public void hideContributorsProgressbar() {
    contributorsProgressbar.stop();
    contributorsProgressbar.setVisibility(View.GONE);
  }

  @Override public void showIssuesProgressbar() {
    issuesProgressbar.setVisibility(View.VISIBLE);
    issuesProgressbar.start();
  }

  @Override public void hideIssuesProgressbar() {
    issuesProgressbar.stop();
    issuesProgressbar.setVisibility(View.GONE);
  }

  @Override public void showRepositoryDetailInfo(Repository repository) {
    getSupportActionBar().setTitle(repository.getName());
    Picasso.with(this).load(repository.getOwner().getAvatarUrl()).into(ownerAvatarImageView);
    descriptionTextView.setText(repository.getDescription());
  }

  @Override public void showContributors(User[] contributors) {
    contributorsContainer.removeAllViews();
    ContributorItemView contributorItemView;
    for (User contributor : contributors) {
      contributorItemView =
          (ContributorItemView) layoutInflater.inflate(R.layout.list_item_contributor, null);
      contributorItemView.bindData(contributor);
      contributorsContainer.addView(contributorItemView);
    }
  }

  @Override public void showIssues(Issue[] issues) {
    issuesContainer.removeAllViews();
    IssueItemView issueItemView;
    for (Issue issue : issues) {
      issueItemView = (IssueItemView) layoutInflater.inflate(R.layout.list_item_issue, null);
      issueItemView.bindData(issue);
      issuesContainer.addView(issueItemView);
    }
  }

    @Override public void showIssuesError() {
    Snackbar.make(content, R.string.repository_detail_issues_error, Snackbar.LENGTH_LONG).show();
  }

  @Override public void showContributorsError() {
    Snackbar.make(content, R.string.repository_detail_contributors_error, Snackbar.LENGTH_LONG)
        .show();
  }
}
