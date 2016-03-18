package org.roszonelib.notetools.dialogs;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialize.color.Material;

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
        negativeText(R.string.back);
        autoDismiss(false);
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
                        String user = CustomViewUtils.getStringInput(view, R.id.input_user, errorMessage);
                        String passWord = CustomViewUtils.getStringInput(view, R.id.input_password, errorMessage);
                        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(passWord)) {
                            callback.onLogin(user, passWord, dialog);
                        }
                        break;
                }
            }
        });
        final MaterialDialog dialog = build();
        CustomViewUtils.addListener(dialog.getCustomView(), R.id.label_advance_options, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onOptionsClick(dialog);
            }
        });
        CustomViewUtils.setFocusEditText(dialog.getCustomView(), R.id.input_user, username);
        return dialog;
    }

    public NoteMaterialDialog setIcon(GoogleMaterial.Icon icon) {
        icon(new IconicsDrawable(getContext(), icon).sizeDp(24).color(Material.Grey._500.getAsColor()));
        return this;
    }


    public NoteMaterialDialog setTitle(String title) {
        title(title);
        titleGravity(GravityEnum.CENTER);
        return this;
    }

    public NoteMaterialDialog adapter(ListItemAdapter adapter, ListItemAdapter.OnListItemClickListener listener) {
        return null;
    }

    public interface OnLoginCallback {
        void onLogin(String username, String password, MaterialDialog dialog);

        void onOptionsClick(MaterialDialog dialog);
    }

}
