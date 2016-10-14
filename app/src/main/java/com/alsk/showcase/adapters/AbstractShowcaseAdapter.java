package com.alsk.showcase.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alsk.showcase.model.ItemData;

import java.util.ArrayList;

public abstract class AbstractShowcaseAdapter<DATA_BINDING extends ViewDataBinding> extends RecyclerView.Adapter<BindableRecyclerViewHolder<DATA_BINDING>> {

    @Nullable
    private ArrayList<ItemData> data;

    public void init(@NonNull ArrayList<ItemData> data) {
        this.data = data;
    }

    public void init(@Nullable AbstractShowcaseAdapter<?> adapter) {
        this.data = adapter == null ? null : adapter.data;
    }

    @Override
    public BindableRecyclerViewHolder<DATA_BINDING> onCreateViewHolder(ViewGroup parent, int viewType) {
        DATA_BINDING binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                getItemLayout(), parent,
                false);

        return new BindableRecyclerViewHolder<>(binding);
    }

    @LayoutRes
    protected abstract int getItemLayout();

    @Nullable
    public ItemData getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
