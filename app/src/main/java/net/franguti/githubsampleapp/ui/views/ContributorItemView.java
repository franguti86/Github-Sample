package net.franguti.githubsampleapp.ui.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import net.franguti.githubsampleapp.R;
import net.franguti.githubsampleapp.entities.User;

public class ContributorItemView extends CardView {

  @Bind(R.id.avatar_circleimageview) CircleImageView avatarCircleImageView;
  @Bind(R.id.owner_name_textview) TextView ownerNameTextView;
  @Bind(R.id.contributions_textview) TextView contributionsTextView;

  public ContributorItemView(Context context) {
    super(context);
  }

  public ContributorItemView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ContributorItemView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this, this);
  }

  public void bindData(User contributor) {
    Picasso.with(getContext())
        .load(contributor.getAvatarUrl())
        .into(avatarCircleImageView);
    ownerNameTextView.setText(contributor.getLogin());
    contributionsTextView.setText(String.valueOf(contributor.getContributions()));
  }
}
