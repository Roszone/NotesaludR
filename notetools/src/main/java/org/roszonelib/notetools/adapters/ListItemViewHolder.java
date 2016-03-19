package org.roszonelib.notetools.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.roszonelib.notetools.R;
import org.roszonelib.notetools.utils.CustomViewUtils;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 18/03/2016 10:01
 * ====================================
 */
public class ListItemViewHolder extends RecyclerView.ViewHolder {
    public TextView LabelTittle;
    public TextView LabelSubTittle;
    public TextView LabelContent;
    public TextView LabelSpec;
    public ImageView AvatarIcon;
    public ImageView InfoIcon;

    public ListItemViewHolder(View itemView) {
        super(itemView);
        LabelTittle = (TextView) itemView.findViewById(R.id.label_tittle);
        LabelSubTittle = (TextView) itemView.findViewById(R.id.label_subtittle);
        LabelContent = (TextView) itemView.findViewById(R.id.label_content);
        LabelSpec = (TextView) itemView.findViewById(R.id.label_spec);
        AvatarIcon = (ImageView) itemView.findViewById(R.id.image_avatar);
        InfoIcon = (ImageView) itemView.findViewById(R.id.image_info);
    }

    protected void setValues(ListItemAdapter.ListItem item) {
        LabelTittle.setText(item.Tittle == null ? "" : item.Tittle);
        LabelSubTittle.setText(item.SubTittle == null ? "" : item.SubTittle);
        LabelContent.setText(item.Content == null ? "" : item.Content);
        LabelSpec.setText(item.TimeAgo == null ? "" : item.TimeAgo);
    }

    public void setIcons(ListItemAdapter.ListItem item) {
        if (item.AvatarIcon == null) {
            CustomViewUtils.setDrawableIcon(AvatarIcon, item.GoogleAvatarIcon);
        } else {
            CustomViewUtils.setDrawableIcon(AvatarIcon, item.AvatarIcon);
        }
        CustomViewUtils.setDrawableIcon(InfoIcon, item.InfoIcon);
    }
}
