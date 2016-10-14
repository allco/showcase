package com.alsk.showcase.adapters;

import com.alsk.showcase.R;
import com.alsk.showcase.databinding.ShowcaseListItemCase3Binding;

public class Showcase3Adapter extends AbstractShowcaseAdapter<ShowcaseListItemCase3Binding> {

    @Override
    protected int getItemLayout() {
        return R.layout.showcase_list_item_case3;
    }

    @Override
    public void onBindViewHolder(BindableRecyclerViewHolder<ShowcaseListItemCase3Binding> holder, int position) {
        ShowcaseListItemCase3Binding dataBinding = holder.getDataBinding();
        dataBinding.setModel(getItem(position));
    }
}

