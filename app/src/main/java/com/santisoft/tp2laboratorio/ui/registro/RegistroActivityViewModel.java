package com.santisoft.tp2laboratorio.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.santisoft.tp2laboratorio.model.Usuario;
import com.santisoft.tp2laboratorio.request.ApiClient;
import com.santisoft.tp2laboratorio.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private Context context;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuario() {
        if (usuarioMutableLiveData == null) {
            usuarioMutableLiveData = new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }

    public void cargarDatosUsuario(boolean esNuevoRegistro) {
        if (usuarioMutableLiveData == null) {
            usuarioMutableLiveData = new MutableLiveData<>();
        }
        if (esNuevoRegistro) {
            usuarioMutableLiveData.setValue(new Usuario());
        } else {
            Usuario usuario = ApiClient.leer(context);
            if (usuario != null) {
                usuarioMutableLiveData.setValue(usuario);
            }
        }
    }

    public void guardarUsuario(Usuario usuario) {
        boolean guardado = ApiClient.guardar(context, usuario);
        if (!guardado) {
            Toast.makeText(context, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }
}







