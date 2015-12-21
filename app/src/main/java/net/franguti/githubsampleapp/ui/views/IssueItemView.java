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
import net.franguti.githubsampleapp.entities.Issue;

public class IssueItemView extends CardView {

  @Bind(R.id.avatar_circleimageview) CircleImageView avatarCircleImageView;
  @Bind(R.id.owner_name_textview) TextView ownerNameTextView;
  @Bind(R.id.issue_title_textview) TextView issueTitleTextView;
  @Bind(R.id.issue_description_textview) TextView issueDescriptionTextView;
  @Bind(R.id.state_textview) TextView stateTextView;

  public IssueItemView(Context context) {
    super(context);
  }

  public IssueItemView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public IssueItemView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this, this);
  }

  public void bindData(Issue issue) {
    Picasso.with(getContext())
        .load(issue.getUser().getAvatarUrl())
        .into(avatarCircleImageView);
    ownerNameTextView.setText(issue.getUser().getLogin());
    issueTitleTextView.setText(issue.getTitle());
    issueDescriptionTextView.setText(issue.getBody());
    stateTextView.setText(issue.getState());
  }
}
