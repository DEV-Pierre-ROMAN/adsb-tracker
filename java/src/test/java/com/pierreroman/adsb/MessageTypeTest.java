package com.pierreroman.adsb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MessageTypeTest {

  @Test
  void fromTypeCodeReturnAircraftIdentificationLowerInterval() {
    Assertions.assertEquals(MessageType.AIRCRAFT_IDENTIFICATION,
        MessageType.fromTypeCode(1),
        "the fromTypeCode function return AIRCRAFT_IDENTIFICATION on lower interval");
  }

  @Test
  void fromTypeCodeReturnAircraftIdentificationHigherInterval() {
    Assertions.assertEquals(MessageType.AIRCRAFT_IDENTIFICATION,
        MessageType.fromTypeCode(4),
        "the fromTypeCode function return AIRCRAFT_IDENTIFICATION on higher interval");
  }

  @Test
  void fromTypeCodeReturnSurfacePositionLowerInterval() {
    Assertions.assertEquals(MessageType.SURFACE_POSITION,
        MessageType.fromTypeCode(5),
        "the fromTypeCode function return SURFACE_POSITION on lower interval");
  }

  @Test
  void fromTypeCodeReturnSurfacePositionHigherInterval() {
    Assertions.assertEquals(MessageType.SURFACE_POSITION,
        MessageType.fromTypeCode(8),
        "the fromTypeCode function return SURFACE_POSITION on higher interval");
  }

  @Test
  void fromTypeCodeReturnAirbornePositionLowerInterval1() {
    Assertions.assertEquals(MessageType.AIRBORNE_POSITION,
        MessageType.fromTypeCode(9),
        "the fromTypeCode function return AIRBORNE_POSITION on lower interval 1");
  }

  @Test
  void fromTypeCodeReturnAirbornePositionLowerInterval2() {
    Assertions.assertEquals(MessageType.AIRBORNE_POSITION,
        MessageType.fromTypeCode(18),
        "the fromTypeCode function return AIRBORNE_POSITION on lower interval 2");
  }

  @Test
  void fromTypeCodeReturnAirbornePositionHigherInterval1() {
    Assertions.assertEquals(MessageType.AIRBORNE_POSITION,
        MessageType.fromTypeCode(20),
        "the fromTypeCode function return AIRBORNE_POSITION on higher interval 1");
  }

  @Test
  void fromTypeCodeReturnAirbornePositionHigherInterval2() {
    Assertions.assertEquals(MessageType.AIRBORNE_POSITION,
        MessageType.fromTypeCode(22),
        "the fromTypeCode function return AIRBORNE_POSITION on higher interval 2");
  }

  @Test
  void fromTypeCodeReturnAirborneVelocity() {
    Assertions.assertEquals(MessageType.AIRBORNE_VELOCITY,
        MessageType.fromTypeCode(19),
        "the fromTypeCode function return AIRBORNE_VELOCITY");
  }

  @Test
  void fromTypeCodeReturnUnknown0() {
    Assertions.assertEquals(MessageType.UNKNOWN,
        MessageType.fromTypeCode(0),
        "the fromTypeCode function return UNKNOWN");
  }

  @Test
  void fromTypeCodeReturnUnknown23() {
    Assertions.assertEquals(MessageType.UNKNOWN,
        MessageType.fromTypeCode(23),
        "the fromTypeCode function return UNKNOWN");
  }
}
