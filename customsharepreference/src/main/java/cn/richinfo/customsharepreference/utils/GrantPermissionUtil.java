package cn.richinfo.customsharepreference.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;


/**
 * Created by Pan on 2015/12/29.
 */
public class GrantPermissionUtil {
    private final static String TAG = "GrantPermissionUtil";

    /**
     *  检查并申请权限
     * @param context 上下文
     * @param permission
     * @param requestCode
     * @return
     */
    public static boolean checkPermissionStateAndRequest(Activity context, String permission, int requestCode) {
        if (PermissionChecker.checkSelfPermission(context,permission)
                != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG,permission + " permission request");
            ActivityCompat.requestPermissions(context,
                    new String[]{permission},
                    requestCode);
            return false;
        } else {
            return true;
        }
    }
}
