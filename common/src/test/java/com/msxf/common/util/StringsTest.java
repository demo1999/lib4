package com.msxf.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author RobX (robxyy@gmail.com)
 */
public class StringsTest {

  @Test public void testIsBlank() {
    assertEquals(true, Strings.isBlank(""));
    assertEquals(true, Strings.isBlank(" "));
    assertEquals(false, Strings.isBlank("ok"));
    assertEquals(false, Strings.isBlank(" no"));
  }

  @Test public void testValueOrDefault() {
    assertEquals("ok", Strings.valueOrDefault("", "ok"));
    assertEquals("ok", Strings.valueOrDefault(" ", "ok"));
    assertEquals("ok", Strings.valueOrDefault("ok", "no"));
    assertEquals(" no", Strings.valueOrDefault(" no", "no"));
  }

  @Test public void testTruncateAt() {
    assertEquals("da", Strings.truncateAt("dadaff", 2));
    assertEquals("dadaff", Strings.truncateAt("dadaff", 10));
    assertEquals("", Strings.truncateAt("dadaff", 0));
  }

  @Test public void testInputStreamToString() {
    try {
      assertEquals("hello",
          Strings.inputStreamToString(new ByteArrayInputStream("hello".getBytes())));
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      assertEquals("hdafdsafello1",
          Strings.inputStreamToString(new ByteArrayInputStream("hdafdsafello1".getBytes())));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test public void testIsNumeric() {
    assertEquals(false, Strings.isNumeric("dadaff"));
    assertEquals(false, Strings.isNumeric("e2424"));
    assertEquals(true, Strings.isNumeric("32432"));
  }
}
