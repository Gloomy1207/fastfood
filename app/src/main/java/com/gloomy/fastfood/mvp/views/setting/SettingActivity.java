package com.gloomy.fastfood.mvp.views.setting;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.gloomy.fastfood.R;
import com.gloomy.fastfood.mvp.BaseActivity;
import com.gloomy.fastfood.mvp.presenters.setting.SettingPresenter;
import com.gloomy.fastfood.mvp.views.main.MainActivity_;
import com.gloomy.fastfood.widgets.HeaderBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright © 2017 AsianTech inc.
 * Created by HungTQB on 18/04/2017.
 */
@EActivity(R.layout.activity_setting)
public class SettingActivity extends BaseActivity implements ISettingView {

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @ViewById(R.id.headerBar)
    HeaderBar mHeaderBar;

    @Bean
    SettingPresenter mPresenter;

    @AfterViews
    void afterViews() {
        mPresenter.setView(this);
        mPresenter.initRecyclerView(mRecyclerView);
        mPresenter.initHeaderBar(mHeaderBar);
    }

    @Override
    public void onLogoutSuccess() {
        MainActivity_.intent(this)
                .flags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .start();
    }

    @Override
    public void onBackClick() {
        finish();
        overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
    }

    @Override
    public void onUpdateProfile() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode);
    }
}
