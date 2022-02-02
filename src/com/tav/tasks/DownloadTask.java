package com.tav.tasks;

import com.tav.beans.FakeDownload;
import lombok.AllArgsConstructor;

import java.util.Calendar;

import static com.tav.NetworkUtil.*;

@AllArgsConstructor
public class DownloadTask implements Runnable {
  private final FakeDownload download;

  @Override
  public void run() {
    String initWord;
    if (download.getDataFetched() == 0) {
      initWord = "Starting";
    } else {
      initWord = "Resuming";
    }
    System.out.println(initWord + " download of " + download.getFileName() + " from " + download.getUrl());

    while (download.isActive()) {
      try {
        Thread.sleep(getCurrentFrequency());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }


      download.addData( getCurrentSpeed());

      if (download.getProgress() >= 100) {
        System.out.println("Finished downloading " + download.getFileName());
        download.setFinishedAt(Calendar.getInstance());
        download.stop();
        return;
      }
    }

    System.out.println("Stopped downloading " + download.getFileName());
  }
}
