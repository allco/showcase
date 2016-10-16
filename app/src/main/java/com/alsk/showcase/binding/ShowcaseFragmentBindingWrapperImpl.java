package com.alsk.showcase.binding;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alsk.showcase.R;
import com.alsk.showcase.databinding.ShowcaseFragmentBinding;
import com.alsk.showcase.viewmodel.ShowcaseViewModel;

public class ShowcaseFragmentBindingWrapperImpl implements ShowcaseFragmentBindingWrapper {

    private ShowcaseFragmentBinding binding;

    @Override
    public View init(LayoutInflater inflater, @Nullable ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.showcase_fragment, container, false);
        ensureInitialized();
        return binding.getRoot();
    }

    @Override
    public void setModel(ShowcaseViewModel viewModel) {
        ensureInitialized();
        binding.setModel(viewModel);
    }

    private void ensureInitialized() {
        if (binding == null || binding.getRoot() == null) {
            throw new IllegalStateException("init() call is required");
        }
    }
}
