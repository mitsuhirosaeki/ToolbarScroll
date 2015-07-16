package com.example.jonasan.toolbarscroll;


import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 *
 *
 */
public class WebFragment extends Fragment {

    @InjectView(R.id.webview)
    ObservableWebView webView;

//    @InjectView(R.id.comment_container)
//    FrameLayout comment;
//

//    @InjectView(R.id.linealayout)
//    LinearLayout linearLayout;

    private int mBaseTranslationY;
    private int count = 0;

    public static WebFragment newInstance() {
        WebFragment fragment = new WebFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public WebFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
//        fm.beginTransaction()
//                .add(R.id.edit_container, EditFragment.newInstance())
//                .commit();
//
        fm.beginTransaction()
                .add(R.id.comment_container, CommentFragment.newInstance())
                .commit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //webview
        webView.loadUrl("http://kinokoru.jp/archives/794");

        webView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            @Override
            public void onScrollChanged(int y, boolean first, boolean drugging) {
                if (drugging) {
                    int toolbarHeight = MainActivity.toolbar.getHeight();
                    if (first) {
//                        float currentHeaderTranslationY = MainActivity.toolbar.getTranslationY();
//                        if (-toolbarHeight < currentHeaderTranslationY && toolbarHeight < y) {
                        mBaseTranslationY = y;
//                        }
                    }
//                    int headerTranslationY = Math.min(0, Math.max(-toolbarHeight, -(y - mBaseTranslationY)));
//                    MainActivity.toolbar.setTranslationY(headerTranslationY);
                    int diff = y - mBaseTranslationY;
                    int translationY = 0 < diff
                            ? Math.max((int) MainActivity.toolbar.getY() - diff, -MainActivity.toolbar.getHeight())
                            : Math.min((int) MainActivity.toolbar.getY() - diff, 0);
                    MainActivity.toolbar.setTranslationY(translationY);
                    count++;
                    if (count > 1){mBaseTranslationY = y;}


                    ViewGroup.LayoutParams layoutParams = webView.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)layoutParams;
                    marginLayoutParams.setMargins(marginLayoutParams.leftMargin,30,marginLayoutParams.rightMargin,marginLayoutParams.bottomMargin);
                    webView.setLayoutParams(marginLayoutParams);


                }
            }

            @Override
            public void onDownMotionEvent() {
                count = 0;
            }

            @Override
            public void onUpOrCancelMotionEvent(ScrollState scrollState) {



            }


        });

//        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
//            @Override
//            public void onScrollChanged(int l, int t, int oldl, int oldt) {
//                int diff = t - oldt; // 縦軸の移動量
//
//                int translationY = 0 < diff
//                        ? Math.max((int) MainActivity.linearLayout.getY() - diff, -MainActivity.linearLayout.getHeight())
//                        : Math.min((int) MainActivity.linearLayout.getY() - diff, 0);
//
//                MainActivity.linearLayout.setTranslationY(translationY);
//            }
//        });

    }
}
