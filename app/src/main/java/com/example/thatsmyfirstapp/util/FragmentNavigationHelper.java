package com.example.thatsmyfirstapp.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.thatsmyfirstapp.R;

public class FragmentNavigationHelper {

    public static FragmentManager fragmentManager;


    public static void setManager(FragmentManager manager) {
        FragmentNavigationHelper.fragmentManager = manager;

    }

    public static void openFragment(Fragment fragment){
        fragmentManager.beginTransaction().replace(R.id.container,fragment).commit();
    }
}
