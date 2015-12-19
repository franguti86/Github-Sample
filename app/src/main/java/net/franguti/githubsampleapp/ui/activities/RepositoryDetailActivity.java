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
import java.util.ArrayList;
import java.util.List;
import net.franguti.githubsampleapp.R;
import net.franguti.githubsampleapp.entities.Repository;
import net.franguti.githubsampleapp.ui.UIModule;
import org.parceler.Parcel;
import org.parceler.Parcels;

public class RepositoryDetailActivity extends BaseActivity {

  public static final String EXTRA_REPOSITORY = "EXTRA_REPOSITORY";

  public static void start(Context activityContext, Repository repository) {
    Intent intent = new Intent(activityContext, RepositoryDetailActivity.class);
    Parcelable parcelable = Parcels.wrap(repository);
    intent.putExtra(EXTRA_REPOSITORY, parcelable);
    activityContext.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });
  }

  @Override protected int getActivityLayout() {
    return R.layout.activity_repository_detail;
  }

  @Override protected List<Object> getModules() {
    List<Object> modules = new ArrayList<>();
    modules.add(new UIModule());
    return modules;
  }

  public Repository getRepository() {
    Parcelable parcelable = getIntent().getExtras().getParcelable(EXTRA_REPOSITORY);
    return Parcels.unwrap(parcelable);
  }

}
