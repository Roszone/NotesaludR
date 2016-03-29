package com.amedidevelopment.notesaludr.test;

import com.amedidevelopment.notesaludr.models.vo.CredentialsVo;
import com.amedidevelopment.notesaludr.models.vo.UserVo;

import org.roszonelib.notetools.utils.TimeUtils;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 28/03/2016 17:15
 * ====================================
 */
public class TestUSER {
    public static UserVo getTestUser(CredentialsVo credentials) {
        UserVo user = new UserVo();
        user.Id = 1;
        user.Address = "Carrera falsa 123";
        user.Company = "NavarroCadena";
        user.Document = "1065628483";
        user.Name = "Rosember Perez Mengual";
        user.Phone = "440001";
        user.Sex = "M";
        user.Credentials = credentials;
        user.State = true;
        user.LastUpdate = TimeUtils.now();
        return user;
    }
}
