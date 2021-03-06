package com.gloomy.fastfood.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.gloomy.fastfood.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Copyright © 2017 Gloomy
 * Created by HungTQB on 27-Mar-17.
 */
@EViewGroup(R.layout.layout_header_bar)
public class HeaderBar extends Toolbar {

    @ViewById(R.id.tvHeaderTitle)
    TextView mTvHeaderTitle;

    @ViewById(R.id.btnLeft)
    ImageView mBtnLeft;

    @ViewById(R.id.btnRight)
    ImageView mBtnRight;

    @Setter
    @Accessors(prefix = "m")
    private OnHeaderBarListener mOnHeaderBarListener;

    public HeaderBar(Context context) {
        this(context, null);
    }

    public HeaderBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setTitle(String title) {
        mTvHeaderTitle.setText(title);
    }

    public void setLeftButtonVisibility(int visibility) {
        mBtnLeft.setVisibility(visibility);
    }

    public void setRightButtonVisibility(int visibility) {
        mBtnRight.setVisibility(visibility);
    }

    public void setImageResourceRightButton(int resId) {
        mBtnRight.setImageResource(resId);
    }

    @Click(R.id.btnLeft)
    void onLeftClick() {
        if (mOnHeaderBarListener != null) {
            mOnHeaderBarListener.onLeftButtonClick();
        }
    }

    @Click(R.id.btnRight)
    void onRightClick() {
        if (mOnHeaderBarListener != null) {
            mOnHeaderBarListener.onRightButtonClick();
        }
    }

    /**
     * OnHeaderBarListener interface
     */
    public interface OnHeaderBarListener {
        void onLeftButtonClick();

        void onRightButtonClick();
    }
}
