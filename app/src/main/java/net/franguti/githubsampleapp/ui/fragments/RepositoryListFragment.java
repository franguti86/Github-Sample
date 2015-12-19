package net.franguti.githubsampleapp.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import javax.inject.Inject;
import net.franguti.githubsampleapp.R;
import net.franguti.githubsampleapp.entities.Repository;
import net.franguti.githubsampleapp.ui.adapters.RepositoryRecyclerViewAdapter;
import net.franguti.githubsampleapp.ui.presenters.RepositoryListPresenter;
import net.franguti.githubsampleapp.ui.presenters.RepositoryListView;
import net.franguti.githubsampleapp.ui.views.ProgressBarView;
import net.franguti.githubsampleapp.utils.FontManager;
import net.franguti.githubsampleapp.utils.Utils;

public class RepositoryListFragment extends BaseFragment implements RepositoryListView {

  @Inject RepositoryListPresenter presenter;
  @Inject RepositoryRecyclerViewAdapter repositoryRecyclerViewAdapter;
  @Inject FontManager fontManager;

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.repository_recyclerview) RecyclerView repositoryRecyclerView;
  @Bind(R.id.search_edittext) EditText searchLanguageEdittext;
  @Bind(R.id.progress_bar_view) ProgressBarView progressBarView;

  @Override protected int getFragmentLayout() {
    return R.layout.fragment_repository_list;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    presenter.setView(this);
    initialiseToolbar();
    initialiseFont();
    initialiseRecyclerView();
    initialiseSearchLanguageEdittext();
  }

  @Override public void onStart() {
    super.onStart();
    presenter.start();
  }

  private void initialiseToolbar() {
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.repository_list_toolbar_title);
  }

  private void initialiseFont() {
    fontManager.setFont(getView());
  }

  private void initialiseRecyclerView() {
    repositoryRecyclerView.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager =
        new LinearLayoutManager(getActivity().getApplicationContext());
    repositoryRecyclerView.setLayoutManager(layoutManager);
    repositoryRecyclerView.setAdapter(repositoryRecyclerViewAdapter);
  }

  private void initialiseSearchLanguageEdittext() {
    searchLanguageEdittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          presenter.performSearchRepository(v.getText().toString());
          return true;
        }
        return false;
      }
    });
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    return super.onOptionsItemSelected(item);
  }

  @Override public void showRepositories(Repository[] repositories) {
    repositoryRecyclerViewAdapter.setRepositories(repositories);
  }

  @OnClick(R.id.search_fab) public void onSearchFabClick(View v) {
    presenter.performShowSearchField();
  }

  @Override public void showProgressBar() {
    progressBarView.setVisibility(View.VISIBLE);
    progressBarView.start();
  }

  @Override public void hideProgressBar() {
    progressBarView.stop();
    progressBarView.setVisibility(View.GONE);
  }

  @Override public void showSearchLanguageField() {
    searchLanguageEdittext.setVisibility(View.VISIBLE);
    searchLanguageEdittext.requestFocus();
  }

  @Override public void hideSearchLanguageField() {
    searchLanguageEdittext.setVisibility(View.GONE);
    Utils.hideSoftKeyboard(getActivity());
  }

  @Override public void showSearchError() {
    Snackbar.make(getView(), getString(R.string.repository_list_error_search), Snackbar.LENGTH_LONG)
        .show();
  }

  @Override public boolean isSearchLanguageFieldDisplayed() {
    return searchLanguageEdittext.getVisibility() == View.VISIBLE;
  }
}
