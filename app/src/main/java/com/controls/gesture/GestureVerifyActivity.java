package com.controls.gesture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.controls.gesture.Util.GestureUtil;
import com.controls.gesture.view.GestureContentView;
import com.controls.gesture.view.GestureDrawline;


/**
 *
 * 手势绘制/校验界面
 *
 */
public class GestureVerifyActivity extends Activity implements View.OnClickListener {
    private static String TAG = "GestureVerifyActivity";
    /** 手机号码*/
    public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
    /** 意图 */
    public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";
    private int isnum = 0;
    private RelativeLayout mTopLayout;
    private TextView mTextTitle;
    private TextView mTextCancel;
    private ImageView mImgUserLogo;
    private TextView mTextPhoneNumber;
    private TextView mTextTip;
    private FrameLayout mGestureContainer;
    private GestureContentView mGestureContentView;
    private TextView mTextForget;
    private TextView mTextOther;
    private String mParamPhoneNumber;
    private long mExitTime = 0;
    private int mParamIntentCode;

    private LayoutInflater mInflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_verify);
        ObtainExtraData();
        setUpViews();
        setUpListeners();
//        CGApp.register(this);//存储该activity

        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private void ObtainExtraData() {
        mParamPhoneNumber = getIntent().getStringExtra(PARAM_PHONE_NUMBER);
        mParamIntentCode = getIntent().getIntExtra(PARAM_INTENT_CODE, 0);
    }

    private void setUpViews() {
        mTopLayout = (RelativeLayout) findViewById(R.id.top_layout);
        mTextTitle = (TextView) findViewById(R.id.text_title);
        mTextCancel = (TextView) findViewById(R.id.text_cancel);
        mImgUserLogo = (ImageView) findViewById(R.id.user_logo);
        mTextPhoneNumber = (TextView) findViewById(R.id.text_phone_number);
        mTextTip = (TextView) findViewById(R.id.text_tip);
        mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
        mTextForget = (TextView) findViewById(R.id.text_forget_gesture);
        mTextOther = (TextView) findViewById(R.id.text_other_account);
        mTextTip.setTextColor(getResources().getColor(R.color.gesture_text_color_1));


        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, true, GestureUtil.getLockPassword(GestureVerifyActivity.this,GestureUtil.Phone),
                new GestureDrawline.GestureCallBack() {

                    @Override
                    public void onGestureCodeInput(String inputCode) {

                    }

                    @Override
                    public void checkedSuccess() {
                        mGestureContentView.clearDrawlineState(0L);
//                        Toast.makeText(GestureVerifyActivity.this, "密码正确", Toast.LENGTH_SHORT).show();

//                        Constants.GESTURES_PASSWORD = false;//用于判断是否需要跳的这页面
                        GestureVerifyActivity.this.finish();
                    }

                    @Override
                    public void checkedFail() {
                        mGestureContentView.clearDrawlineState(1300L);
                        mTextTip.setVisibility(View.VISIBLE);

                        mTextTip.setText(Html
                                .fromHtml("<font >密码错误</font>"));
                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils.loadAnimation(GestureVerifyActivity.this, R.anim.shake);
                        mTextTip.startAnimation(shakeAnimation);
                        isnum++;
                        if(isnum == 4){
//                            startActivity(new Intent(GestureVerifyActivity.this,LoginActivity.class));
//                            Constants.GESTURES_PASSWORD = false;//用于判断是否需要跳的这页面
//                            UtilityUtils.removePassWord(GestureVerifyActivity.this,UtilityUtils.getUserPhone(GestureVerifyActivity.this));
//                            UtilityUtils.loginOut(GestureVerifyActivity.this);//清空本地保存的   userid  和token
//                            finish();
                        }
                    }
                });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
    }

    private void setUpListeners() {
        mTextCancel.setOnClickListener(this);
        mTextForget.setOnClickListener(this);
        mTextOther.setOnClickListener(this);
        mTextPhoneNumber.setText(GestureUtil.Phone);//设置用户手机号
    }


    long waitTime = 2000;
    long touchTime = 0;

    /**
     * 退出应用
     */
    @Override
    public void onBackPressed() {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                touchTime = currentTime;
            } else {
//                CGApp.finishAllActivity();
//                finish();
            }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_other_account://用其他账号登录
//                startActivity(new Intent(this,LoginActivity.class));
//                Constants.GESTURES_PASSWORD = false;//用于判断是否需要跳的这页面
//                UtilityUtils.loginOut(this);//清空本地保存的   userid  和token
                finish();
                break;
            case R.id.text_forget_gesture://忘记手势密码
//                AppUtil.isPasswroid(this, mInflater, true);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
//        JPushInterface.onResume(this);
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        JPushInterface.onPause(this);
//        MobclickAgent.onPause(this);
    }
}
