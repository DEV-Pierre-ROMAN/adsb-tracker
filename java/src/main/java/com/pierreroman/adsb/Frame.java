package com.pierreroman.adsb;

import java.util.Arrays;

/**
 * A frame respecting the format of the ADS-B.
 *
 * @param raw is the array of bytes of the frames ({@value #FRAME_SIZE} bytes)
 */
public record Frame(byte[] raw) {
  private static final int FRAME_SIZE = 14;

  private static final String CALLSIGN_CHAR_TABLE =
      "?ABCDEFGHIJKLMNOPQRSTUVWXYZ????? ???????????????0123456789??????";
  private static final int CALLSIGN_CHAR_BITS_SIZE = 6;
  private static final int CALLSIGN_NUMBER_OF_CHARS = 8;
  private static final long CALLSIGN_MASK = 0x3FL;

  /**
   * A Frame must have a raw of {@value #FRAME_SIZE} bytes.
   *
   * @throws IllegalArgumentException if the raw length is not {@value #FRAME_SIZE}.
   */
  public Frame {
    if (raw.length != FRAME_SIZE) {
      throw new IllegalArgumentException(
          "A raw must be %d bytes long, got %d".formatted(FRAME_SIZE, raw.length)
      );
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

  public int ca() {
    return raw[0] & 0x07;
  }

  public int icao() {
    return ((raw[1] & 0xFF) << 16) | ((raw[2] & 0xFF) << 8) | (raw[3] & 0xFF);
  }

  public int typeCode() {
    return (raw[4] & 0xFF) >> 3;
  }

  /**
   * Extract and decode the callsign contained in the raw.
   *
   * @return the trimmed callsign with decoded symbol,
   *     the unknown symbols are returned as {@code ?}.
   */
  public String callsign() {

    long frameCallsign = ((raw[5] & 0xFFL) << 40) | ((raw[6] & 0xFFL) << 32) | ((raw[7] & 0xFFL) << 24)
        | ((raw[8] & 0xFFL) << 16)
        | ((raw[9] & 0xFFL) << 8)
        | (raw[10] & 0xFFL);
    StringBuilder callsignBuilder = new StringBuilder();

    for (int i = 0; i < CALLSIGN_NUMBER_OF_CHARS; i++) {

      final int shift = CALLSIGN_CHAR_BITS_SIZE * (CALLSIGN_NUMBER_OF_CHARS - 1 - i);
      final int index = (int) ((frameCallsign >> shift) & CALLSIGN_MASK);

      callsignBuilder.append(CALLSIGN_CHAR_TABLE.charAt(index));
    }

    return callsignBuilder.toString().trim();
  }
}
