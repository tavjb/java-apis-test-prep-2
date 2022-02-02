package com.tav;

import com.tav.beans.FakeDownload;
import com.tav.tasks.DownloadTask;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class DownloadSystem {
  public static final DownloadSystem instance = new DownloadSystem();

  private DownloadSystem() {
    this.isRunning = true;
    finishedDownloadKiller = new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(20000);
        } catch (InterruptedException e) {
          System.out.println("counter-killer stopped");
        }

        for (FakeDownload download : getDownloads()) {
          if (download.isExpired()) {
            downloads.remove(download);
          }
        }
      }
    });
    finishedDownloadKiller.start();
  }

  public void printMenu() {
    System.out.println("Please choose an action: 1 - download file, 2 - stop download, 3 - restart download, 4 - see all downloads, 5 - exit");
  }

  @Getter
  private final Set<FakeDownload> downloads = new HashSet<>();
  @Getter
  @Setter
  private boolean isRunning;
  private final Thread finishedDownloadKiller;


  public void downloadFile(final String fileName, final String url, final double size) {
    final var fd = new FakeDownload(fileName, url, size);
    new Thread(new DownloadTask(fd)).start();
    downloads.add(fd);
  }

  public void restartDownload(final String fileName, final String url) {
    downloads.forEach( fd -> {
        boolean isSearchedDownload = fd.getFileName().equals(fileName) && fd.getUrl().equals(url);
        if (!fd.isActive() && isSearchedDownload) {
          fd.start();
          new Thread(new DownloadTask(fd)).start();
        }
      }
    );
  }

  public void stopDownload(final String fileName, final String url) {
    downloads.forEach( fd -> {
          boolean isSearchedDownload = fd.getFileName().equals(fileName) && fd.getUrl().equals(url);
          if (fd.isActive() && isSearchedDownload) {
            fd.stop();
          }
        }
    );
  }

  public void kill() {
    finishedDownloadKiller.stop();
  }
}
