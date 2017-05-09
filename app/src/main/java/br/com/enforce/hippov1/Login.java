package br.com.enforce.hippov1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private EditText loginUsuario;
    private EditText senhaUsuario;
    private Button buttonEnviar;
    private Button buttonNvCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsuario = (EditText) findViewById(R.id.loginUsuario);
        senhaUsuario = (EditText) findViewById(R.id.senhaUsuario);
        buttonEnviar = (Button) findViewById(R.id.buttonEnviar);
        buttonNvCadastro = (Button) findViewById(R.id.buttonNvCadastro);


    }
}
