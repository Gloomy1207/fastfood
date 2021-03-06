package com.gloomy.fastfood.mvp.views.detail.store;

import com.gloomy.fastfood.mvp.IBaseView;

/**
 * Copyright © 2017 AsianTech inc.
 * Created by HungTQB on 24/04/2017.
 */
public interface IStoreDetailView extends IBaseView {
    void onBackClick();

    void onSetStoreName(String storeName);

    void onSetStoreTime(String storeTime);

    void onSetStoreAddress(String storeAddress);

    void onSetStoreImage(String imagePath, int width);

    void onSetNumberStars(String stars);

    void onSetNumberRating(String numberRating);

    void onNotLogin();

    void onEmptyComment();

    void onSendingComment();

    void onSendComment();

    void onRatingComplete(String message);

    void onRatingFailure();

    void onViewImagesClick();
}
