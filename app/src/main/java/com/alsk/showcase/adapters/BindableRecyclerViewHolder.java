package com.alsk.showcase.adapters;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

class BindableRecyclerViewHolder<DATABINDING extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private DATABINDING dataBinding;

    BindableRecyclerViewHolder(DATABINDING dataBinding) {
        super(dataBinding.getRoot());
        this.dataBinding = dataBinding;
    }

    DATABINDING getDataBinding() {
        return dataBinding;
    }
}
