package org.roszonelib.notetools.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;

import org.roszonelib.notetools.R;

import java.util.ArrayList;
import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemViewHolder> {
    private MaterialDialog mDialog = null;

    public enum ListItemType {
        ONLY_TITTLE,
        SINGLE_LINE,
        TWO_LINE,
        THREE_LINE
    }

    private List<ListItem> mItems;
    private OnListItemClickListener mListener;

    public ListItemAdapter(List<ListItem> items) {
        mItems = items;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = getLayoutFromType(viewType);
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ListItemViewHolder(v);

    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType().ordinal();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private int getLayoutFromType(int type) {
        ListItemType i = ListItemType.values()[type];
        switch (i) {
            case SINGLE_LINE:
                return R.layout.itemview_single;
            case TWO_LINE:
                return R.layout.itemview_double;
            case THREE_LINE:
                return R.layout.itemview_triple;
            default:
                return R.layout.itemview_tittle;
        }
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        ListItem item = mItems.get(position);
        holder.setValues(item);
        holder.setIcons(item);
        setItemListener(holder, item, position);
    }

    private void setItemListener(ListItemViewHolder holder, final ListItemAdapter.ListItem item, final int position) {
        holder.itemView.setOnClickListener(item.getType() == ListItemAdapter.ListItemType.ONLY_TITTLE ? null : new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(item, position, mDialog);
                }
            }
        });
    }

    public ListItemAdapter setOnClickListener(OnListItemClickListener listener) {
        mListener = listener;
        return this;
    }

    public ListItemAdapter setDialog(MaterialDialog dialog) {
        mDialog = dialog;
        return this;
    }

    public ListItemAdapter addItems(String tittle, List<ListItem> items) {
        mItems.add(new ListItem(ListItemType.ONLY_TITTLE, tittle));
        mItems.addAll(items);
        notifyDataSetChanged();
        return this;
    }

    public interface OnListItemClickListener {
        void onClick(ListItem item, int position, MaterialDialog dialog);
    }

    public static class builder {
        private List<ListItem> mItems;
        private Context mContext;

        public builder(Context context) {
            mContext = context;
            mItems = new ArrayList<>();
        }

        public builder addSingleListItem(int resId, GoogleMaterial.Icon icon) {
            ListItem item = new ListItem(ListItemType.SINGLE_LINE, mContext.getString(resId));
            item.Id = resId;
            item.GoogleAvatarIcon = icon;
            mItems.add(item);
            return this;
        }

        public ListItemAdapter build() {
            return new ListItemAdapter(mItems);
        }
    }

    public static class ListItem {
        public ListItemAdapter.ListItemType Type;
        public Integer Id;
        public String Tittle;
        public String SubTittle;
        public String Content;
        public String TimeAgo;
        public Drawable AvatarIcon;
        public GoogleMaterial.Icon GoogleAvatarIcon;
        public GoogleMaterial.Icon InfoIcon;

        public ListItemAdapter.ListItemType getType() {
            return Type;
        }

        public ListItem(ListItemAdapter.ListItemType type, String tittle) {
            Id = 0;
            Tittle = tittle;
            Type = type;
            Content = "";
            SubTittle = "";
            TimeAgo = null;
            AvatarIcon = null;
            InfoIcon = null;
        }

    }
}