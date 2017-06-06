package br.com.enforce.hippov1.tempdata;

import java.util.ArrayList;
import java.util.List;

import br.com.enforce.hippov1.entities.Item;
import br.com.enforce.hippov1.rest.ClienteLoginRest;

public class SingletonHippo {

    private String logado;
    private static SingletonHippo instance;
    private ClienteLoginRest cliente;
    private List<Item> itens = new ArrayList<>();

    private int qtdEstoque;
    private String valorCarrinho;

    private SingletonHippo() {}

    public static SingletonHippo Instance(){
        if (instance == null){
            instance = new SingletonHippo();
        }
        return instance;
    }

    public ClienteLoginRest getCliente() {
        return cliente;
    }

    public void setCliente(ClienteLoginRest cliente) {
        this.cliente = cliente;
    }

    public String getLogado(){
        return logado;
    }


    public void setLogado(String mail){
        this.logado = mail;
    }

    public void addItem(Item item) {
        itens.add(item);
    }



    public List<Item> getItens() {
        return itens;
    }

    public void clear()
    {
        instance = null;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setValorCarrinho(String valorCarrinho) {
        this.valorCarrinho = valorCarrinho;
    }

    public String getValorCarrinho() {
        return valorCarrinho;
    }
}