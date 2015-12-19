package net.franguti.githubsampleapp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import net.franguti.githubsampleapp.R;
import net.franguti.githubsampleapp.ui.UIModule;

public class RepositoryListActivity extends BaseActivity {

  public static void start(Context context) {
    Intent intent = new Intent(context, RepositoryListActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected int getActivityLayout() {
    return R.layout.activity_repository_list;
  }

  @Override protected List<Object> getModules() {
    List<Object> modules = new ArrayList<>();
    modules.add(new UIModule());
    return modules;
  }

}
