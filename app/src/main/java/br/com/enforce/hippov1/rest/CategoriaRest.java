package br.com.enforce.hippov1.rest;

import java.util.ArrayList;
import java.util.List;

public class CategoriaRest {

    private Long id;
    private String nome;
    private List<String> cat;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void categorias(){
        ArrayList<String> cat = new ArrayList<String>();
    }

    public List<String> getList() {
        return cat;
    }
}