package com.example.nerd.piggame;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Nerdy on 7/6/2017.
 */

public class DoubleDownFragment extends PreferenceFragment {
    public void onCreate(Bundle savedState){
        super.onCreate(savedState);
        addPreferencesFromResource(R.xml.preferences);
    }
}