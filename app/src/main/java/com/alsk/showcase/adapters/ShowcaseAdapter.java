package com.alsk.showcase.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alsk.showcase.R;
import com.alsk.showcase.ShowcasePresenter;
import com.alsk.showcase.databinding.ShowcaseListItemBinding;
import com.alsk.showcase.model.ItemData;

import static com.alsk.showcase.ShowcasePresenter.MODE_1;

public class ShowcaseAdapter extends AbstractRecyclerViewAdapter<ItemData, ShowcaseListItemBinding> {

    @ShowcasePresenter.Mode
    private int mode = MODE_1;

    @Override
    public BindableRecyclerViewHolder<ShowcaseListItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ShowcaseListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.showcase_list_item, parent,
                false);

        return new BindableRecyclerViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(BindableRecyclerViewHolder<ShowcaseListItemBinding> holder, int position) {
        ShowcaseListItemBinding dataBinding = holder.getDataBinding();
        dataBinding.setModel(getItem(position));
        dataBinding.setMode(mode);
    }

    public void setMode(@ShowcasePresenter.Mode int mode) {
        if (this.mode == mode) {
            return;
        }
        this.mode = mode;
        notifyDataSetChanged();
    }

    public int getMode() {
        return mode;
    }
}
