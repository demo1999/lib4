package com.msxf.common.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * 网络工具类
 *
 * @author RobX (robxyy@gmail.com)
 */
public final class NetworkUtils {
  private NetworkUtils() {
    // No instances.
  }

  /**
   * 返回网络连接信息
   */
  public static NetworkInfo getNetworkInfo(Context context) {
    ConnectivityManager cm =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    return cm.getActiveNetworkInfo();
  }

  /**
   * 返回当前网络的状态,当且仅当网络已连接或正在连接才返回true,反之false.
   */
  public static boolean isActiveNetwork(Context context) {
    NetworkInfo info = NetworkUtils.getNetworkInfo(context);
    return info != null && info.isConnectedOrConnecting();
  }

  /**
   * 当且仅当已经连接好的网络才返回true,反之false.
   */
  public static boolean isConnected(Context context) {
    NetworkInfo info = NetworkUtils.getNetworkInfo(context);
    return info != null && info.isConnected();
  }

  /**
   * 当且仅当已经连接了手机类型的网络才返回true,反之返回false.
   */
  public static boolean isConnectedMobile(Context context) {
    NetworkInfo info = NetworkUtils.getNetworkInfo(context);
    return info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE;
  }

  /**
   * 当且仅当已经连接了Wifi类型的网络才返回true,反之返回false.
   */
  public static boolean isConnectedWifi(Context context) {
    NetworkInfo info = NetworkUtils.getNetworkInfo(context);
    return info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI;
  }

  /**
   * 当且仅当连接快速的网络才返回true,反之false.
   */
  public static boolean isConnectionFast(Context context) {
    NetworkInfo info = NetworkUtils.getNetworkInfo(context);
    return info != null && info.isConnected() && isConnectionFast(info.getType(),
        info.getSubtype());
  }

  /**
   * 当且仅当连接快速的网络才返回true,反之false.
   *
   * @param type 连接类型
   * @param subType 连接子类型
   */
  public static boolean isConnectionFast(int type, int subType) {
    if (type == ConnectivityManager.TYPE_WIFI) {
      return true;
    } else if (type == ConnectivityManager.TYPE_MOBILE) {
      switch (subType) {
        case TelephonyManager.NETWORK_TYPE_1xRTT:
          return false; // ~ 50-100 kbps
        case TelephonyManager.NETWORK_TYPE_CDMA:
          return false; // ~ 14-64 kbps
        case TelephonyManager.NETWORK_TYPE_EDGE:
          return false; // ~ 50-100 kbps
        case TelephonyManager.NETWORK_TYPE_EVDO_0:
          return true; // ~ 400-1000 kbps
        case TelephonyManager.NETWORK_TYPE_EVDO_A:
          return true; // ~ 600-1400 kbps
        case TelephonyManager.NETWORK_TYPE_GPRS:
          return false; // ~ 100 kbps
        case TelephonyManager.NETWORK_TYPE_HSDPA:
          return true; // ~ 2-14 Mbps
        case TelephonyManager.NETWORK_TYPE_HSPA:
          return true; // ~ 700-1700 kbps
        case TelephonyManager.NETWORK_TYPE_HSUPA:
          return true; // ~ 1-23 Mbps
        case TelephonyManager.NETWORK_TYPE_UMTS:
          return true; // ~ 400-7000 kbps
        /*
         * Above API level 7, make sure to set android:targetSdkVersion
				 * to appropriate level to use these
				 */
        case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
          return true; // ~ 1-2 Mbps
        case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
          return true; // ~ 5 Mbps
        case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
          return true; // ~ 10-20 Mbps
        case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
          return false; // ~25 kbps
        case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
          return true; // ~ 10+ Mbps
        // Unknown
        case TelephonyManager.NETWORK_TYPE_UNKNOWN:
        default:
          return false;
      }
    } else {
      return false;
    }
  }
}
