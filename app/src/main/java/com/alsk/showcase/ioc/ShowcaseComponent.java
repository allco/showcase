package com.alsk.showcase.ioc;

import com.alsk.showcase.ShowcaseFragment;
import com.alsk.showcase.model.ShowcaseModelImpl;
import com.alsk.showcase.viewmodel.ShowcaseViewModelImpl;

import dagger.Component;

@Component(modules = {ShowcaseModule.class})
public interface ShowcaseComponent {
    void inject(ShowcaseFragment fragment);
    void inject(ShowcaseViewModelImpl showcaseViewModel);
    void inject(ShowcaseModelImpl showcaseModel);
}
