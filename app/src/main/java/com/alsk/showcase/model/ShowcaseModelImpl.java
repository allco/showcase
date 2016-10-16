package com.alsk.showcase.model;

import android.content.Context;
import android.databinding.ObservableList;
import android.os.AsyncTask;
import android.support.annotation.WorkerThread;

import com.alsk.showcase.R;
import com.alsk.showcase.ioc.ShowcaseComponent;

import javax.inject.Inject;

public class ShowcaseModelImpl extends AsyncTask<Void,ItemData,Void> implements ShowcaseModel {

    private static final int ITEMS_DEFAULT_COUNT = 10;

    private Context context;
    private ObservableList<ItemData> listItems;

    @Inject
    public ShowcaseModelImpl(ShowcaseComponent component) {
        component.inject(this);
    }

    @Override
    public void generate(Context context, ObservableList<ItemData> list) {
        this.context = context;
        this.listItems = list;
        execute();
    }

    @Override
    protected void onProgressUpdate(ItemData... values) {
        super.onProgressUpdate(values);
        // insert item at arbitrary place
        int index = (int) Math.max(0, Math.round(Math.random() * (listItems.size() - 1)));
        listItems.add(index, values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // detach all data
        listItems = null;
        context = null;
    }

    @WorkerThread
    @Override
    protected Void doInBackground(Void[] params) {
        for (int i = 0; i < ITEMS_DEFAULT_COUNT; i++) {
            publishProgress(ItemData.newInstance(context.getString(R.string.item_dummy_title, i), context.getString(R.string.item_dummy_text)));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}