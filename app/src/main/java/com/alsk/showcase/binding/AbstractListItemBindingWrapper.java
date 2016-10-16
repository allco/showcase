package com.alsk.showcase.binding;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alsk.showcase.model.ItemData;

public interface AbstractListItemBindingWrapper {
    void init(LayoutInflater inflater, @Nullable ViewGroup container);
    void setModel(ItemData itemData);
    View getRoot();
}
