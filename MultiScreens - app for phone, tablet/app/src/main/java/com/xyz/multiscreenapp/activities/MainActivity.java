package com.xyz.multiscreenapp.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.multiscreenapp.fragments.AndroidVersionFragment;
import com.xyz.multiscreenapp.listeners.FragmentCommunicationListener;
import com.xyz.multiscreenapp.R;
import com.xyz.multiscreenapp.fragments.VersionDetailsFragment;
import com.xyz.multiscreenapp.model.VersionModel;

public class MainActivity extends AppCompatActivity implements FragmentCommunicationListener {

    private boolean isDualContainerPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout = findViewById(R.id.versionContainer);
        if (frameLayout != null) {
            isDualContainerPresent = true;
        }

        if (isDualContainerPresent) {
            launchAndroidVersionFragment(R.id.versionContainer);
        } else {
            launchAndroidVersionFragment(R.id.container);
        }
    }

    private void launchAndroidVersionFragment(int containerId) {
        AndroidVersionFragment androidVersionFragment = new AndroidVersionFragment();
        getSupportFragmentManager().beginTransaction().replace(containerId, androidVersionFragment, "AndroidVersionFragment").commit();
    }

    @Override
    public void onDataPassed(VersionModel model) {
        VersionDetailsFragment versionDetailsFragment = new VersionDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("versionName", model.getVersionName());
        bundle.putString("description", model.getVersionDescription());
        versionDetailsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.container, versionDetailsFragment, "VersionDetail").commit();
    }
}