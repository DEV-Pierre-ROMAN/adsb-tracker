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
}
