package com.sakecfest.shahandanchor.ashish.pratishtha.Model;

public class EventModel {

  String image, title, id;

  public EventModel(String image, String title, String id) {
    this.image = image;
    this.title = title;
    this.id = id;
  }

  public String getImage() {
    return image;
  }

  public String getTitle() {
    return title;
  }

  public String getId() {
    return id;
  }
}
