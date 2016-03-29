package com.amedidevelopment.notesaludr.models.vo;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 28/03/2016 15:25
 * ====================================
 */
public class CredentialsVo {
    public String Username;
    public String Password;
    public Integer Role;

    public CredentialsVo() {
    }

    public CredentialsVo(String username, String password) {
        this.Username = username;
        this.Password = password;
    }
}
