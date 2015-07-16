package com.example.jonasan.toolbarscroll;

import android.app.FragmentManager;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

//    @InjectView(R.id.toolbar)
    public static Toolbar toolbar;
    @InjectView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        //toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.CYAN);

        //framelayout
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .add(R.id.containor, WebFragment.newInstance())
                .commit();



    }


}
