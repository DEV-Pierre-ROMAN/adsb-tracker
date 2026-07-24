package com.pierreroman.adsb;

import java.util.Arrays;

/**
 * A frame respecting the format of the ADS-B.
 *
 * @param raw is the property that containing the bytes of the frames (14 bytes)
 */
public record Frame(byte[] raw) {

  /**
   * A Frame must have a raw of 14 bytes.
   *
   * @throws IllegalArgumentException if the raw length is not 14.
   */
  public Frame {
    if (raw.length != 14) {
      throw new IllegalArgumentException("A raw must be 14 bytes long, got " + raw.length);
    }

    raw = Arrays.copyOf(raw, raw.length);
  }

  @Override
  public byte[] raw() {
    return Arrays.copyOf(raw, raw.length);
  }

  public int df() {
    return (raw[0] & 0xFF) >> 3;
  }

}
