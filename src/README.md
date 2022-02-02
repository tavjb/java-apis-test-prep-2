## Fake Download Task

Write a program that mocks downloading files.
Allow 5 actions to be performed:

1. Download new file
2. Stop download
3. Restart download
4. View all downloads
5. Exit

Each download will have:
```
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
  }

  public int getProgress() {
  }

  public void stop() {
  }

  public void start() {
  }

  public boolean isExpired() {
  }
```

The download will be in a random speed with a random frequency
For example:
-- wait 1.4 seconds --
add 8000.54
-- wait 0.2 seconds --
add 40.2
etc..


Additionally, make a task that runs every 20 seconds and removes
all finished downloads
