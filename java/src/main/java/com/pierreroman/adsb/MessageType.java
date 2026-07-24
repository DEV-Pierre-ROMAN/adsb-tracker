package com.pierreroman.adsb;

/**
 * Enum for defining the signification of a type code.
 */
public enum MessageType {
  AIRCRAFT_IDENTIFICATION, SURFACE_POSITION, AIRBORNE_POSITION, AIRBORNE_VELOCITY, UNKNOWN;

  /**
   * return the corresponding message type.
   */
  public static MessageType fromTypeCode(int typeCode) {
    if (typeCode >= 1 && typeCode <= 4) {
      return AIRCRAFT_IDENTIFICATION;
    } else if (typeCode >= 5 && typeCode <= 8) {
      return SURFACE_POSITION;
    } else if ((typeCode >= 9 && typeCode <= 18)
        || (typeCode >= 20 && typeCode <= 22)) {
      return AIRBORNE_POSITION;
    } else if (typeCode == 19) {
      return AIRBORNE_VELOCITY;
    }

    return UNKNOWN;
  }
}
