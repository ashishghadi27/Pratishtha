package com.sakecfest.shahandanchor.ashish.pratishtha.Model;

public class DayModel {
  private String days, id, event_id, image;

  public DayModel(String days, String id, String event_id, String image){
    this.days = days;
    this.id = id;
    this.event_id = event_id;
    this.image = image;
  }

  public String getDays() {
    return days;
  }

  public String getId() {
    return id;
  }

  public String getEvent_id() {
    return event_id;
  }

  public String getImage() {
    return image;
  }
}
