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

import static com.alsk.showcase.ShowcasePresenter.MODE_1;
import static com.alsk.showcase.ShowcasePresenter.MODE_2;
import static com.alsk.showcase.ShowcasePresenter.MODE_3;

public class ShowcaseFragment extends Fragment {

    public static final String STATE_MODE_KEY = "STATE_MODE_KEY";

    @Deprecated
    public ShowcaseFragment() {
    }

    static public ShowcaseFragment newInstance() {
        @SuppressWarnings("deprecation")
        ShowcaseFragment fragment = new ShowcaseFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    ShowcasePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        //noinspection WrongConstant
        presenter = ShowcasePresenter.newInstance(getActivity(), inflater, container, getArguments().getInt(STATE_MODE_KEY, MODE_1));
        return presenter.getRootView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.generateDummyData(getContext());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.showcase_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showcase_fragment_menu_case1: presenter.setMode(MODE_1); return true;
            case R.id.showcase_fragment_menu_case2: presenter.setMode(MODE_2); return true;
            case R.id.showcase_fragment_menu_case3: presenter.setMode(MODE_3); return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getArguments().putInt(STATE_MODE_KEY, presenter.getCurrentMode());
        presenter = null;
    }
}
