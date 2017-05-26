package br.com.enforce.hippov1.tempdata;

import javax.inject.Singleton;

public class SingletonHippo {

    private static SingletonHippo instance;
    private int idCategoria;


    private SingletonHippo() {} //no outer class can initialize this class's object

    public static SingletonHippo Instance()
    {   //if no instance is initialized yet then create new instance |  else return stored instance

        if (instance == null)
        {
            instance = new SingletonHippo();
        }
        return instance;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

}