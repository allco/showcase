package com.alsk.showcase.adapters;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.alsk.showcase.binding.AbstractListItemBindingWrapper;

import java.lang.ref.WeakReference;

public abstract class AbstractRecyclerViewAdapter<DATA, BINDING extends AbstractListItemBindingWrapper> extends RecyclerView.Adapter<BindableRecyclerViewHolder<BINDING>> {

    private ObservableList<DATA> data;

    public void init(@NonNull ObservableList<DATA> data) {
        this.data = data;
        this.data.addOnListChangedCallback(createChangesListener(new WeakReference<>(this)));
        notifyDataSetChanged();
    }

    @SuppressWarnings("unused")
    public void addItem(DATA item) {
        data.add(item);
    }

    @SuppressWarnings("unused")
    public void removeItem(DATA item) {
        data.remove(item);
    }

    DATA getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static <DATA, BINDING extends AbstractListItemBindingWrapper> ObservableList.OnListChangedCallback<ObservableList<DATA>> createChangesListener(final WeakReference<AbstractRecyclerViewAdapter<DATA, BINDING>> adapterRef) {
        return new ObservableList.OnListChangedCallback<ObservableList<DATA>>() {
            @Override
            public void onChanged(ObservableList<DATA> sender) {
                AbstractRecyclerViewAdapter adapter = adapterRef == null ? null : adapterRef.get();
                if (adapter == null) {
                    return;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList<DATA> sender, int positionStart, int itemCount) {
                AbstractRecyclerViewAdapter adapter = adapterRef == null ? null : adapterRef.get();
                if (adapter == null) {
                    return;
                }
                adapter.notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(ObservableList<DATA> sender, int positionStart, int itemCount) {
                AbstractRecyclerViewAdapter adapter = adapterRef == null ? null : adapterRef.get();
                if (adapter == null) {
                    return;
                }
                adapter.notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList<DATA> sender, int fromPosition, int toPosition, int itemCount) {
                AbstractRecyclerViewAdapter adapter = adapterRef == null ? null : adapterRef.get();
                if (adapter == null) {
                    return;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemRangeRemoved(ObservableList<DATA> sender, int positionStart, int itemCount) {
                AbstractRecyclerViewAdapter adapter = adapterRef == null ? null : adapterRef.get();
                if (adapter == null) {
                    return;
                }
                adapter.notifyItemRangeRemoved(positionStart, itemCount);
            }
        };
    }
}