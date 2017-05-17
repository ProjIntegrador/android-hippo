package br.com.enforce.hippov1.rest;

import java.util.ArrayList;

public class CategoriaRest {

    private ArrayList<DadosCategoria> CategoriaList = new ArrayList<>();
    private Long id;
    private String nome;

    public class DadosCategoria{

        private Long id;
        private String nome;
        //  public Long getId() {return id; }
    }

    public Object getTdCategoria() {    return id;  }
    public Object getNomeCategoria() {  return nome;    }

}