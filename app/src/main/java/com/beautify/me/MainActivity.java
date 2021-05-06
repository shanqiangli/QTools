package com.beautify.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.beautify.qtools.brightness.BrightnessUtils;
import com.beautify.qtools.common.AppCommonUtils;
import com.beautify.qtools.common.ClickUtils;
import com.beautify.qtools.log.LogUtils;
import com.beautify.qtools.view.ActivityUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //当前亮度
        LogUtils.d("lsq---1>>>"+BrightnessUtils.getBrightness());
        LogUtils.d("lsq---2>>>"+BrightnessUtils.getWindowBrightness(this.getWindow()));
        LogUtils.d("lsq---3>>>"+BrightnessUtils.isAutoBrightnessEnabled());
        LogUtils.d("lsq---4>>>"+ AppCommonUtils.isAppDebug());
        LogUtils.d("lsq---5>>>"+ AppCommonUtils.getAppPath());
        LogUtils.d("lsq---6>>>"+ AppCommonUtils.getAppVersionName());
        LogUtils.d("lsq---7>>>"+ AppCommonUtils.getAppInfo().toString());
        LogUtils.d("lsq---8>>>"+ AppCommonUtils.getApkInfo(AppCommonUtils.getAppPath()));
//        for(AppUtils.AppInfo model:AppUtils.getAppsInfo()){
//            LogUtils.d("lsq---9>>>"+ model.toString());
//        }
        TextView view = findViewById(R.id.myclick);
        Button button = findViewById(R.id.myButton);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AppCommonUtils.launchAppDetailsSettings();
                ClickUtils.applyPressedViewScale(view,0.6f);
            }
        });
//
        ClickUtils.expandClickArea(button,0,0,120,120);
        ClickUtils.applySingleDebouncing(button, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickUtils.applyPressedBgDark(view);
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                ActivityUtils.startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}