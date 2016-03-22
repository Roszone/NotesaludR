package org.roszonelib.notetools.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialize.color.Material;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 17/03/2016 10:22
 * ====================================
 */
public class CustomViewUtils {

    public static String getStringFromInput(View view, int resId, String errorMsg) {
        TextInputLayout til = getInput(view, resId);
        if (til != null && til.getEditText() != null) {
            EditText ed = til.getEditText();
            if (TextUtils.isEmpty(ed.getText())) {
                til.setError(errorMsg);
            } else {
                til.setError(null);
            }
            return ed.getText().toString();
        } else {
            return "";
        }
    }

    public static void addListener(View view, int resId, View.OnClickListener listener) {
        view.findViewById(resId).setOnClickListener(listener);
    }

    private static TextInputLayout getInput(View view, int resId) {
        return view != null ? (TextInputLayout) view.findViewById(resId) : null;
    }

    public static void hideKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

    public static RecyclerView addRecyclerViewAdapter(View view, int resId, RecyclerView.Adapter adapter) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(resId);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    public static void setDrawableIcon(ImageView image, GoogleMaterial.Icon icon) {
        setDrawableIcon(image, getIconDrawable(image.getContext(), icon));
    }

    public static void setDrawableIcon(ImageView image, Drawable dr) {
        if (dr == null) image.setVisibility(View.GONE);
        else {
            image.setVisibility(View.VISIBLE);
            image.setImageDrawable(dr);
        }
    }

    public static Drawable getIconDrawable(Context context, GoogleMaterial.Icon icon) {
        return icon == null ? null : new IconicsDrawable(context).icon(icon)
                .sizeDp(24)
                .color(Material.Grey._500.getAsColor());
    }

    public static void setInputText(View view, int resId, String value) {
        EditText ed = getInput(view, resId).getEditText();
        if (ed != null) {
            ed.setText(value);
        }
    }

    public static void setLabelText(View view, int resIdLabel, int resIdString) {
        TextView tv = (TextView) view.findViewById(resIdLabel);
        tv.setText(Html.fromHtml(view.getContext().getString(resIdString)));
    }
}
