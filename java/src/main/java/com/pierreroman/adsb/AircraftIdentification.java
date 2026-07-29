package com.pierreroman.adsb;

/**
 * Is a aircraft ID named callsign, composed of a maximum of 8 char.
 *
 * @param callsign is a decoded ID of an aircraft of 8 char max.
 */
public record AircraftIdentification(String callsign) implements AdsbMessage {
}
