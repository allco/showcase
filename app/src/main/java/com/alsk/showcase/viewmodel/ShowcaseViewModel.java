package com.alsk.showcase.viewmodel;

import android.content.Context;
import android.databinding.Observable;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ShowcaseViewModel extends Observable{

    int MODE_1 = 1;
    int MODE_2 = 2;
    int MODE_3 = 3;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MODE_1, MODE_2, MODE_3})
    @interface Mode {}

    @Mode
    int getMode();
    void setMode(@Mode int mode);
    void generateDummyData(Context context);
}
