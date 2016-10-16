package com.alsk.showcase.adapters;

import com.alsk.showcase.R;
import com.alsk.showcase.databinding.ShowcaseListItemCase2Binding;
import com.alsk.showcase.model.ItemData;

public class Showcase2Adapter extends AbstractRecyclerViewAdapter<ItemData, ShowcaseListItemCase2Binding> {

    @Override
    protected int getItemLayout() {
        return R.layout.showcase_list_item_case2;
    }

    @Override
    public void onBindViewHolder(BindableRecyclerViewHolder<ShowcaseListItemCase2Binding> holder, int position) {
        ShowcaseListItemCase2Binding dataBinding = holder.getDataBinding();
        dataBinding.setModel(getItem(position));
    }
}

