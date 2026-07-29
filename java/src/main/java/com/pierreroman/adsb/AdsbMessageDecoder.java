package com.pierreroman.adsb;

import java.util.Optional;

/**
 * Decodes frame to an AdsbMessage.
 */
public class AdsbMessageDecoder {

  /**
   * Decodes the frame and converts it in the appropriate AdsbMessage type.
   *
   * @param frame encoded ads-b frame.
   * @return a decoded message or Optional.empty() if the frame is unsupported.
   */
  public Optional<AdsbMessage> decode(Frame frame) {
    return switch (MessageType.fromTypeCode(frame.typeCode())) {
      case AIRCRAFT_IDENTIFICATION -> Optional.of(new AircraftIdentification(frame.callsign()));
      case AIRBORNE_POSITION, AIRBORNE_VELOCITY, SURFACE_POSITION, UNKNOWN -> Optional.empty();
    };
  }
}
