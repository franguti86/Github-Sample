package net.franguti.githubsampleapp.entities;

import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;

@Parcel
public class User {

  @SerializedName("login") String login;
  @SerializedName("id") long id;
  @SerializedName("avatar_url") String avatarUrl;
  @SerializedName("contributions") int contributions;

  public User() { }

  public User(String login, long id, String avatarUrl, int contributions) {
    this.login = login;
    this.id = id;
    this.avatarUrl = avatarUrl;
    this.contributions = contributions;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public int getContributions() {
    return contributions;
  }

  public void setContributions(int contributions) {
    this.contributions = contributions;
  }
}
