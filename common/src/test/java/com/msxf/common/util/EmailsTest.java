package com.msxf.common.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author RobX (robxyy@gmail.com)
 */
public class EmailsTest {

  @Test public void testIsValid() {
    assertEquals(false, Emails.isValid("da@"));
    assertEquals(false, Emails.isValid("da"));
    assertEquals(false, Emails.isValid("da@ds"));
    assertEquals(false, Emails.isValid("da@ds."));
    assertEquals(true, Emails.isValid("da@ds.com"));
    assertEquals(true, Emails.isValid("da@ds.2"));
  }
}
