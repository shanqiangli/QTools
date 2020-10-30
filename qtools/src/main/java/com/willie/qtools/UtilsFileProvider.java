package com.willie.qtools;

import android.app.Application;

import androidx.core.content.FileProvider;

/**
 * <pre>
 *     desc  :
 * </pre>
 */
public class UtilsFileProvider extends FileProvider {

    @Override
    public boolean onCreate() {
        //noinspection ConstantConditions
        Utils.init((Application) getContext().getApplicationContext());
        return true;
    }
}
