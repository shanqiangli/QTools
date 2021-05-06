package com.beautify.me;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.beautify.qtools.common.ClickUtils;

public class SecondActivity extends Activity implements ClickUtils.Back2HomeFriendlyListener {
    @Override
    public void show(CharSequence text, long duration) {

    }

    @Override
    public void dismiss() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView view = findViewById(R.id.myclick);
        Button button = findViewById(R.id.myButton);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AppCommonUtils.launchAppDetailsSettings();
                finish();
            }
        });
        ClickUtils.back2HomeFriendly("ddddddddd");
    }
}
