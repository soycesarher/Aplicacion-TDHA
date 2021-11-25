package com.example.tdah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegistroPsicologo extends Activity {

    private EditText txt_nombre_psicologo;
    private EditText txt_apellido_paterno;
    private EditText txt_apellido_materno;
    private EditText txt_calle;
    private EditText txt_num_exterior;
    private EditText txt_cp;
    private EditText txt_localidad;
    private EditText txt_municipio;
    private EditText txt_telefono;
    private EditText txt_correo;
    private EditText txt_contrasena;
    private EditText txt_celdula;

private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    private boolean boolean_error_texto;
    private boolean boolean_error_contrasena;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro_psicologo);

        inicializa_firebase();

        txt_nombre_psicologo = findViewById(R.id.txt_nombre_psicologo);
        txt_apellido_paterno = findViewById(R.id.txt_apellido_paterno);
        txt_apellido_materno = findViewById(R.id.txt_apellido_materno);
        txt_calle = findViewById(R.id.txt_calle);
        txt_num_exterior = findViewById(R.id.txt_num_exterior);
        txt_cp = findViewById(R.id.txt_cp);
        txt_localidad = findViewById(R.id.txt_localidad);
        txt_municipio = findViewById(R.id.txt_municipio);
        txt_telefono = findViewById(R.id.txt_telefono);
        txt_correo = findViewById(R.id.txt_correo_psicologo);
        txt_contrasena = findViewById(R.id.txt_contrasena_psicologo);
        txt_celdula = findViewById(R.id.txt_cedula_psicologo);

        txt_nombre_psicologo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean_error_texto = valida_texto(txt_nombre_psicologo);
            }
            @Override
            public void afterTextChanged(Editable s) {            }
        });

        txt_apellido_paterno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean_error_texto = valida_texto(txt_apellido_paterno);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txt_apellido_materno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean_error_texto = valida_texto(txt_apellido_materno);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txt_calle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean_error_texto = valida_texto(txt_calle);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(RegistroPsicologo.this, PsicologoPrincipal.class));
            finish();
        }
    }
    /**
     * Valida el formato de editText_texto
     * @param editText_texto EditText que contiente el paciente
     * @return boolean_nombre_paciente_v Regresa el booleano false si no es correcto el formato y true es formato
     */
    private boolean valida_texto(EditText editText_texto) {

        editText_texto.setError(null);

        String sting_texto = editText_texto.getText().toString().trim();

        boolean boolean_error = true;

        View focusView = null;

        if (TextUtils.isEmpty(sting_texto)) {
            editText_texto.setError(getString((R.string.error_campo_requerido)));
            focusView = editText_texto;
            boolean_error = false;
        }

        if (!sting_texto.matches(".{2,20}")) {
            editText_texto.setError(getString(R.string.error_formato_no_valido));
            focusView = editText_texto;
            boolean_error = false;
        }

        if (!boolean_error) {

            focusView.requestFocus();

        }

        return boolean_error;
    }
    /**
     * Esta funcion retorna verdadero si la contrasena tiene errores y  si es falso no tiene errores
     *
     * @param editText_contrasena EditText contrasena
     * @return boolean_contrasena_v
     */
    private boolean valida_contrasena(EditText editText_contrasena) {

        editText_contrasena.setError(null);

        String Password = editText_contrasena.getText().toString().trim();

        boolean boolean_contrasena_v = false;

        View focusView = null;

        if (TextUtils.isEmpty(Password)) {
            editText_contrasena.setError(getString(R.string.error_campo_requerido));
            focusView = editText_contrasena;
            boolean_contrasena_v = true;
        }

        if (!Password.matches(".*[!@#$%^&*+=?-].*")) {
            editText_contrasena.setError(getString(R.string.error_caracter_especial_requerido));
            focusView = editText_contrasena;
            boolean_contrasena_v = true;
        }

        if (!Password.matches(".*\\d.*")) {
            editText_contrasena.setError(getString(R.string.error_numero_requerido));
            focusView = editText_contrasena;
            boolean_contrasena_v = true;
        }

        if (!Password.matches(".*[a-z].*")) {
            editText_contrasena.setError(getString(R.string.error_no_se_encontraron_minusculas));
            focusView = editText_contrasena;
            boolean_contrasena_v = true;
        }

        if (!Password.matches(".*[A-Z].*")) {
            editText_contrasena.setError(getString(R.string.error_no_se_encontraron_mayusculas));
            focusView = editText_contrasena;
            boolean_contrasena_v = true;
        }

        if (!Password.matches(".{8,15}")) {
            editText_contrasena.setError(getString(R.string.error_contrasena_muy_corta));
            focusView = editText_contrasena;
            boolean_contrasena_v = true;
        }

        if (Password.matches(".*\\s.*")) {
            editText_contrasena.setError(getString(R.string.error_sin_espacios));
            focusView = editText_contrasena;
            boolean_contrasena_v = true;
        }

        if (boolean_contrasena_v) {

            focusView.requestFocus();

        }
        return boolean_contrasena_v;
    }

    /**
     * Esta funcion retorna verdadero si el correo tiene errores y falso si el correo no tiene errores
     *
     * @param editText_correo EditText correo
     * @return boolean_error
     */
    private boolean valida_correo(EditText editText_correo) {

        editText_correo.setError(null);

        boolean boolean_correo_v = true;

        View focusView = null;

        Pattern pattern = Patterns.EMAIL_ADDRESS;

        String Email = editText_correo.getText().toString().trim();

        if (TextUtils.isEmpty(Email)) {
            editText_correo.setError(getString(R.string.error_campo_requerido));
            focusView = editText_correo;
            boolean_correo_v = false;
        } else if (!pattern.matcher(Email).matches()) {
            editText_correo.setError(getString(R.string.error_correo_no_valido));
            focusView = editText_correo;
            boolean_correo_v = false;
        }
        if (!boolean_correo_v) {

            focusView.requestFocus();

        }
        return boolean_correo_v;

    }

    /**
     * Crea he inicializa las instancias de Firebase Authentication y obtiene la referencia de
     * Firebase Database
     */
    private void inicializa_firebase() {
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebase_database = FirebaseDatabase.getInstance();
        databaseReference = firebase_database.getReference();
    }

}
