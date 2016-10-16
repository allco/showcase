package com.alsk.showcase.model;

import android.content.Context;
import android.databinding.ObservableList;

public interface ShowcaseModel {
    void generate(Context context, ObservableList<ItemData> list);
}
