package com.alsk.showcase.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

public abstract class AbstractRecyclerViewAdapter<DATA, BINDING extends ViewDataBinding> extends RecyclerView.Adapter<BindableRecyclerViewHolder<BINDING>> {

    private ObservableList<DATA> data;

    public void init(@NonNull ObservableList<DATA> data) {
        this.data = data;
        this.data.addOnListChangedCallback(createChangesListener(new WeakReference<>(this)));
        notifyDataSetChanged();
    }

    @Override
    public BindableRecyclerViewHolder<BINDING> onCreateViewHolder(ViewGroup parent, int viewType) {
        BINDING binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                getItemLayout(), parent,
                false);
        return new BindableRecyclerViewHolder<>(binding);
    }

    @LayoutRes
    protected abstract int getItemLayout();

    public void addItem(DATA item) {
        data.add(item);
    }

    public void removeItem(DATA item) {
        data.remove(item);
    }

    public DATA getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static <DATA, BINDING extends ViewDataBinding, VIEWMODEL> ObservableList.OnListChangedCallback<ObservableList<DATA>> createChangesListener(final WeakReference<AbstractRecyclerViewAdapter<DATA, BINDING>> adapterRef) {
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