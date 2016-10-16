package com.alsk.showcase.binding;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alsk.showcase.viewmodel.ShowcaseViewModel;

public interface ShowcaseFragmentBindingWrapper {
    View init(LayoutInflater inflater, @Nullable ViewGroup container);
    void setModel(ShowcaseViewModel viewModel);
}
