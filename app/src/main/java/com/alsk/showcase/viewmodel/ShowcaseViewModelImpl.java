package com.alsk.showcase.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.PropertyChangeRegistry;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alsk.showcase.BR;
import com.alsk.showcase.adapters.AbstractRecyclerViewAdapter;
import com.alsk.showcase.adapters.ShowcaseAdapter;
import com.alsk.showcase.ioc.ShowcaseComponent;
import com.alsk.showcase.model.ItemData;
import com.alsk.showcase.model.ShowcaseModel;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import static com.alsk.showcase.ioc.ShowcaseModule.NAMED_CASE1;
import static com.alsk.showcase.ioc.ShowcaseModule.NAMED_CASE2;
import static com.alsk.showcase.ioc.ShowcaseModule.NAMED_CASE3;

public class ShowcaseViewModelImpl implements ShowcaseViewModel {

    @Inject
    ShowcaseModel model;

    @Inject
    @Named(NAMED_CASE1)
    Provider<ShowcaseAdapter> adapterProviderCase1;

    @Inject
    @Named(NAMED_CASE2)
    Provider<ShowcaseAdapter> adapterProviderCase2;

    @Inject
    @Named(NAMED_CASE3)
    Provider<ShowcaseAdapter> adapterProviderCase3;

    @Mode
    private int mode = MODE_1;
    private ObservableArrayList<ItemData> listItems = null;
    private final PropertyChangeRegistry changeRegistry = new PropertyChangeRegistry();

    @Inject
    public ShowcaseViewModelImpl(ShowcaseComponent component) {
        component.inject(this);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        changeRegistry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        changeRegistry.remove(callback);
    }

    @Mode
    @Override
    public int getMode() {
        return mode;
    }

    @Override
    public void setMode(@Mode int mode) {
        this.mode = mode;
        changeRegistry.notifyChange(this, BR._all);
    }

    @Override
    public void generateDummyData(final Context context) {
        if (listItems != null) {
            // generate only once
            return;
        }

        listItems = new ObservableArrayList<>();
        model.generate(context, listItems);
    }

    @BindingAdapter("model")
    public static void bindAdapter(RecyclerView view, ShowcaseViewModel model) {

        ShowcaseViewModelImpl viewModel = model instanceof ShowcaseViewModelImpl ? (ShowcaseViewModelImpl) model : null;

        if (viewModel == null || viewModel.listItems == null) {
            view.setAdapter(null);
            return;
        }

        @Mode int mode = viewModel.getMode();
        AbstractRecyclerViewAdapter showcaseAdapter;
        switch (mode) {
            case ShowcaseViewModelImpl.MODE_1: showcaseAdapter = viewModel.adapterProviderCase1.get(); break;
            case ShowcaseViewModelImpl.MODE_2: showcaseAdapter = viewModel.adapterProviderCase2.get(); break;
            case ShowcaseViewModelImpl.MODE_3: showcaseAdapter = viewModel.adapterProviderCase3.get(); break;
            default:
                throw new IllegalStateException("Unknown mode");
        }
        //noinspection unchecked
        showcaseAdapter.init(viewModel.listItems);
        view.setLayoutManager(mode == ShowcaseViewModelImpl.MODE_3 ? new GridLayoutManager(view.getContext(), 2) : new LinearLayoutManager(view.getContext()));
        view.setAdapter(showcaseAdapter);
    }
}
