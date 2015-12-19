package net.franguti.githubsampleapp.entities;

import com.google.gson.annotations.SerializedName;

public class Repository {

  @SerializedName("id") private long id;
  @SerializedName("name") private String name;
  @SerializedName("full_name") private String fullName;
  @SerializedName("description") private String description;
  @SerializedName("owner") User owner;

  public Repository() { }

  public Repository(long id, String name, String fullName, String description, User owner) {
    this.id = id;
    this.name = name;
    this.fullName = fullName;
    this.description = description;
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

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
}