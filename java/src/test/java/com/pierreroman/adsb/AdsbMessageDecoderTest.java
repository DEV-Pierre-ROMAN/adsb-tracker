package com.pierreroman.adsb;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AdsbMessageDecoderTest {

  @Test
  void decodeAndConvertFrameToAircraftIdentification() {
    byte[] bytes = new byte[14];
    bytes[0] = (byte) 0x8D;
    bytes[1] = (byte) 0x48;
    bytes[2] = (byte) 0x40;
    bytes[3] = (byte) 0xD6;
    bytes[4] = (byte) 0x20;
    bytes[5] = (byte) 0x2C;
    bytes[6] = (byte) 0xC3;
    bytes[7] = (byte) 0x71;
    bytes[8] = (byte) 0xC3;
    bytes[9] = (byte) 0x2C;
    bytes[10] = (byte) 0xE0;
    bytes[11] = (byte) 0x57;
    bytes[12] = (byte) 0x60;
    bytes[13] = (byte) 0x98;

    AdsbMessageDecoder decoder = new AdsbMessageDecoder();
    Frame frame = new Frame(bytes);
    Optional<AdsbMessage> message = decoder.decode(frame);

    Assertions.assertEquals(Optional.of(new AircraftIdentification("KLM1023")), message,
        "adsbMessageDecoder decode AircraftIdentification");
  }

  @Test
  void decodeReturnsEmptyForUnsupportedMessageType() {
    byte[] bytes = new byte[14];
    bytes[4] = (byte) 0x58;

    AdsbMessageDecoder decoder = new AdsbMessageDecoder();
    Frame frame = new Frame(bytes);
    Optional<AdsbMessage> message = decoder.decode(frame);

    Assertions.assertEquals(Optional.empty(), message,
        "adsbMessageDecoder return empty when frame is unsupported");
  }
}
