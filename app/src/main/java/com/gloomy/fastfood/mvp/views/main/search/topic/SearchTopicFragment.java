package com.gloomy.fastfood.mvp.views.main.search.topic;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gloomy.fastfood.R;
import com.gloomy.fastfood.api.responses.SearchTopicResponse;
import com.gloomy.fastfood.mvp.BaseFragment;
import com.gloomy.fastfood.mvp.models.Topic;
import com.gloomy.fastfood.mvp.presenters.main.search.topic.SearchTopicPresenter;
import com.gloomy.fastfood.mvp.views.detail.topic.TopicDetailActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.parceler.Parcels;

/**
 * Copyright © 2017 Gloomy
 * Created by HungTQB on 30-Mar-17.
 */
@EFragment(R.layout.fragment_search_topic)
public class SearchTopicFragment extends BaseFragment implements ISearchTopicView {

    @ViewById(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @ViewById(R.id.disableView)
    View mDisableView;

    @Bean
    SearchTopicPresenter mPresenter;

    private SearchTopicResponse mSearchTopicResponse;

    @AfterViews
    void afterViews() {
        mPresenter.setView(this);
        mPresenter.initRecyclerView(mRecyclerView);
        mPresenter.initSwipeRefresh(mSwipeRefreshLayout, mDisableView);
        mPresenter.getSearchTopicData(mSearchTopicResponse);
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
        mDisableView.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
        showNoInternetConnectionMessage();
    }

    @Override
    public void onLoadDataComplete(SearchTopicResponse response) {
        mSearchTopicResponse = response;
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreComplete() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onLoadDataFailure() {
        mSwipeRefreshLayout.setRefreshing(false);
        mDisableView.setVisibility(View.GONE);
        showLoadDataFailure();
    }

    @Override
    public void onRefreshComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
        mDisableView.setVisibility(View.GONE);
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onItemTopicClick(Topic topic) {
        TopicDetailActivity_.intent(getActivity()).mTopicParcel(Parcels.wrap(topic)).start();
    }

    @Override
    public void notifyDataSetChanged() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
