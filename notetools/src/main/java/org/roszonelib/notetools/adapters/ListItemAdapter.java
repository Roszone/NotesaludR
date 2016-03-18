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
                return R.layout.itemviewholder_single_line;
            case TWO_LINE:
                return R.layout.itemviewholder_two_line;
            case THREE_LINE:
                return R.layout.itemviewholder_three_line;
            default:
                return R.layout.itemviewholder_only_tittle;
        }
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        ListItem item = mItems.get(position);
        setValues(holder, item);
        setIcons(holder, item);
        setItemListener(holder, item, position);
    }

    private void setValues(ListItemViewHolder holder, ListItem item) {
        holder.LabelTittle.setText(item.Tittle);
        holder.LabelSubTittle.setText(item.SubTittle);
        holder.LabelContent.setText(item.Content);
        holder.LabelInfo.setText(item.TimeAgo);
    }

    private void setIcons(ListItemViewHolder holder, ListItem item) {
        if (item.AvatarIcon == null) {
            holder.AvatarIcon.setImageDrawable(null);
        } else {
            holder.AvatarIcon.setImageDrawable(item.AvatarIcon);
        }
    }

    private void setItemListener(ListItemViewHolder holder, final ListItem item, final int position) {
        holder.itemView.setOnClickListener(item.getType() == ListItemType.ONLY_TITTLE ? null : new View.OnClickListener() {
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

    public ListItemAdapter addArrayList(String tittle, List<ListItem> items) {
        mItems.add(new ListItem(ListItemType.ONLY_TITTLE, tittle));
        mItems.addAll(items);
        notifyDataSetChanged();
        return this;
    }

    public interface OnListItemClickListener {
        void onClick(ListItem item, int position, MaterialDialog dialog);
    }

    public static class builder {

        public builder(Context context) {
        }

        public builder addSingleListItem(int resId, GoogleMaterial.Icon gmd_person_add) {
            return null;
        }

        public ListItemAdapter build() {
            return null;
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
        public GoogleMaterial.Icon InfoIcon;

        public ListItemAdapter.ListItemType getType() {
            return Type;
        }

        public ListItem(ListItemAdapter.ListItemType type, String content) {
            Id = 0;
            Tittle = "";
            Type = type;
            Content = content;
            SubTittle = "";
            TimeAgo = null;
            AvatarIcon = null;
            InfoIcon = null;
        }

    }
}