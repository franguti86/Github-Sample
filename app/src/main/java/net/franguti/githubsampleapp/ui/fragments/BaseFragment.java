package net.franguti.githubsampleapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import net.franguti.githubsampleapp.ui.activities.BaseActivity;

public abstract class BaseFragment extends Fragment {

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    injectDependencies();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(getFragmentLayout(), container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    injectViews(view);
  }

  /**
   * Every fragment has to inflate a layout in the onCreateView method. We have added this method
   * to
   * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
   * inflate in this method when extends BaseFragment.
   */
  @LayoutRes
  protected abstract int getFragmentLayout();

  /**
   * Replace every field annotated using @Inject annotation with the provided dependency specified
   * inside a Dagger module value.
   */
  private void injectDependencies() {
    ((BaseActivity) getActivity()).inject(this);
  }

  /**
   * Replace every field annotated with ButterKnife annotations like @InjectView with the proper
   * value.
   *
   * @param view to extract each widget injected in the fragment.
   */
  private void injectViews(final View view) {
    ButterKnife.bind(this, view);
  }
}
