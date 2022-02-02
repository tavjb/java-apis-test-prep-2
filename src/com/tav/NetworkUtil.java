package com.tav;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NetworkUtil {
  private static final double MAX_SPEED = 1000;
  private static final int MAX_FREQ = 3000;

  public static double getCurrentSpeed() {
    return new Random().nextDouble(MAX_SPEED);
  }

  public static int getCurrentFrequency() {
    return new Random().nextInt(MAX_FREQ);
  }
}
