/*
 * Copyright (C) 2007 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.msxf.common.util;

import android.support.annotation.Nullable;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Utility methods for working with {@link Closeable} objects.
 */
public final class Closeables {
  private Closeables() {
    // No instances.
  }

  public static void close(@Nullable Closeable closeable, boolean swallowIOException)
      throws IOException {
    if (closeable == null) {
      return;
    }
    try {
      closeable.close();
    } catch (IOException e) {
      if (!swallowIOException) {
        throw e;
      }
    }
  }

  public static void closeQuietly(@Nullable InputStream inputStream) {
    try {
      close(inputStream, true);
    } catch (IOException impossible) {
      throw new AssertionError(impossible);
    }
  }

  public static void closeQuietly(@Nullable Reader reader) {
    try {
      close(reader, true);
    } catch (IOException impossible) {
      throw new AssertionError(impossible);
    }
  }
}