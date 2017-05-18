package br.com.enforce.hippov1.fragmentos;

import android.app.Fragment;
import android.support.annotation.Nullable;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.enforce.hippov1.R;

public class Boleto_op extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_boleto_op, container, false);

        return view;
    }
}
