package com.alsk.showcase.binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alsk.showcase.model.ItemData;

public abstract class AbstractListItemBindingWrapperImpl<BINDING extends ViewDataBinding> implements AbstractListItemBindingWrapper {

    private BINDING  binding;
    private final int layout;

    protected AbstractListItemBindingWrapperImpl(@LayoutRes int layout) {
        this.layout = layout;
    }

    @Override
    public void init(LayoutInflater inflater, @Nullable ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, layout, container, false);
    }

    @Override
    public View getRoot() {
        ensureInitialized();
        return binding.getRoot();
    }

    @Override
    public void setModel(ItemData itemData) {
        ensureInitialized();
        innerSetModel(binding, itemData);
    }

    protected abstract void innerSetModel(BINDING binding, ItemData itemData);

    private void ensureInitialized() {
        if (binding == null || binding.getRoot() == null) {
            throw new IllegalStateException("init() call is required");
        }
    }
}
