package com.sakecfest.shahandanchor.ashish.pratishtha.Model;

public class ScheduleModel {

  private String name, time, link;

  public ScheduleModel(String name, String time, String link) {
    this.name = name;
    this.time = time;
    this.link = link;
  }

  public String getName() {
    return name;
  }

  public String getTime() {
    return time;
  }

  public String getLink() {
    return link;
  }
}
