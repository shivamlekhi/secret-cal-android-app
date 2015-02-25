package com.leagapps.secretcal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Sam on 7/16/2014.
 */
public class AboutActivity extends Activity {

    LinearLayout AboutItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        AboutItem = (LinearLayout) findViewById(R.id.about_item);
        AboutItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent Notes = new Intent(AboutActivity.this, NotesActivity.class);
                startActivity(Notes);
                return true;
            }
        });
    }
}
