package com.alsk.showcase;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alsk.showcase.adapters.ShowcaseAdapter;
import com.alsk.showcase.databinding.ShowcaseFragmentBinding;
import com.alsk.showcase.model.ItemData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings("WeakerAccess")
public class ShowcasePresenter implements MenuItem.OnMenuItemClickListener {

    private static final int ITEMS_DEFAULT_COUNT = 10;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MODE_1, MODE_2, MODE_3})
    public @interface Mode {}

    public static final int MODE_1 = 1;
    public static final int MODE_2 = 2;
    public static final int MODE_3 = 3;

    @NonNull
    private Context context;
    @NonNull
    private final ShowcaseFragmentBinding binding;

    @NonNull
    private final ShowcaseAdapter adapter;

    public static ShowcasePresenter newInstance(@NonNull Context context, @NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        ShowcaseFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.showcase_fragment, container, false);
        return new ShowcasePresenter(context, binding, new ShowcaseAdapter());
    }

    ShowcasePresenter(@NonNull Context context, @NonNull ShowcaseFragmentBinding binding, @NonNull ShowcaseAdapter adapter) {
        this.context = context;
        this.binding = binding;
        this.adapter = adapter;
    }

    public View getRootView() {
        return binding.getRoot();
    }

    public void generateDummyData() {
        ObservableArrayList<ItemData> listItems = new ObservableArrayList<>();
        for (int i = 0; i < ITEMS_DEFAULT_COUNT; i++) {
            listItems.add(ItemData.newInstance(context.getString(R.string.item_dummy_title, i), context.getString(R.string.item_dummy_text)));
        }
        adapter.init(listItems);
        binding.setAdapter(adapter);
        binding.setMode(adapter.getMode());
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showcase_fragment_menu_case1: binding.setMode(MODE_1); return true;
            case R.id.showcase_fragment_menu_case2: binding.setMode(MODE_2); return true;
            case R.id.showcase_fragment_menu_case3: binding.setMode(MODE_3); return true;
        }
        return false;
    }

    @SuppressWarnings("unused")
    @BindingAdapter({"adapter", "mode"})
    public static void bindAdapter(RecyclerView view, @Nullable ShowcaseAdapter adapter, @Mode int mode) {

        if (adapter == null) {
            throw new IllegalStateException("call ((ShowcaseFragmentBinding)binding).setAdapter(adapter) is required");
        }

        adapter.setMode(mode);
        view.setLayoutManager(mode == MODE_3 ? new GridLayoutManager(view.getContext(), 2) : new LinearLayoutManager(view.getContext()));
        if (adapter != view.getAdapter()) {
            view.setAdapter(adapter);
        }
    }
}
