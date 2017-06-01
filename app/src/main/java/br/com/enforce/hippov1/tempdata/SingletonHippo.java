package br.com.enforce.hippov1.tempdata;

import java.util.ArrayList;
import java.util.List;

import br.com.enforce.hippov1.entities.Item;
import br.com.enforce.hippov1.rest.ClienteLoginRest;

public class SingletonHippo {

    private static SingletonHippo instance;
    private ClienteLoginRest cliente;
    private List<Item> itens = new ArrayList<>();

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

    public void addItem(Item item) {
        itens.add(item);
    }

    public List<Item> getItens() {
        return itens;
    }
}