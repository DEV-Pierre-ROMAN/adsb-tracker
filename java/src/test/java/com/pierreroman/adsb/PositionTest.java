package com.pierreroman.adsb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PositionTest {
  @Test
  void latitudeAccessorReturnsConstructorValue() {
    Position position = new Position(48.85, 2.35);

    Assertions.assertEquals(48.85, position.latitude(),
        "L'acceseur renvoie la valeur latitude du constructeur");
  }

  @Test
  void longitudeAccessorReturnsConstructorValue() {
    Position position = new Position(48.85, 2.35);

    Assertions.assertEquals(2.35, position.longitude(),
        "L'acceseur renvoie la valeur longitude du constructeur");
  }

  @Test
  void twoPositionsWithSameCoordinatesAreEqual() {
    Position position1 = new Position(48.85, 2.35);
    Position position2 = new Position(48.85, 2.35);

    Assertions.assertEquals(position1, position2,
        "les positions avec les meme valeur peuvent être comparer avec un equal");
  }

  @Test
  void constructorMaximumLatitudeIs90() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new Position(91, 0);
    });
  }

  @Test
  void constructorMaximumLongitudeIs180() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new Position(0, 181);
    });
  }

  @Test
  void constructorIsInclusiveInExtremity() {
    Assertions.assertDoesNotThrow(() -> {
      new Position(90, 180);
    });
  }
}
