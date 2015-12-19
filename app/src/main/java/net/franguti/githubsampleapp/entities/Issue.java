package net.franguti.githubsampleapp.entities;

import com.google.gson.annotations.SerializedName;

public class Issue {

  @SerializedName("id") private long id;
  @SerializedName("title") private String title;
  @SerializedName("body") private String body;
  @SerializedName("state") private String state;
  @SerializedName("user") private User user;
  @SerializedName("assignee") private User assignee;

  public Issue() { }

  public Issue(long id, String title, String body, String state, User user, User assignee) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.state = state;
    this.user = user;
    this.assignee = assignee;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getAssignee() {
    return assignee;
  }

  public void setAssignee(User assignee) {
    this.assignee = assignee;
  }
}
