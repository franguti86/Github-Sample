package net.franguti.githubsampleapp.entities;

import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;

@Parcel
public class Repository {

  @SerializedName("id") long id;
  @SerializedName("name") String name;
  @SerializedName("full_name") String fullName;
  @SerializedName("description") String description;
  @SerializedName("html_url") String htmlUrl;
  @SerializedName("owner") User owner;

  public Repository() { }

  public Repository(long id, String name, String fullName, String description, String htmlUrl, User owner) {
    this.id = id;
    this.name = name;
    this.fullName = fullName;
    this.description = description;
    this.htmlUrl = htmlUrl;
    this.owner = owner;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
}
