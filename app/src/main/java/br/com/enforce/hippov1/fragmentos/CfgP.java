package br.com.enforce.hippov1.fragmentos;

import android.support.v4.app.Fragment;

public class CfgP {
    private String titulo;
    private Fragment frg;

    public CfgP(String titulo, Fragment frg) {
        this.titulo = titulo;
        this.frg = frg;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Fragment getFrg() {
        return frg;
    }

    public void setFrg(Fragment frg) {
        this.frg = frg;
    }
}
