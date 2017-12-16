package com.yj.mvp.loging;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.yj.bean.User;
import com.yj.common.CommonUtils;
import com.yj.distribution.MainActivity;
import com.yj.distribution.R;
import com.yj.mvp.mvpbase.MVPBaseActivity;
import com.yj.mvp.loging.LoginContract;
import com.yj.mvp.loging.LoginPresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author LK
 */
public class LandingActivity extends MVPBaseActivity<LoginContract.View<User>, LoginPresenterImpl> implements LoginContract.View<User> {

    @BindView(R.id.tv_title_content)
    TextView tvTitleContent;
    @BindView(R.id.edit_username)
    MaterialEditText editUsername;
    @BindView(R.id.edit_password)
    MaterialEditText editPassword;
    @BindView(R.id.login_pgb)
    ProgressBar loginPgb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_landing;
    }

    @Override
    protected void initData() {
        tvTitleContent.setText("登   陆");
    }

    @Override
    public void showProgress() {
        loginPgb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loginPgb.setVisibility(View.GONE);
    }

    @Override
    public void setError(String msg) {
        showToast(msg);
    }

    @Override
    public void navigateToHome(User user) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", user);
        CommonUtils.goActivity(mcontext, MainActivity.class, bundle, true);
    }

    @OnClick({R.id.tv_register, R.id.get_back_psw, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                break;
            case R.id.get_back_psw:
                break;
            case R.id.login_btn:
                mPresenter.validateCredentials(editUsername.getText().toString().trim(), editPassword.getText().toString().trim());
                hintKbTwo();
                break;
            default:
                break;
        }
    }

    @Override
    protected LoginPresenterImpl createPresenter() {
        return new LoginPresenterImpl(this, mcontext);
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
