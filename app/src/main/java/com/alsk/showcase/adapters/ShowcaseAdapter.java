package com.alsk.showcase.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alsk.showcase.binding.AbstractListItemBindingWrapper;
import com.alsk.showcase.model.ItemData;

import javax.inject.Inject;
import javax.inject.Provider;

public class ShowcaseAdapter extends AbstractRecyclerViewAdapter<ItemData, AbstractListItemBindingWrapper> {

    private final Provider<AbstractListItemBindingWrapper> bindingWrapperProvider;

    @Inject
    public ShowcaseAdapter(Provider<AbstractListItemBindingWrapper> bindingWrapperProvider) {
        this.bindingWrapperProvider = bindingWrapperProvider;
    }

    @Override
    public BindableRecyclerViewHolder<AbstractListItemBindingWrapper> onCreateViewHolder(ViewGroup parent, int viewType) {
        AbstractListItemBindingWrapper bindingWrapper = bindingWrapperProvider.get();
        bindingWrapper.init(LayoutInflater.from(parent.getContext()), parent);
        return new BindableRecyclerViewHolder<>(bindingWrapper);
    }

    @Override
    public void onBindViewHolder(BindableRecyclerViewHolder<AbstractListItemBindingWrapper> holder, int position) {
        holder.getDataBinding().setModel(getItem(position));
    }
}
