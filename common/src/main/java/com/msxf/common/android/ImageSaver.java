package com.msxf.common.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * {@link Bitmap} 工具类
 *
 * Created by RobX on 15/8/27.
 */
public final class ImageSaver {
  private ImageSaver() {
    // No instances.
  }

  /**
   * 当且仅当压缩并保持成功才返回true，反之false
   *
   * @param pathName 源图片路径
   * @param reqWidth 请求的图片宽度
   * @param reqHeight 请求的图片高度
   * @param quality 质量
   * @param context 上下文引用
   */
  public static boolean compressAndSaveFile(String pathName, int reqWidth, int reqHeight,
      int quality, Context context) {
    final File outDir = StorageUtils.getCacheDir(context);
    File src = new File(pathName);
    String filename = src.getName();
    File outFile = new File(outDir, filename);
    return compressAndSaveFile(pathName, reqWidth, reqHeight, quality, outFile.getPath());
  }

  /**
   * 当且仅当压缩并保持成功才返回 true，反之 false
   *
   * @param pathName 源图片路径
   * @param reqWidth 请求的图片宽度
   * @param reqHeight 请求的图片高度
   * @param quality 质量
   * @param outPathName 输出路径
   */
  public static boolean compressAndSaveFile(String pathName, int reqWidth, int reqHeight,
      int quality, String outPathName) {
    Bitmap src = decodeSampledBitmap(pathName, reqWidth, reqHeight);
    if (src == null) {
      return false;
    }
    int dstHeight = src.getHeight() * reqWidth / src.getWidth();
    Bitmap out = Bitmap.createScaledBitmap(src, reqWidth, dstHeight, true);
    if (!src.isRecycled()) {
      src.recycle();
      src = null;
    }
    File outFile = new File(outPathName);
    if (outFile.exists()) {
      outFile.delete();
    }
    try {
      outFile.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      return compressAndSaveFile(out, quality, outFile);
    } finally {
      if (out != null && !out.isRecycled()) {
        out.recycle();
        out = null;
      }
    }
  }

  /**
   * 当且仅当压缩并保持成功才返回 true，反之 false
   *
   * @param src 源{@link Bitmap}
   * @param quality 质量
   * @param outFile 输出图片
   */
  public static boolean compressAndSaveFile(Bitmap src, int quality, File outFile) {
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(outFile);
      return src.compress(Bitmap.CompressFormat.JPEG, quality, fos);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (fos != null) {
        try {
          fos.flush();
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return false;
  }

  /**
   * 解码图片成 {@link Bitmap}
   *
   * @param pathName 源图片路径
   * @param reqWidth 请求图片的宽度
   * @param reqHeight 请求图片的高度
   */
  public static Bitmap decodeSampledBitmap(String pathName, int reqWidth, int reqHeight) {
    // First decode with inJustDecodeBounds=true to check dimensions
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(pathName, options);

    // Calculate inSampleSize
    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
    //options.inSampleSize = 1;

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(pathName, options);
  }

  /**
   * 计算并返回最佳的 {@link BitmapFactory.Options#inSampleSize}
   *
   * @param options 图片解码选项
   * @param reqWidth 请求的图片宽度
   * @param reqHeight 请求的图片高度
   */
  public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
      int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {
      final int halfHeight = height / 2;
      final int halfWidth = width / 2;

      // Calculate the largest inSampleSize value that is a power of 2 and keeps both
      // height and width larger than the requested height and width.
      while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
        inSampleSize *= 2;
      }
    }
    return inSampleSize;
  }
}
