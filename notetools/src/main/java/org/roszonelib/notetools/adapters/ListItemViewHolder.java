package org.roszonelib.notetools.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    public TextView LabelInfo;
    public ImageView AvatarIcon;
    public ImageView InfoIcon;

    public ListItemViewHolder(View itemView) {
        super(itemView);
    }

}
