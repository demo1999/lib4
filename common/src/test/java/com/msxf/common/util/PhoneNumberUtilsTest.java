package com.msxf.common.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author RobX (robxyy@gmail.com)
 */
public class PhoneNumberUtilsTest {

  @Test public void testIsValid() {
    assertEquals(false, PhoneNumberUtils.isValid("1324efa"));
    assertEquals(false, PhoneNumberUtils.isValid("1324"));
    assertEquals(false, PhoneNumberUtils.isValid("2324"));
    assertEquals(true, PhoneNumberUtils.isValid("13229767902"));
    assertEquals(false, PhoneNumberUtils.isValid("1322976790."));
    assertEquals(false, PhoneNumberUtils.isValid("2322976790a"));
  }
}
