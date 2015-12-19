package net.franguti.githubsampleapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import javax.inject.Inject;
import net.franguti.githubsampleapp.R;
import net.franguti.githubsampleapp.entities.Repository;

public class RepositoryRecyclerViewAdapter extends RecyclerView.Adapter<RepositoryRecyclerViewAdapter.RepositoryViewHolder> {

  private final LayoutInflater layoutInflater;
  private Repository[] repositories = new Repository[0];

  @Inject public RepositoryRecyclerViewAdapter(LayoutInflater layoutInflater) {
    this.layoutInflater = layoutInflater;
  }

  @Override public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = layoutInflater.inflate(R.layout.list_item_repository, parent, false);
    RepositoryViewHolder holder = new RepositoryViewHolder(view);
    return holder;
  }

  @Override public void onBindViewHolder(RepositoryViewHolder holder, int position) {
    holder.bindData(repositories[position]);
  }

  @Override public int getItemCount() {
    return repositories.length;
  }

  public void setRepositories(Repository[] repositories) {
    this.repositories = repositories;
    notifyDataSetChanged();
  }

  static class RepositoryViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.repository_name_textview) TextView repositoryNameTextView;

    public RepositoryViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bindData(Repository repository) {
      repositoryNameTextView.setText(repository.getName());
    }

  }

}
