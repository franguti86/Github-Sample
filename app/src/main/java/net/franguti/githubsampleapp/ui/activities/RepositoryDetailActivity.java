package net.franguti.githubsampleapp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
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
import net.franguti.githubsampleapp.utils.FontManager;
import org.parceler.Parcel;
import org.parceler.Parcels;

public class RepositoryDetailActivity extends BaseActivity implements RepositoryDetailView {

  public static final String EXTRA_REPOSITORY = "EXTRA_REPOSITORY";

  @Inject RepositoryDetailPresenter presenter;
  @Inject FontManager fontManager;

  @Bind(android.R.id.content) View content;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.owner_avatar) ImageView ownerAvatarImageView;
  @Bind(R.id.description_textview) TextView descriptionTextView;

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

  @OnClick(R.id.share_fab)
  public void onShareFabClick(View v) {
    presenter.performShareRepository(getString(R.string.repository_detail_share_text));
  }

  public Repository getRepository() {
    Parcelable parcelable = getIntent().getExtras().getParcelable(EXTRA_REPOSITORY);
    return Parcels.unwrap(parcelable);
  }

  @Override public void showContributorsProgressbar() {

  }

  @Override public void hideContributorsProgressbar() {

  }

  @Override public void showIssuesProgressbar() {

  }

  @Override public void hideIssuesProgressbar() {

  }

  @Override public void showRepositoryDetailInfo(Repository repository) {
    getSupportActionBar().setTitle(repository.getName());
    Picasso.with(this).load(repository.getOwner().getAvatarUrl()).into(ownerAvatarImageView);
    descriptionTextView.setText(repository.getDescription());
  }

  @Override public void showContributors(User[] contributors) {

  }

  @Override public void showIssues(Issue[] issues) {

  }

  @Override public void showIssuesError() {
    Snackbar.make(content, R.string.repository_detail_issues_error, Snackbar.LENGTH_LONG).show();
  }

  @Override public void showContributorsError() {
    Snackbar.make(content, R.string.repository_detail_contributors_error, Snackbar.LENGTH_LONG).show();
  }
}
