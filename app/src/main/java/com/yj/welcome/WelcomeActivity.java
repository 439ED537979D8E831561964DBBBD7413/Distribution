package com.yj.welcome;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.yj.base.BaseActivity;
import com.yj.common.CommonUtils;
import com.yj.distribution.MainActivity;
import com.yj.distribution.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author LK
 *         开机欢迎界面
 */
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.CountdownText)
    TextView CountdownText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData() {
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
    private void goActivity(){
        CommonUtils.goActivity(mcontext, MainActivity.class, null, true);
    }
}
