package com.example.nerdy.piggame;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Nerdy on 7/6/2017.
 */

public class SettingsFragment extends PreferenceFragment {
    public void onCreate(Bundle savedState){
        super.onCreate(savedState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
