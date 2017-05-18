package br.com.enforce.hippov1.fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.enforce.hippov1.R;

/**
 * Created by henrique.prigio1 on 10/05/2017.
 */

public class Cartao_op extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cartao_op, container, false);

        return view;
    }
}
