package com.example.nerd.piggame;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Nerdy on 7/6/2017.
 */

public class SettingsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}