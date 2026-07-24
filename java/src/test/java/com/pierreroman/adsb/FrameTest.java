package com.pierreroman.adsb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FrameTest {

  @Test
  void constructorAcceptArray14Byte() {
    Assertions.assertDoesNotThrow(() -> {
      new Frame(new byte[14]);
    });
  }

  @Test
  void constructordoesntAcceptArrayLower14Byte() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new Frame(new byte[13]);
    });
  }

  @Test
  void constructordoesntAcceptArrayUpper14Byte() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new Frame(new byte[15]);
    });
  }

  @Test
  void propertiesImmuableEnter() {
    byte[] bytes = new byte[14];
    bytes[0] = 3;
    Frame frame = new Frame(bytes);
    bytes[0] = 4;

    Assertions.assertEquals(3, frame.raw()[0],
        "la référence d'entrée n'est plus accèssible pour modifications");
  }

  @Test
  void propertiesImmuableExit() {
    byte[] bytes = new byte[14];
    bytes[0] = 3;
    Frame frame = new Frame(bytes);
    frame.raw()[0] = 4;

    Assertions.assertEquals(3, frame.raw()[0],
        "la référence de l'accesseur ne permet pas de modifier la propriété");
  }

  @Test
  void dfIsDecodedFromTopFiveBits() {
    byte df = (byte) 0x8D;
    byte[] bytes = new byte[14];
    bytes[0] = df;

    Frame frame = new Frame(bytes);
    Assertions.assertEquals(17, frame.df(),
        "la methode df convertie bien les 5 premiers bits");
  }

  @Test
  void caIsDecodedFromLastThreeBitsOfFirstByte() {
    byte df = (byte) 0x8D;
    byte[] bytes = new byte[14];
    bytes[0] = df;

    Frame frame = new Frame(bytes);
    Assertions.assertEquals(5, frame.ca(),
        "la methode ca convertie bien les 3 derniers bits du premier octet");
  }

  @Test
  void icaoIsDecodedFromTheByte2to4() {
    byte[] bytes = new byte[14];
    bytes[1] = (byte) 0x48;
    bytes[2] = (byte) 0x40;
    bytes[3] = (byte) 0xD6;

    Frame frame = new Frame(bytes);
    Assertions.assertEquals(0x4840D6,  frame.icao(),
        "la methode ca convertie bien les octet 2 à 4");
  }

  @Test
  void meTcIsDecodedFromTheFiveFirstbitsOfTheFifthBytes() {
    byte[] bytes = new byte[14];
    bytes[4] = (byte) 0x20;

    Frame frame = new Frame(bytes);
    Assertions.assertEquals(4,  frame.typeCode(),
        "la methode ca convertie bien les 5 premiers bits du 5 ème octet");
  }
}
