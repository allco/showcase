package com.alsk.showcase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alsk.showcase.binding.ShowcaseFragmentBindingWrapper;
import com.alsk.showcase.ioc.DaggerShowcaseComponent;
import com.alsk.showcase.viewmodel.ShowcaseViewModel;

import javax.inject.Inject;

import static com.alsk.showcase.viewmodel.ShowcaseViewModelImpl.MODE_1;
import static com.alsk.showcase.viewmodel.ShowcaseViewModelImpl.MODE_2;
import static com.alsk.showcase.viewmodel.ShowcaseViewModelImpl.MODE_3;

public class ShowcaseFragment extends Fragment {

    private static final String STATE_MODE_KEY = "STATE_MODE_KEY";

    @Inject
    ShowcaseViewModel viewModel;

    @Inject
    ShowcaseFragmentBindingWrapper bindingWrapper;

    @Deprecated
    public ShowcaseFragment() {
        DaggerShowcaseComponent.builder().build().inject(this);
    }

    static public ShowcaseFragment newInstance() {
        @SuppressWarnings("deprecation")
        ShowcaseFragment fragment = new ShowcaseFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        //noinspection WrongConstant
        viewModel.setMode(getArguments().getInt(STATE_MODE_KEY, MODE_1));
        return bindingWrapper.init(inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.generateDummyData(getContext());
        bindingWrapper.setModel(viewModel);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.showcase_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showcase_fragment_menu_case1: viewModel.setMode(MODE_1); return true;
            case R.id.showcase_fragment_menu_case2: viewModel.setMode(MODE_2); return true;
            case R.id.showcase_fragment_menu_case3: viewModel.setMode(MODE_3); return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getArguments().putInt(STATE_MODE_KEY, viewModel.getMode());
        bindingWrapper = null;
        viewModel = null;
    }
}
