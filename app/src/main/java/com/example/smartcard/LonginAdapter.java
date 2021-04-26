package com.example.smartcard;

import android.content.ContentValues;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LonginAdapter extends FragmentPagerAdapter {
    private Context context;
    int totaltabs;

    public LonginAdapter(FragmentManager fm, Context context, int totaltabs) {
        super(fm);
        this.context = context;
        this.totaltabs = totaltabs;
    }

    @Override
    public int getCount() {
        return totaltabs;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                logintab_frag_action logintab_frag = new logintab_frag_action();
                return logintab_frag;
            case 1:
                signup_frag_action signup_frag = new signup_frag_action();
                return signup_frag;
            default:
                return null;
        }
    }
}
