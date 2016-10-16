package com.alsk.showcase;

import android.content.Context;
import android.databinding.ObservableList;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;

import com.alsk.showcase.model.ItemData;

public class ShowcaseModel extends AsyncTask<Void,ItemData,Void> {

    private final Context context;
    private final ObservableList<ItemData> listItems;

    ShowcaseModel(Context context, ObservableList<ItemData> list) {
        this.context = context;
        this.listItems = list;
    }

    public static ShowcaseModel generate(Context context, ObservableList<ItemData> list) {
        ShowcaseModel model = new ShowcaseModel(context, list);
        model.execute();
        return model;
    }

    @Override
    protected void onProgressUpdate(ItemData... values) {
        super.onProgressUpdate(values);
        listItems.add(values[0]);
    }

    @MainThread
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @WorkerThread
    @Override
    protected Void doInBackground(Void[] params) {
        for (int i = 0; i < ShowcaseViewModel.ITEMS_DEFAULT_COUNT; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(ItemData.newInstance(context.getString(R.string.item_dummy_title, i), context.getString(R.string.item_dummy_text)));
        }
        return null;
    }
}