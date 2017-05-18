package br.com.enforce.hippov1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;

import java.util.ArrayList;

import br.com.enforce.hippov1.listas.Constantes;

public class MainActivity extends AppCompatActivity {

    private CheckBox termos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        final SharedPreferences prefs = getSharedPreferences("MeusDados", MODE_PRIVATE);

        String configuracao = prefs.getString("termos", null);
        termos = (CheckBox) findViewById(R.id.termos);
        if(configuracao==null){


            termos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked) {

                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("termos", "ok");
                        editor.apply();
                        //vai para outra
                        Intent intent = new Intent(MainActivity.this, Produto.class);
                        startActivity(intent);

                        finish();

                    }else{

                    }


                }
            });


        }else{
            // vai para outra
            Intent intent = new Intent(this, Produto.class);
            startActivity(intent);

        }


    }



}