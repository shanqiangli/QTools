package com.beautify.qtools.provider;

import android.app.Application;

import androidx.core.content.FileProvider;

import com.beautify.qtools.utils.Utils;

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
