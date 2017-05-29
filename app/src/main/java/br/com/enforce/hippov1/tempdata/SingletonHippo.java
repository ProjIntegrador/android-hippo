package br.com.enforce.hippov1.tempdata;

import java.util.ArrayList;
import java.util.List;

import br.com.enforce.hippov1.entities.Item;

public class SingletonHippo {

    private static SingletonHippo instance;
    private List<Item> itens = new ArrayList<>();

    private SingletonHippo() {}

    public static SingletonHippo Instance(){
        if (instance == null){
            instance = new SingletonHippo();
        }
        return instance;
    }

    public void addItem(Item item) {
        itens.add(item);
    }

    public List<Item> getItens() {
        return itens;
    }
}