package com.alsk.showcase.adapters;

import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.lang.ref.WeakReference;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class AbstractRecyclerViewAdapter<DATA, BINDING extends ViewDataBinding> extends RecyclerView.Adapter<BindableRecyclerViewHolder<BINDING>> {

    private ObservableList<DATA> data;

    public void init(@NonNull ObservableList<DATA> data) {
        this.data = data;
        this.data.addOnListChangedCallback(createChangesListener(new WeakReference<>(this)));
        notifyDataSetChanged();
    }

    private static <DATA, BINDING extends ViewDataBinding> ObservableList.OnListChangedCallback<ObservableList<DATA>> createChangesListener(final WeakReference<AbstractRecyclerViewAdapter<DATA, BINDING>> adapterRef) {
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

    public void addItem(DATA item) {
        ensureDataInitialised();
        data.add(item);
    }

    public void removeItem(DATA item) {
        ensureDataInitialised();
        data.remove(item);
    }

    public DATA getItem(int position) {
        ensureDataInitialised();
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        ensureDataInitialised();
        return data.size();
    }

    private void ensureDataInitialised() {
        if (data != null) { return; }
        throw new IllegalStateException("init(...) method wasn't called");
    }
}