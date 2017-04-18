package com.gloomy.fastfood.ui.views.main.profile.feeds;

import android.support.v7.widget.RecyclerView;

import com.gloomy.fastfood.R;
import com.gloomy.fastfood.api.responses.ProfileResponse;
import com.gloomy.fastfood.models.Topic;
import com.gloomy.fastfood.observer.ProfileObserver;
import com.gloomy.fastfood.observer.listener.OnReceiveObserverListener;
import com.gloomy.fastfood.ui.BaseFragment;
import com.gloomy.fastfood.ui.presenters.main.profile.feed.ProfileFeedPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright © 2017 Gloomy
 * Created by HungTQB on 01-Apr-17.
 */
@EFragment(R.layout.fragment_profile_feeds)
public class ProfileFeedsFragment extends BaseFragment implements IProfileFeedView, OnReceiveObserverListener {

    @Bean
    ProfileFeedPresenter mPresenter;

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @AfterViews
    void afterViews() {
        mPresenter.setView(this);
        mPresenter.initRecyclerView(mRecyclerView);
    }

    @Override
    public void onShowProgressDialog() {
        showProgressDialog();
    }

    @Override
    public void onDismissProgressDialog() {
        dismissProgressDialog();
    }

    @Override
    public void onNoInternetConnection() {
        showNoInternetConnectionMessage();
    }

    @Override
    public void onStart() {
        super.onStart();
        ProfileObserver.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        ProfileObserver.unregister(this);
    }

    @Override
    public void onReceiveProfileData(ProfileResponse profileResponse) {
        mPresenter.onReceiveProfileData(profileResponse);
    }

    @Override
    public void onItemTopicClick(Topic topic) {
        // TODO: 18-Apr-17 Handle when click item topic
    }

    @Override
    public void onLoadMoreComplete() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onLoadDataComplete() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
