package com.alsk.showcase.adapters;

import android.support.v7.widget.RecyclerView;

import com.alsk.showcase.binding.AbstractListItemBindingWrapper;

class BindableRecyclerViewHolder<DATABINDING extends AbstractListItemBindingWrapper> extends RecyclerView.ViewHolder {

    private final DATABINDING dataBinding;

    BindableRecyclerViewHolder(DATABINDING dataBinding) {
        super(dataBinding.getRoot());
        this.dataBinding = dataBinding;
    }

    DATABINDING getDataBinding() {
        return dataBinding;
    }
}
