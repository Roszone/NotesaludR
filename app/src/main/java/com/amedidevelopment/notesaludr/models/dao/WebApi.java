package com.amedidevelopment.notesaludr.models.dao;

import com.amedidevelopment.notesaludr.models.dto.CredentialsDto;
import com.amedidevelopment.notesaludr.models.dto.UserDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * ====================================
 * Proyecto : NotesaludR
 * Empresa  : Amedi S.a.s.
 * Autor    : Rosember
 * Fecha    : 04/05/2016 17:57
 * ====================================
 */
public class WebApi {
    public interface AccountLogin {
        @POST("api/account/login/")
        Call<UserDto> AuthenticateLogin(@Query("credentials")CredentialsDto credentials);
    }
}
