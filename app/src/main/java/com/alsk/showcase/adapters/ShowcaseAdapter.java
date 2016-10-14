package com.alsk.showcase.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alsk.showcase.R;
import com.alsk.showcase.databinding.ShowcaseListItemBinding;
import com.alsk.showcase.model.ItemData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ShowcaseAdapter extends AbstractRecyclerViewAdapter<ItemData, ShowcaseListItemBinding> {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MODE_1, MODE_2, MODE_3})
    public @interface Mode {}

    public static final int MODE_1 = 1;
    public static final int MODE_2 = 2;
    public static final int MODE_3 = 3;

    @Mode
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

    public void setMode(@Mode int mode) {
        if (this.mode == mode) {
            return;
        }
        this.mode = mode;
        notifyDataSetChanged();
    }

    public int getMode() {
        return mode;
    }

    @BindingAdapter({"adapter", "mode"})
    public static void bindAdapter(RecyclerView view, @Nullable ShowcaseAdapter adapter, @Mode int mode) {

        if (adapter == null) {
            throw new IllegalStateException("call ((ShowcaseFragmentBinding)binding).setAdapter(adapter) is required");
        }

        RecyclerView.LayoutManager layoutManager =
                mode == MODE_3 ? new GridLayoutManager(view.getContext(), 2) : new LinearLayoutManager(view.getContext());
        view.setLayoutManager(layoutManager);
        adapter.setMode(mode);
        view.setAdapter(adapter);
    }
}
