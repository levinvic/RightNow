/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package tw.org.iii.rightnow.FloatView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import tw.org.iii.rightnow.FloatView.rom.HuaweiUtils;
import tw.org.iii.rightnow.FloatView.rom.MeizuUtils;
import tw.org.iii.rightnow.FloatView.rom.MiuiUtils;
import tw.org.iii.rightnow.FloatView.rom.QikuUtils;
import tw.org.iii.rightnow.FloatView.rom.RomUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author zhaozp
 * @since 2016-10-17
 */

public class FloatWindowManager {
    private static final String TAG = "FloatWindowManager";

    private static volatile FloatWindowManager instance;

    private boolean isWindowDismiss = true;
    private WindowManager windowManager = null;
    private WindowManager.LayoutParams mParams = null;
    private AVCallFloatView floatView = null;
    private Dialog dialog;

    public static FloatWindowManager getInstance() {
        if (instance == null) {
            synchronized (FloatWindowManager.class) {
                if (instance == null) {
                    instance = new FloatWindowManager();
                }
            }
        }
        return instance;
    }

    public void applyOrShowFloatWindow(Context context) {
        if (checkPermission(context)) {
            showWindow(context);
        } else {
            applyPermission(context);
        }
    }
    //
    public void openFloatWindow(Context context){
        showWindow(context);
    }

    public boolean checkfloatwindowPermission(Context context) {
        if (checkPermission(context)) {
            return true;
        } else {
            return false;
        }
    }

    public void floatwindowPermission(Context context) {
        applyPermission(context);
    }



    private boolean checkPermission(Context context) {
        //6.0 版本之後由於 google 增加了對懸浮窗權限的管理，所以方法就統一了
        if (Build.VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                return miuiPermissionCheck(context);
            } else if (RomUtils.checkIsMeizuRom()) {
                return meizuPermissionCheck(context);
            } else if (RomUtils.checkIsHuaweiRom()) {
                return huaweiPermissionCheck(context);
            } else if (RomUtils.checkIs360Rom()) {
                return qikuPermissionCheck(context);
            }
        }
        return commonROMPermissionCheck(context);
    }

    private boolean huaweiPermissionCheck(Context context) {
        return HuaweiUtils.checkFloatWindowPermission(context);
    }

    private boolean miuiPermissionCheck(Context context) {
        return MiuiUtils.checkFloatWindowPermission(context);
    }

    private boolean meizuPermissionCheck(Context context) {
        return MeizuUtils.checkFloatWindowPermission(context);
    }

    private boolean qikuPermissionCheck(Context context) {
        return QikuUtils.checkFloatWindowPermission(context);
    }

    private boolean commonROMPermissionCheck(Context context) {
        //最新發現魅族6.0的系統這種方式不好用，天殺的，只有你是奇葩，沒辦法，單獨適配一下
        if (RomUtils.checkIsMeizuRom()) {
            return meizuPermissionCheck(context);
        } else {
            Boolean result = true;
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    Class clazz = Settings.class;
                    Method canDrawOverlays = clazz.getDeclaredMethod("canDrawOverlays", Context.class);
                    result = (Boolean) canDrawOverlays.invoke(null, context);
                } catch (Exception e) {
                    Log.e(TAG, Log.getStackTraceString(e));
                }
            }
            return result;
        }
    }

    private void applyPermission(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                miuiROMPermissionApply(context);
            } else if (RomUtils.checkIsMeizuRom()) {
                meizuROMPermissionApply(context);
            } else if (RomUtils.checkIsHuaweiRom()) {
                huaweiROMPermissionApply(context);
            } else if (RomUtils.checkIs360Rom()) {
                ROM360PermissionApply(context);
            }
        }
        commonROMPermissionApply(context);
    }

    private void ROM360PermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() {
            @Override
            public void confirmResult(boolean confirm) {
                if (confirm) {
                    QikuUtils.applyPermission(context);
                } else {
                    Log.e(TAG, "ROM:360, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void huaweiROMPermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() {
            @Override
            public void confirmResult(boolean confirm) {
                if (confirm) {
                    HuaweiUtils.applyPermission(context);
                } else {
                    Log.e(TAG, "ROM:huawei, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void meizuROMPermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() {
            @Override
            public void confirmResult(boolean confirm) {
                if (confirm) {
                    MeizuUtils.applyPermission(context);
                } else {
                    Log.e(TAG, "ROM:meizu, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void miuiROMPermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() {
            @Override
            public void confirmResult(boolean confirm) {
                if (confirm) {
                    MiuiUtils.applyMiuiPermission(context);
                } else {
                    Log.e(TAG, "ROM:miui, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    /**
     * 通用 rom 权限申请
     */
    private void commonROMPermissionApply(final Context context) {
        //這裡也一樣，魅族系統需要單獨適配
        if (RomUtils.checkIsMeizuRom()) {
            meizuROMPermissionApply(context);
        } else {
            if (Build.VERSION.SDK_INT >= 23) {
                showConfirmDialog(context, new OnConfirmResult() {
                    @Override
                    public void confirmResult(boolean confirm) {
                        if (confirm) {
                            try {
                                Class clazz = Settings.class;
                                Field field = clazz.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION");

                                //TODO 圓形功能列點擊

                                Intent intent = new Intent(field.get(null).toString());
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setData(Uri.parse("package:" + context.getPackageName()));
                                context.startActivity(intent);



                            } catch (Exception e) {
                                Log.e(TAG, Log.getStackTraceString(e));
                            }
                        } else {
                            Log.d(TAG, "user manually refuse OVERLAY_PERMISSION");
                            //需要做統計效果
                        }
                    }
                });
            }
        }
    }

    private void showConfirmDialog(Context context, OnConfirmResult result) {
        showConfirmDialog(context, "您的手機沒有授予懸浮窗權限，請開啓重試", result);
    }

    private void showConfirmDialog(Context context, String message, final OnConfirmResult result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        dialog = new AlertDialog.Builder(context).setCancelable(true).setTitle("")
                .setMessage(message)
                .setPositiveButton("現在去開啓",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirmResult(true);
                                dialog.dismiss();
                            }
                        }).setNegativeButton("暫不開啓",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirmResult(false);
                                dialog.dismiss();
                            }
                        }).create();
        dialog.show();
    }



    private interface OnConfirmResult {
        void confirmResult(boolean confirm);
    }

    private void showWindow(Context context) {
        if (!isWindowDismiss) {
            Log.e(TAG, "view is already added here");
            return;
        }

        isWindowDismiss = false;
        if (windowManager == null) {
            windowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        }

        Point size = new Point();
        windowManager.getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        mParams = new WindowManager.LayoutParams();
        mParams.packageName = context.getPackageName();
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        mParams.format = PixelFormat.RGBA_8888;
        mParams.gravity = Gravity.LEFT | Gravity.TOP;
        mParams.x = screenWidth - dp2px(context, 100);
        mParams.y = screenHeight - dp2px(context, 171);


//        ImageView imageView = new ImageView(mContext);
//        imageView.setImageResource(R.drawable.app_icon);
        floatView = new AVCallFloatView(context);
        floatView.setParams(mParams);
        floatView.setIsShowing(true);
        windowManager.addView(floatView, mParams);
    }

    public void dismissWindow() {
        if (isWindowDismiss) {
            Log.e(TAG, "window can not be dismiss cause it has not been added");
            return;
        }

        isWindowDismiss = true;
        floatView.setIsShowing(false);
        if (windowManager != null && floatView != null) {
            windowManager.removeViewImmediate(floatView);
        }
    }

    private int dp2px(Context context, float dp){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
