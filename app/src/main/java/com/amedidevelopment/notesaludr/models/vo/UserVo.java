package com.amedidevelopment.notesaludr.models.vo;

import android.graphics.Bitmap;
import android.text.TextUtils;

import org.roszonelib.notetools.utils.ImageBaseUtils;

import java.util.Date;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 28/03/2016 11:26
 * ====================================
 */
public class UserVo {
    public int Id;
    public String Name;
    public String Document;
    public String Sex;
    public String Address;
    public String Phone;
    public String Company;
    public String ProfileImage;
    public CredentialsVo Credentials;
    public Date LastUpdate;
    public Boolean State;

    public Bitmap getProfileImage() {
        return ProfileImage != null && !TextUtils.isEmpty(ProfileImage) ? ImageBaseUtils.decodeBase64(ProfileImage) : null;
    }
}
