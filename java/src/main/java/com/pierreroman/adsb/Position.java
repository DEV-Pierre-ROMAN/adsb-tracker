package com.pierreroman.adsb;

/**
 * A geographic position expressed in decimal degrees.
 *
 * @param latitude the latitude of the position
 * @param longitude the longitude of the position
 */
public record Position(double latitude, double longitude) {
  private static final double MAX_LATITUDE = 90.0;
  private static final double MAX_LONGITUDE = 180.0;
  private static final double MIN_LATITUDE = -180.0;
  private static final double MIN_LONGITUDE = -90.0;

  /**
   * A Position latitude and longitude must respect interval value.
   *
   * @throws IllegalArgumentException if the coordinates are out of range.
   */
  public Position {
    if (latitude < MIN_LATITUDE
        || latitude > MAX_LATITUDE) {
      throw new IllegalArgumentException(
          "the latitude must be between -90 and 90, got "
              + latitude
      );
    }

    if (longitude < MIN_LONGITUDE
        || longitude > MAX_LONGITUDE) {
      throw new IllegalArgumentException(
          "the longitude must be between -180 and 180, got "
              + longitude
      );
    }
  }
}
