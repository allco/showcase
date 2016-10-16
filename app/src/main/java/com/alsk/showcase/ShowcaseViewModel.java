package com.alsk.showcase;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.IntDef;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alsk.showcase.adapters.AbstractRecyclerViewAdapter;
import com.alsk.showcase.adapters.Showcase1Adapter;
import com.alsk.showcase.adapters.Showcase2Adapter;
import com.alsk.showcase.adapters.Showcase3Adapter;
import com.alsk.showcase.model.ItemData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ShowcaseViewModel implements Observable {

    public static final int MODE_1 = 1;
    public static final int MODE_2 = 2;
    public static final int MODE_3 = 3;
    static final int ITEMS_DEFAULT_COUNT = 10;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MODE_1, MODE_2, MODE_3})
    public @interface Mode {}

    @Mode
    private int mode = MODE_1;

    private ShowcaseModel model;
    private ObservableArrayList<ItemData> listItems = new ObservableArrayList<>();
    private PropertyChangeRegistry changeRegistry = new PropertyChangeRegistry();

    public static ShowcaseViewModel newInstance(@Mode int mode) {
        ShowcaseViewModel viewModel = new ShowcaseViewModel();
        viewModel.setMode(mode);
        return viewModel;
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
    public int getMode() {
        return mode;
    }

    public void setMode(@Mode int mode) {
        this.mode = mode;
        changeRegistry.notifyChange(this, BR._all);
    }

    public void generateDummyData(final Context context) {
        if (model != null) {
            // generate only once
            return;
        }
        model = ShowcaseModel.generate(context, listItems);
    }

    @BindingAdapter("model")
    public static void bindAdapter(RecyclerView view, ShowcaseViewModel viewModel) {

        @Mode int mode = viewModel.getMode();
        AbstractRecyclerViewAdapter showcaseAdapter;
        switch (mode) {
            case ShowcaseViewModel.MODE_1: showcaseAdapter = new Showcase1Adapter(); break;
            case ShowcaseViewModel.MODE_2: showcaseAdapter = new Showcase2Adapter(); break;
            case ShowcaseViewModel.MODE_3: showcaseAdapter = new Showcase3Adapter(); break;
            default:
                throw new IllegalStateException("Unknown mode");
        }
        //noinspection unchecked
        showcaseAdapter.init(viewModel.listItems);
        view.setLayoutManager(mode == ShowcaseViewModel.MODE_3 ? new GridLayoutManager(view.getContext(), 2) : new LinearLayoutManager(view.getContext()));
        view.setAdapter(showcaseAdapter);
    }
}
