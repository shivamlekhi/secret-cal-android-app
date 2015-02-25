package com.leagapps.secretcal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

/**
 * Created by Sam on 8/10/2014.
 */
public class IntroActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewPager pager = (ViewPager) findViewById(R.id.intro_viewpager);
    }
}
