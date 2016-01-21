package com.controls.gesture.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by 涛 on 2015/12/17.
 */
public class GestureUtil {
    public static String Phone= "15123123123";//手机号
    private static final String CONFIG_GESTURE = "config_gesture";//存储手势密码的key
    // 手势密码点的状态
    public static final int POINT_STATE_NORMAL = 0; // 正常状态
    public static final int POINT_STATE_SELECTED = 1; // 按下状态
    public static final int POINT_STATE_WRONG = 2; // 错误状态
    /**
     * 获取屏幕分辨率
     * @param context
     * @return
     */
    public static int[] getScreenDispaly(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        //获取屏幕信息
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;;// 手机屏幕的宽度
        int height =dm.heightPixels;// 手机屏幕的高度
        int result[] = { width, height };
        return result;
    }

    /**
     * 存储登录后是否是否提示用户过设置手势密码
     *
     * @param context
     * @param phone
     * @return
     */
    public static void saveisLockPassWord(Context context, String phone, int isLock) {
        SharedPreferences preferences = context.getSharedPreferences(CONFIG_GESTURE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(phone + "isLock", isLock);
        editor.apply();
    }

    /**
     * 获取登录后是否是否提示用户过设置手势密码
     *
     * @param context
     * @param phone
     * @return int 返回0 是该账户没有在该手机登录过    1是登录了  没有提示设置过手势   2是已经提示过了
     */
    public static int getisLockPassWord(Context context, String phone) {
        SharedPreferences preferences = context.getSharedPreferences(CONFIG_GESTURE, Context.MODE_PRIVATE);
        return preferences.getInt(phone + "isLock", 0);
    }

    /**
     * 删除手势密码
     *
     * @param context
     * @param phone
     */
    public static void removePassWord(Context context, String phone) {
        SharedPreferences preferences = context.getSharedPreferences(CONFIG_GESTURE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(phone);
        editor.apply();
    }

    /**
     * 保存手势密码
     *
     * @param context
     * @param phone
     * @param pwd
     */
    public static void saveLockPassWord(Context context, String phone, String pwd) {
        SharedPreferences preferences = context.getSharedPreferences(CONFIG_GESTURE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(phone, pwd);
        editor.apply();
    }

    /**
     * 获取手势密码
     *
     * @param context
     * @param phone
     * @return
     */
    public static String getLockPassword(Context context, String phone) {
        SharedPreferences preferences = context.getSharedPreferences(CONFIG_GESTURE, Context.MODE_PRIVATE);
        return preferences.getString(phone, null);
    }
}
