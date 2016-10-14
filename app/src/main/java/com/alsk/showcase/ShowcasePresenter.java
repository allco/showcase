package com.alsk.showcase;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alsk.showcase.adapters.ShowcaseAdapter;
import com.alsk.showcase.databinding.ShowcaseFragmentBinding;
import com.alsk.showcase.model.ItemData;

import static com.alsk.showcase.adapters.ShowcaseAdapter.MODE_1;
import static com.alsk.showcase.adapters.ShowcaseAdapter.MODE_2;
import static com.alsk.showcase.adapters.ShowcaseAdapter.MODE_3;

public class ShowcasePresenter implements MenuItem.OnMenuItemClickListener {

    private static final int ITEMS_DEFAULT_COUNT = 10;

    @NonNull
    private Context context;
    @NonNull
    private final ShowcaseFragmentBinding binding;

    @NonNull
    private final ShowcaseAdapter adapter;

    public static ShowcasePresenter newInstance(@NonNull Context context, @NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        ShowcaseFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.showcase_fragment, container, false);
        return new ShowcasePresenter(context, binding, new ShowcaseAdapter());
    }

    ShowcasePresenter(Context context, @NonNull ShowcaseFragmentBinding binding, @NonNull ShowcaseAdapter adapter) {
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
}
