package com.tav;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class App {
  private static final double MAX_SIZE = 100000;

  public static void main(String[] args) throws IOException {
    while (DownloadSystem.instance.isRunning()) {
      DownloadSystem.instance.printMenu();
      final Scanner scanner = new Scanner(System.in);
      final int cmd = scanner.nextInt();
      switch (cmd) {
        case 1:
          initDownload();
          break;

        case 2:
          stopDownload();
          break;

        case 3:
          restartDownload();
          break;

        case 4:
          DownloadSystem.instance.getDownloads().forEach(System.out::println);
          break;

        default:
          DownloadSystem.instance.setRunning(false);
          DownloadSystem.instance.kill();
      }
    }
  }

  private static void restartDownload() {
    final Scanner scanner = new Scanner(System.in);
    System.out.print("What's the file name?");
    final String fileName = scanner.next();
    System.out.print("What's the file location? (url)");
    final String url = scanner.next();
    DownloadSystem.instance.restartDownload(fileName, url);
  }

  private static void stopDownload() {
    final Scanner scanner = new Scanner(System.in);
    System.out.print("What's the file name?");
    final String fileName = scanner.next();
    System.out.print("What's the file location? (url)");
    final String url = scanner.next();
    DownloadSystem.instance.stopDownload(fileName, url);
  }

  private static void initDownload() {
    final Scanner scanner = new Scanner(System.in);
    System.out.print("What's the file name?");
    final String fileName = scanner.next();
    System.out.print("What's the file location? (url)");
    final String url = scanner.next();
    final double size = new Random().nextDouble(MAX_SIZE);
    DownloadSystem.instance.downloadFile(fileName, url, size);
  }
}
