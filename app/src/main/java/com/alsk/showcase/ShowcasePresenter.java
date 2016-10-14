package com.alsk.showcase;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
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

import com.alsk.showcase.adapters.AbstractShowcaseAdapter;
import com.alsk.showcase.adapters.Showcase1Adapter;
import com.alsk.showcase.adapters.Showcase2Adapter;
import com.alsk.showcase.adapters.Showcase3Adapter;
import com.alsk.showcase.databinding.ShowcaseFragmentBinding;
import com.alsk.showcase.model.ItemData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

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

    @Mode
    private int currentMode;

    public static ShowcasePresenter newInstance(@NonNull Context context, @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Mode int mode) {
        ShowcaseFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.showcase_fragment, container, false);
        return new ShowcasePresenter(context, binding, mode);
    }

    private ShowcasePresenter(@NonNull Context context, @NonNull ShowcaseFragmentBinding binding, @Mode int mode) {
        this.context = context;
        this.binding = binding;
        this.currentMode = mode;
    }

    public View getRootView() {
        return binding.getRoot();
    }

    public void generateDummyData() {
        ArrayList<ItemData> listItems = new ArrayList<>();
        for (int i = 0; i < ITEMS_DEFAULT_COUNT; i++) {
            listItems.add(ItemData.newInstance(context.getString(R.string.item_dummy_title, i), context.getString(R.string.item_dummy_text)));
        }
        Showcase1Adapter adapter = new Showcase1Adapter();
        adapter.init(listItems);
        binding.setAdapter(adapter);
        binding.setMode(currentMode);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showcase_fragment_menu_case1: setMode(MODE_1); return true;
            case R.id.showcase_fragment_menu_case2: setMode(MODE_2); return true;
            case R.id.showcase_fragment_menu_case3: setMode(MODE_3); return true;
        }
        return false;
    }

    private void setMode(@Mode int mode) {
        currentMode = mode;
        binding.setMode(mode);
    }

    @Mode
    public int getCurrentMode() {
        return currentMode;
    }

    @BindingAdapter({"adapter", "mode"})
    public static void bindAdapter(RecyclerView view, @Nullable AbstractShowcaseAdapter adapter, @Mode int mode) {

        if (adapter == null) {
            throw new IllegalStateException("call ((ShowcaseFragmentBinding)binding).setAdapter(adapter) is required");
        }

        AbstractShowcaseAdapter showcaseAdapter;
        switch (mode) {
            case MODE_1: showcaseAdapter = new Showcase1Adapter(); break;
            case MODE_2: showcaseAdapter = new Showcase2Adapter(); break;
            case MODE_3: showcaseAdapter = new Showcase3Adapter(); break;
            default:
                throw new IllegalStateException("Unknown mode");
        }
        //noinspection unchecked
        showcaseAdapter.init(adapter);
        view.setLayoutManager(mode == MODE_3 ? new GridLayoutManager(view.getContext(), 2) : new LinearLayoutManager(view.getContext()));
        view.setAdapter(showcaseAdapter);
    }
}
