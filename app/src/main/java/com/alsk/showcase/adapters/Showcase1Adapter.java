package com.alsk.showcase.adapters;

import com.alsk.showcase.R;
import com.alsk.showcase.databinding.ShowcaseListItemCase1Binding;

public class Showcase1Adapter extends AbstractShowcaseAdapter<ShowcaseListItemCase1Binding> {

    @Override
    protected int getItemLayout() {
        return R.layout.showcase_list_item_case1;
    }

    @Override
    public void onBindViewHolder(BindableRecyclerViewHolder<ShowcaseListItemCase1Binding> holder, int position) {
        ShowcaseListItemCase1Binding dataBinding = holder.getDataBinding();
        dataBinding.setModel(getItem(position));
    }
}

