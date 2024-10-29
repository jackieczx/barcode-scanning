package com.jck.scanning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.Scanning.R;
import com.jck.scan.BarcodeScanningActivity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.scanningBtn).setOnClickListener(v -> {
            Intent mIntent = new Intent(MainActivity.this, BarcodeScanningActivity.class);
            startActivity(mIntent);
        });


    }
}
