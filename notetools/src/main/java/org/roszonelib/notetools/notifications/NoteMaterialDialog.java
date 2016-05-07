package org.roszonelib.notetools.notifications;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import org.roszonelib.notetools.R;
import org.roszonelib.notetools.adapters.ListItemAdapter;
import org.roszonelib.notetools.interfaces.OnLoginClickListener;
import org.roszonelib.notetools.utils.DeviceUtils;
import org.roszonelib.notetools.utils.SimpleViewUtils;

import java.text.NumberFormat;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 17/03/2016 9:55
 * ====================================
 */
public class NoteMaterialDialog {
    private MaterialDialog mDialog;
    private Context mContext;

    public NoteMaterialDialog(@NonNull Context context) {
        mContext = context;
    }

    public void dismissIfShowing() {
        try {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
        } catch (Exception ignore) {

        }
    }

    private String getString(int resId) {
        return mContext.getString(resId);
    }

    public NoteMaterialDialog setLoginDialog(int resIdTittle, String username, final OnLoginClickListener callback) {
        mDialog = new MaterialDialog.Builder(mContext)
                .customView(R.layout.dialog_login, true)
                .title(mContext.getString(resIdTittle))
                .titleGravity(GravityEnum.CENTER)
                .autoDismiss(false)
                .negativeText(R.string.back)
                .positiveText(R.string.next)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        switch (which) {
                            case NEGATIVE:
                                dialog.dismiss();
                                break;
                            case POSITIVE:
                                View view = dialog.getCustomView();
                                String errorMessage = getString(R.string.InvalidValidation);
                                String user = SimpleViewUtils.getStringFromInput(view, R.id.input_user, errorMessage);
                                String pass = SimpleViewUtils.getStringFromInput(view, R.id.input_password, errorMessage);
                                if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)) {
                                    callback.onLogin(user, pass, DeviceUtils.isConnectedToInternet(mContext));
                                }
                                break;
                        }
                    }
                }).build();
        View view = mDialog.getCustomView();
        SimpleViewUtils.addStringToInput(view, R.id.input_user, username);
        SimpleViewUtils.addListener(view, R.id.label_advance_options, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onOptions();
            }
        });
        return this;
    }


    public NoteMaterialDialog setAdapter(int resIdTittle, ListItemAdapter adapter, ListItemAdapter.OnListItemClickListener listener) {
        mDialog = new MaterialDialog.Builder(mContext)
                .title(mContext.getString(resIdTittle))
                .titleGravity(GravityEnum.START)
                .customView(R.layout.dialog_recycler, false)
                .autoDismiss(false)
                .build();
        adapter.setOnClickListener(listener);
        adapter.setDialog(mDialog);
        SimpleViewUtils.setRecyclerAdapter(mDialog.getCustomView(), R.id.recycler_view, adapter);
        return this;
    }

    public NoteMaterialDialog setFormUserDialog() {
        mDialog = new MaterialDialog.Builder(mContext)
                .customView(R.layout.dialog_form_user, false)
                .autoDismiss(false)
                .negativeText(R.string.back)
                .positiveText(R.string.next)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        switch (which) {
                            case NEGATIVE:
                                dialog.dismiss();
                                break;
                            case POSITIVE:
                                dialog.dismiss();
                                break;
                        }
                    }
                }).build();
        View view = mDialog.getCustomView();
        SimpleViewUtils.addStringToLabel(view, R.id.label_spec, R.string.create_user_note);
        return this;
    }


    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public MaterialDialog createProgressDialog(boolean indeterminate, final OnShowListener listener) {
        mDialog = new MaterialDialog.Builder(mContext)
                .content(Html.fromHtml("<b>Conectando...</b><br>Espere un momento"))
                .progress(true, 0)
                .progressPercentFormat(NumberFormat.getPercentInstance())
                .cancelable(false).showListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        listener.onShow(mDialog);
                    }
                }).build();
        return mDialog;
    }

    public static void showInDialog(String reason, Context context) {
        if(context !=null && reason !=null) new MaterialDialog.Builder(context)
                .content(Html.fromHtml(reason))
                .negativeText(R.string.back)
                .positiveText(R.string.next)
                .cancelable(false)
                .autoDismiss(false)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build()
                .show();
    }

    public interface OnShowListener {
        void onShow(MaterialDialog dialog);
    }
}
