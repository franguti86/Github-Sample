package net.franguti.githubsampleapp.ui.activities;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import net.franguti.githubsampleapp.R;
import net.franguti.githubsampleapp.ui.UIModule;

public class RepositoryListActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repository_list);
  }

  @Override protected List<Object> getModules() {
    List<Object> modules = new ArrayList<>();
    modules.add(new UIModule());
    return modules;
  }

}
