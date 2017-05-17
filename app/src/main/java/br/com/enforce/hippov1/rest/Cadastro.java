package br.com.enforce.hippov1.rest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.enforce.hippov1.R;

public class Cadastro extends AppCompatActivity {

    private EditText nomeCompletoCliente;
    private EditText emailCliente;
    private EditText senhaCliente;
    private EditText celularCliente;
    private EditText dtNascCliente;
    private CheckBox cbx_promosao_por_email;
    private Button btn_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nomeCompletoCliente = (EditText) findViewById(R.id.nomeCompletoCliente);
        emailCliente = (EditText) findViewById(R.id.emailCliente);
        senhaCliente = (EditText) findViewById(R.id.senhaCliente);
        celularCliente = (EditText) findViewById(R.id.celularCliente);
        dtNascCliente = (EditText)  findViewById(R.id.dtNascCliente);
        cbx_promosao_por_email  = (CheckBox) findViewById(R.id.cbx_promosao_por_email);
        btn_cadastrar = (Button) findViewById(R.id.btn_cadastrar);

    }

}
