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

public class ShowcaseFragment extends Fragment {

    @Deprecated
    public ShowcaseFragment() {
    }

    static public ShowcaseFragment newInstance() {
        ShowcaseFragment fragment = new ShowcaseFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    ShowcasePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        presenter = ShowcasePresenter.newInstance(getActivity(), inflater, container);
        return presenter.getRootView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.generateDummyData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.showcase_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onMenuItemClick(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter = null;
    }
}
