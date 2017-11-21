package com.yj.welcome;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.yj.common.CommonUtils;
import com.yj.distribution.MainActivity;
import com.yj.distribution.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author LK
 *         开机欢迎界面
 */
public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.CountdownText)
    TextView CountdownText;
    private Context mcontext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        mcontext = getApplicationContext();
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
        CommonUtils.goActivity(mcontext, MainActivity.class, null, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }
}
