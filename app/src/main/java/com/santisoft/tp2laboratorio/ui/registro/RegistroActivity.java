package com.santisoft.tp2laboratorio.ui.registro;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.santisoft.tp2laboratorio.R;
import com.santisoft.tp2laboratorio.databinding.ActivityRegistroBinding;
import com.santisoft.tp2laboratorio.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private RegistroActivityViewModel rvm;
    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rvm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        boolean esNuevoRegistro = getIntent().getBooleanExtra("esNuevo", false);
        rvm.cargarDatosUsuario(esNuevoRegistro);


        rvm.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etDni.setText(String.valueOf(usuario.getDni()));
                binding.etNombre.setText(usuario.getNombre());
                binding.etApellido.setText(usuario.getApellido());
                binding.etEmail.setText(usuario.getMail());
                binding.etPassword.setText(usuario.getPassword());
            }
        });


        binding.btGuardar.setOnClickListener(v -> {
            String nombre = binding.etNombre.getText().toString();
            String apellido = binding.etApellido.getText().toString();
            Long DNI = Long.valueOf(binding.etDni.getText().toString());
            String correo = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();

            Usuario usuario = new Usuario(nombre, apellido, DNI, correo, password);
            rvm.guardarUsuario(usuario);
            Toast.makeText(this, "Campos guardados correctamente", Toast.LENGTH_SHORT).show();
        });
    }
}
