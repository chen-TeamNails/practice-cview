package com.gogo.demo1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by liuchen_ on 2018/6/13.
 */

public class ScrollPanelListView extends ListView implements AbsListView.OnScrollListener {

    private View scrollView;
    private static final String TAG = "ScrollPanelListView";
    private int mWidthMeasureSpec;
    private int mHeightMeasureSpec;
    private int thumbOffset;
    private int mScorllBarPanelPosition = 100;

    public ScrollPanelListView(Context context) {
        super(context);
    }

    public ScrollPanelListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnScrollListener(this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.scroll);
        try {
            int layoutId = ta.getResourceId(R.styleable.scroll_scrollBarPanel, 0);
            scrollView = LayoutInflater.from(getContext()).inflate(layoutId, this, false);
        } finally {
            ta.recycle();
        }
        initScrollView();
    }

    private void initScrollView() {

        scrollView.setVisibility(GONE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (scrollView != null && scrollView.getVisibility() == VISIBLE) {
            drawChild(canvas, scrollView, getDrawingTime());
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (scrollView != null && getAdapter() != null) {
            mWidthMeasureSpec = widthMeasureSpec;
            mHeightMeasureSpec = heightMeasureSpec;
            measureChild(scrollView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (scrollView != null && getAdapter() != null) {

            int left = getMeasuredWidth() - scrollView.getMeasuredWidth() - getVerticalScrollbarWidth();

            scrollView.layout(left,
                    mScorllBarPanelPosition,
                    left + scrollView.getMeasuredWidth(),
                    mScorllBarPanelPosition + scrollView.getMeasuredHeight());
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.d(TAG, "onScoll");
        if (scrollView != null) {
            int height =
                    Math.round(1.0f * getMeasuredHeight() * computeVerticalScrollExtent() / computeVerticalScrollRange());
            Log.d(TAG, "onScoll 滑块的高度 》》" + height);
            //滑块中间的的y坐标  thumoffset/offset =  滑块的高度/extent=
            thumbOffset = height * computeVerticalScrollOffset() / computeVerticalScrollExtent();
            thumbOffset += height / 2;
            mScorllBarPanelPosition = thumbOffset - scrollView.getMeasuredHeight() / 2;
            int left = getMeasuredWidth() - scrollView.getMeasuredWidth() - getVerticalScrollbarWidth();
            scrollView.layout(left,
                    mScorllBarPanelPosition,
                    left + scrollView.getMeasuredWidth(),
                    mScorllBarPanelPosition + scrollView.getMeasuredHeight());
        }

//        computeHorizontalScrollExtent();//放大之后的滑块的高度
//        computeHorizontalScrollOffset();//放倒后滑块顶部到父组件顶部的距离
//        computeHorizontalScrollRange();//滑动的范围

    }

    @Override
    protected boolean awakenScrollBars() {
        return super.awakenScrollBars();
    }

    @Override
    protected boolean awakenScrollBars(int startDelay, boolean invalidate) {
        boolean awkenScrollBars = super.awakenScrollBars(startDelay, invalidate);
        if (awkenScrollBars && scrollView != null) {
            if (scrollView.getVisibility() == GONE) {
                scrollView.setVisibility(VISIBLE);
                ViewCompat.animate(scrollView)
//                        .alphaBy(0)
                        .alpha(1)
                        .setDuration(2000)
                        .alpha(0)
                        .setDuration(2000)

                        .setListener(new ViewPropertyAnimatorListener() {
                            @Override
                            public void onAnimationStart(View view) {

                            }

                            @Override
                            public void onAnimationEnd(View view) {
                                scrollView.setVisibility(GONE);
                            }

                            @Override
                            public void onAnimationCancel(View view) {

                            }
                        })
                        .start();
            }
        }
        return awkenScrollBars;
    }
}
