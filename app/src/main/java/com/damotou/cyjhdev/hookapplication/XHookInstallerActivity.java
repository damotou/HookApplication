package com.damotou.cyjhdev.hookapplication;

import android.content.Intent;
import android.os.Bundle;

import com.damotou.cyjhdev.hookapplication.util.RootUtil;

import java.util.HashMap;

/**
 * Created by cyjhdev on 15-7-6.
 */
public class XHookInstallerActivity extends XHookDropdownNavActivity {

    public static final String EXTRA_SECTION = "section";
    public static final String EXTRA_SECTION_LEGACY = "opentab";

    private static final HashMap<String, Integer> TABS;
    static {
        TABS = new HashMap<String, Integer>(TAB_COUNT, 1);
        TABS.put("install", TAB_INSTALL);
        TABS.put("about", TAB_ABOUT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectInitialTab(getIntent(), savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("section", getActionBar().getSelectedNavigationIndex());
    }

    private void selectInitialTab(Intent intent, Bundle savedInstanceState) {
        int selectTabIndex = -1;

        Bundle extras = intent.getExtras();
        if (extras != null) {
            Object section = extras.get(EXTRA_SECTION);
            if (section == null)
                section = extras.get(EXTRA_SECTION_LEGACY);

            if (section instanceof Integer) {
                selectTabIndex = (Integer) section;
            } else if (section instanceof String && TABS.containsKey(section)) {
                selectTabIndex = TABS.get(section);
            }
        }

        if (selectTabIndex == -1  && savedInstanceState != null)
            selectTabIndex = savedInstanceState.getInt("section", -1);

        if (selectTabIndex >= 0 && selectTabIndex < TAB_COUNT)
            getActionBar().setSelectedNavigationItem(selectTabIndex);
    }
}
