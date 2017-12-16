package com.yj.welcome;

import android.os.Build;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yj.mvp.mvpbase.BaseActivity;
import com.yj.common.CommonUtils;
import com.yj.common.Constant;
import com.yj.distribution.MainActivity;
import com.yj.distribution.R;
import com.yj.mvp.loging.LandingActivity;
import com.yj.util.PreferenceUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author LK
 *         开机欢迎界面
 */
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.CountdownText)
    TextView CountdownText;
    private String uid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        uid = PreferenceUtils.getPrefString(mcontext, Constant.UID, "");
        timer.start();

    }

    private CountDownTimer timer = new CountDownTimer(3000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            CountdownText.setText("跳过 " + (millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            goActivity();

        }
    };

    @OnClick(R.id.CountdownText)
    public void onViewClicked() {
        goActivity();
    }

    private void goActivity() {
        if (uid.equals("")) {
            CommonUtils.goActivity(mcontext, LandingActivity.class, null, true);
        } else {
            CommonUtils.goActivity(mcontext, MainActivity.class, null, true);
        }
    }

}
