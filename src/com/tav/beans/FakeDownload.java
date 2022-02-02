package com.tav.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;

public class FakeDownload {
  public FakeDownload(String fileName, String url, double size) {
    this.fileName = fileName;
    this.url = url;
    this.size = size;
    this.isActive = true;
  }

  @Getter
  private final String fileName;
  @Getter
  private final String url;
  @Getter
  private final double size;
  @Getter
  private double dataFetched;
  @Getter
  private boolean isActive;
  @Getter
  @Setter
  private Calendar finishedAt;


  public void addData(double data) {
    this.dataFetched += data;
    if (this.dataFetched > size) {
      this.dataFetched = size;
    }
  }

  public int getProgress() {
    return (int) ((dataFetched / size) * 100);
  }

  public void stop() {
    isActive = false;
  }

  public void start() {
    isActive = true;
  }

  public boolean isExpired() {
    if (finishedAt != null) {
      return finishedAt.before(Calendar.getInstance());
    }
    return false;
  }

  @Override
  public String toString() {
    return "FakeDownload{" +
        "fileName='" + fileName + '\'' +
        ", url='" + url + '\'' +
        ", size=" + size +
        ", dataFetched=" + dataFetched +
        ", isActive=" + isActive +
        ", finishedAt=" + finishedAt +
        ", PROGRESS=" + getProgress() + "%" +
        '}';
  }
}
