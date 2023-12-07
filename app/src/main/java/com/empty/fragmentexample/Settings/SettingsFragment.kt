package com.empty.fragmentexample.Settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.empty.fragmentexample.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}