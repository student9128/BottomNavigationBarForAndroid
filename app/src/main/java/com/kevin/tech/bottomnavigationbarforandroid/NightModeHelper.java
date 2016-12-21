package com.kevin.tech.bottomnavigationbarforandroid;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;


public class NightModeHelper
{

    private static final String PREF_KEY = "nightModeState";

    private static int sUiNightMode = Configuration.UI_MODE_NIGHT_UNDEFINED;

    private WeakReference<Activity> mActivity;

    private SharedPreferences mPrefs;


    public NightModeHelper(Activity activity)
    {

        int currentMode = (activity.getResources().getConfiguration()
                .uiMode & Configuration.UI_MODE_NIGHT_MASK);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(activity);
        init(activity, -1, mPrefs.getInt(PREF_KEY, currentMode));
    }

   
    public NightModeHelper(Activity activity, int theme)
    {

        int currentMode = (activity.getResources().getConfiguration()
                .uiMode & Configuration.UI_MODE_NIGHT_MASK);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(activity);
        init(activity, theme, mPrefs.getInt(PREF_KEY, currentMode));
    }


    public NightModeHelper(Activity activity, int theme, int defaultUiMode)
    {

        init(activity, theme, defaultUiMode);
    }

    private void init(Activity activity, int theme, int defaultUiMode)
    {

        mActivity = new WeakReference<Activity>(activity);
        if (sUiNightMode == Configuration.UI_MODE_NIGHT_UNDEFINED)
        {
            sUiNightMode = defaultUiMode;
        }
        updateConfig(sUiNightMode);

        if (theme != -1)
        {
            activity.setTheme(theme);
        }
    }

    private void updateConfig(int uiNightMode)
    {

        Activity activity = mActivity.get();
        if (activity == null)
        {
            throw new IllegalStateException("Activity went away?");
        }
        Configuration newConfig = new Configuration(activity.getResources().getConfiguration());
        newConfig.uiMode &= ~Configuration.UI_MODE_NIGHT_MASK;
        newConfig.uiMode |= uiNightMode;
        activity.getResources().updateConfiguration(newConfig, null);
        sUiNightMode = uiNightMode;
        if (mPrefs != null)
        {
            mPrefs.edit()
                    .putInt(PREF_KEY, sUiNightMode)
                    .apply();
        }
    }

    public static int getUiNightMode()
    {

        return sUiNightMode;
    }

    public void toggle()
    {

        if (sUiNightMode == Configuration.UI_MODE_NIGHT_YES)
        {
            notNight();
        } else
        {
            night();
        }
    }


    public void notNight()
    {

        updateConfig(Configuration.UI_MODE_NIGHT_NO);
        System.gc();
        System.runFinalization(); 
        System.gc();
        mActivity.get().recreate();
    }

    public void night()
    {

        updateConfig(Configuration.UI_MODE_NIGHT_YES);
        System.gc();
        System.runFinalization(); // added in https://github.com/android/platform_frameworks_base/commit/6f3a38f3afd79ed6dddcef5c83cb442d6749e2ff
        System.gc();
        mActivity.get().recreate();
    }
}