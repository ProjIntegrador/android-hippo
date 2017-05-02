package br.com.enforce.hippov1.rest;

class CategoriaRest {


    private Long id;
    private String nome;

    public CategoriaRest(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}