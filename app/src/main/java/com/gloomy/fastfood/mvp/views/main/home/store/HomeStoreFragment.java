package com.gloomy.fastfood.mvp.views.main.home.store;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gloomy.fastfood.R;
import com.gloomy.fastfood.mvp.BaseFragment;
import com.gloomy.fastfood.mvp.models.Store;
import com.gloomy.fastfood.mvp.presenters.main.home.store.HomeStorePresenter;
import com.gloomy.fastfood.mvp.views.detail.store.StoreDetailActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.parceler.Parcels;

/**
 * Copyright © 2017 Gloomy
 * Created by HungTQB on 29-Mar-17.
 */
@EFragment(R.layout.fragment_home_store)
public class HomeStoreFragment extends BaseFragment implements IHomeStoreView {

    @Bean
    HomeStorePresenter mPresenter;

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @ViewById(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.disableView)
    View mDisableView;

    @AfterViews
    void afterViews() {
        mPresenter.setView(this);
        mPresenter.getDataForStores();
        mPresenter.initSwipeRefresh(mSwipeRefreshLayout, mDisableView);
    }

    @Override
    public void onLoadDataComplete() {
        mPresenter.initRecyclerView(mRecyclerView);
    }

    @Override
    public void onLoadMoreComplete() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onLoadDataFailure() {
        mDisableView.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
        showLoadDataFailure();
    }

    @Override
    public void onRefreshComplete() {
        mDisableView.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
        mPresenter.refreshData(mRecyclerView);
    }

    @Override
    public void onNoInternetConnection() {
        mSwipeRefreshLayout.setRefreshing(false);
        showNoInternetConnectionMessage();
    }

    @Override
    public void onItemHomeStoreClick(Store store) {
        StoreDetailActivity_.intent(getActivity()).mStoreParcel(Parcels.wrap(store)).start();
    }

    @Override
    public void notifyDataSetChanged() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onShowProgressDialog() {
        showProgressDialog();
    }

    @Override
    public void onDismissProgressDialog() {
        dismissProgressDialog();
    }
}
