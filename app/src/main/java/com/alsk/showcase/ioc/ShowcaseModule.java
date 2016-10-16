package com.alsk.showcase.ioc;

import com.alsk.showcase.R;
import com.alsk.showcase.adapters.ShowcaseAdapter;
import com.alsk.showcase.binding.AbstractListItemBindingWrapper;
import com.alsk.showcase.binding.AbstractListItemBindingWrapperImpl;
import com.alsk.showcase.binding.ShowcaseFragmentBindingWrapper;
import com.alsk.showcase.binding.ShowcaseFragmentBindingWrapperImpl;
import com.alsk.showcase.databinding.ShowcaseListItemCase1Binding;
import com.alsk.showcase.databinding.ShowcaseListItemCase2Binding;
import com.alsk.showcase.databinding.ShowcaseListItemCase3Binding;
import com.alsk.showcase.model.ItemData;
import com.alsk.showcase.model.ShowcaseModel;
import com.alsk.showcase.model.ShowcaseModelImpl;
import com.alsk.showcase.viewmodel.ShowcaseViewModel;
import com.alsk.showcase.viewmodel.ShowcaseViewModelImpl;

import javax.inject.Named;
import javax.inject.Provider;

import dagger.Module;
import dagger.Provides;

@SuppressWarnings("WeakerAccess")
@Module
public class ShowcaseModule {

    public static final String NAMED_CASE1 = "NAMED_CASE1";
    public static final String NAMED_CASE2 = "NAMED_CASE2";
    public static final String NAMED_CASE3 = "NAMED_CASE3";

    @Provides
    public ShowcaseViewModel provideShowcaseViewModel(ShowcaseComponent component) {return new ShowcaseViewModelImpl(component);}

    @Provides
    public ShowcaseModel provideShowcaseModel(ShowcaseComponent component) {return new ShowcaseModelImpl(component);}

    @Provides
    public ShowcaseFragmentBindingWrapper provideShowcaseFragmentBindingWrapper() {return new ShowcaseFragmentBindingWrapperImpl();}

    @Named(NAMED_CASE1)
    @Provides
    public AbstractListItemBindingWrapper provideCase1BindingWrapper() {
        return new AbstractListItemBindingWrapperImpl<ShowcaseListItemCase1Binding>(R.layout.showcase_list_item_case1) {
            @Override
            protected void innerSetModel(ShowcaseListItemCase1Binding binding, ItemData itemData) {
                binding.setModel(itemData);
            }
        };
    }

    @Named(NAMED_CASE2)
    @Provides
    public AbstractListItemBindingWrapper provideCase2BindingWrapper() {
        return new AbstractListItemBindingWrapperImpl<ShowcaseListItemCase2Binding>(R.layout.showcase_list_item_case2) {
            @Override
            protected void innerSetModel(ShowcaseListItemCase2Binding binding, ItemData itemData) {
                binding.setModel(itemData);
            }
        };
    }

    @Named(NAMED_CASE3)
    @Provides
    public AbstractListItemBindingWrapper provideCase3BindingWrapper() {
        return new AbstractListItemBindingWrapperImpl<ShowcaseListItemCase3Binding>(R.layout.showcase_list_item_case3) {
            @Override
            protected void innerSetModel(ShowcaseListItemCase3Binding binding, ItemData itemData) {
                binding.setModel(itemData);
            }
        };
    }

    @Named(NAMED_CASE1)
    @Provides
    public ShowcaseAdapter provideShowcaseAdapterCase1(@Named(NAMED_CASE1) Provider<AbstractListItemBindingWrapper> bindingWrapperProvider) {
        return new ShowcaseAdapter(bindingWrapperProvider);
    }

    @Named(NAMED_CASE2)
    @Provides
    public ShowcaseAdapter provideShowcaseAdapterCase2(@Named(NAMED_CASE2) Provider<AbstractListItemBindingWrapper> bindingWrapperProvider) {
        return new ShowcaseAdapter(bindingWrapperProvider);
    }

    @Named(NAMED_CASE3)
    @Provides
    public ShowcaseAdapter provideShowcaseAdapterCase3(@Named(NAMED_CASE3) Provider<AbstractListItemBindingWrapper> bindingWrapperProvider) {
        return new ShowcaseAdapter(bindingWrapperProvider);
    }
}
