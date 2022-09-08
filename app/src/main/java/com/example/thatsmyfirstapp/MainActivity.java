package com.example.thatsmyfirstapp;

import static com.example.thatsmyfirstapp.util.FragmentNavigationHelper.openFragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.thatsmyfirstapp.fragment.HomeFragment;
import com.example.thatsmyfirstapp.fragment.ProfileFragment;
import com.example.thatsmyfirstapp.fragment.SettingsFragment;
import com.example.thatsmyfirstapp.util.FragmentNavigationHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        FragmentNavigationHelper.setManager(getSupportFragmentManager());
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    openFragment(homeFragment);
                    return true;
                case R.id.profile:
                    openFragment(profileFragment);
                    return true;
                case R.id.setting:
                    openFragment(settingsFragment);
                    return true;
            }
            return false;
        });
    }
}

