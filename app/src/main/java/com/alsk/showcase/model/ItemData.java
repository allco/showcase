package com.alsk.showcase.model;

import android.support.annotation.NonNull;

public class ItemData {

    @NonNull
    private final String title;
    @NonNull
    private final String text;

    private ItemData(@NonNull String title, @NonNull String text) {
        this.title = title;
        this.text = text;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getText() {
        return text;
    }

    @NonNull
    public static ItemData newInstance(@NonNull String title, @NonNull String text) {

        return new ItemData(title, text);
    }
}
