package br.com.enforce.hippov1.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRest {

    private Long id;
    private String nome;
    private String descproduto;
    private BigDecimal preco;
    private double descontopromo;
    private CategoriaRest categoria;
    private boolean produtoativo;
    private int iduser;
    private int qtdminima;
    private String imagem;
    private List<String> products;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public CategoriaRest getCategoria() {
        return categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescproduto() {
        return descproduto;
    }

    public void setDescproduto(String descproduto) {
        this.descproduto = descproduto;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public double getDescontopromo() {
        return descontopromo;
    }

    public void setDescontopromo(double descontopromo) {
        this.descontopromo = descontopromo;
    }

    public void setCategoria(CategoriaRest categoria) {
        this.categoria = categoria;
    }

    public boolean isProdutoativo() {
        return produtoativo;
    }

    public void setProdutoativo(boolean produtoativo) {
        this.produtoativo = produtoativo;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getQtdminima() {
        return qtdminima;
    }

    public void setQtdminima(int qtdminima) {
        this.qtdminima = qtdminima;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    //  Devolver produtos da categoria:
    public void produtos(){
        ArrayList<String> produtos = new ArrayList<String>();
    }

    public List<String> getProducts() {
        return products;
    }


}
