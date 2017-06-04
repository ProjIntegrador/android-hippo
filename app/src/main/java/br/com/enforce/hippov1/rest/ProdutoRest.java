package br.com.enforce.hippov1.rest;

import android.widget.ImageView;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProdutoRest {

    private Long idProduto;
    private String nomeProduto;
    private String descProduto;
    private BigDecimal precProduto;
    private double descontoPromocao;
    private CategoriaRest categoria;
    private boolean ativoProduto;
    private int qtdMinEstoque;
    private String imagem;

    public Long getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public BigDecimal getPrecProduto() {
        return precProduto;
    }

    public CategoriaRest getCategoria() {
        return categoria;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescProduto() {
        return descProduto;
    }

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    public void setPrecProduto(BigDecimal precProduto) {
        this.precProduto = precProduto;
    }

    public double getDescontoPromocao() {
        return descontoPromocao;
    }

    public void setDescontoPromocao(double descontoPromocao) {
        this.descontoPromocao = descontoPromocao;
    }

    public void setCategoria(CategoriaRest categoria) {
        this.categoria = categoria;
    }

    public boolean isAtivoProduto() {
        return ativoProduto;
    }

    public void setAtivoProduto(boolean ativoProduto) {
        this.ativoProduto = ativoProduto;
    }

    public int getQtdMinEstoque() {
        return qtdMinEstoque;
    }

    public void setQtdMinEstoque(int qtdMinEstoque) {
        this.qtdMinEstoque = qtdMinEstoque;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    //  Devolver produtos da categoria:
    public void produtos() {
        ArrayList<String> produtos = new ArrayList<String>();
    }


}
