package org.roszonelib.notetools.dialogs;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;

import org.roszonelib.notetools.R;
import org.roszonelib.notetools.adapters.ListItemAdapter;
import org.roszonelib.notetools.utils.CustomViewUtils;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 17/03/2016 9:55
 * ====================================
 */
public class NoteMaterialDialog extends MaterialDialog.Builder {
    private Activity mActivity;

    public NoteMaterialDialog(Activity activity) {
        super(activity);
        mActivity = activity;
    }

    private String getString(int resId) {
        return getContext().getString(resId);
    }

    public MaterialDialog createLoginDialog(String username, final OnLoginCallback callback) {
        customView(R.layout.dialog_login, false);
        autoDismiss(false);
        negativeText(R.string.back);
        positiveText(R.string.next);
        onAny(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                CustomViewUtils.hideKeyboard(mActivity);
                switch (which) {
                    case NEGATIVE:
                        dialog.dismiss();
                        break;
                    case POSITIVE:
                        View view = dialog.getCustomView();
                        String errorMessage = getString(R.string.InvalidValidation);
                        String user = CustomViewUtils.getStringFromInput(view, R.id.input_user, errorMessage);
                        String passWord = CustomViewUtils.getStringFromInput(view, R.id.input_password, errorMessage);
                        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(passWord)) {
                            callback.onClickLogin(user, passWord, dialog);
                        }
                        break;
                }
            }
        });
        final MaterialDialog dialog = build();
        View view = dialog.getCustomView();
        CustomViewUtils.setInputText(view, R.id.input_user, username);
        CustomViewUtils.addListener(dialog.getCustomView(), R.id.label_advance_options, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickOptions(dialog);
            }
        });
        return dialog;
    }

    public NoteMaterialDialog setIcon(GoogleMaterial.Icon icon) {
        icon(CustomViewUtils.getIconDrawable(getContext(), icon));
        return this;
    }


    public NoteMaterialDialog setTitle(String title, GravityEnum gravity) {
        title(title);
        titleGravity(gravity);
        return this;
    }

    public MaterialDialog setAdapter(ListItemAdapter adapter, ListItemAdapter.OnListItemClickListener listener) {
        customView(R.layout.dialog_recycler_view, false);
        autoDismiss(false);
        MaterialDialog dialog = build();
        adapter.setOnClickListener(listener);
        adapter.setDialog(dialog);
        CustomViewUtils.addRecyclerViewAdapter(dialog.getCustomView(), R.id.recycler_view, adapter);
        return dialog;
    }

    public MaterialDialog createUserDialog() {
        customView(R.layout.dialog_create_user, false);
        autoDismiss(false);
        negativeText(R.string.back);
        positiveText(R.string.next);
        onAny(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                CustomViewUtils.hideKeyboard(mActivity);
                switch (which) {
                    case NEGATIVE:
                        dialog.dismiss();
                        break;
                    case POSITIVE:
                        dialog.dismiss();
                        break;
                }
            }
        });
        MaterialDialog dialog = build();
        View view = dialog.getCustomView();
        CustomViewUtils.setLabelText(view, R.id.label_spec, R.string.create_user_note);
        return build();
    }

    public interface OnLoginCallback {
        void onClickLogin(String username, String password, MaterialDialog dialog);

        void onClickOptions(MaterialDialog dialog);
    }

}
