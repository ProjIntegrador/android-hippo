package br.com.enforce.hippov1.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.enforce.hippov1.R;
import br.com.enforce.hippov1.listas.Constantes;

/**
 * Created by nalmir on 06/02/2017.
 */
public class CategoriaA extends Fragment {

    private Context context;

    private ListView lista_lv_produtos;
    private SimpleAdapter simple_adapter_produtos;
    private ArrayList<Constantes> arrau_list_produtos;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.categoria_a, container, false);


        inicializarVariavel(view);
        inicializarAcao();

        return view;
    }

    private void inicializarVariavel( View view ) {

        context = view.getContext();

        lista_lv_produtos = (ListView) view.findViewById(R.id.categoria_a_lv_produtos);

        gerarDados();

        String[] De = { Constantes.TEXTO_01, Constantes.TEXTO_02 };
        int[] Para = { R.id.celula_iv_foto, R.id.celula_tv_nome };
        simple_adapter_produtos = new SimpleAdapter(
                context,
                arrau_list_produtos,
                R.layout.celula,
                De,
                Para
        );

        lista_lv_produtos.setAdapter(simple_adapter_produtos);
    }

    private void inicializarAcao() {
        lista_lv_produtos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Constantes hmAux = (Constantes) parent.getItemAtPosition(position);
                //
                Toast.makeText(
                        context,
                        hmAux.get(Constantes.TEXTO_03),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    int [] fotos = {
            R.drawable.avenger01,
            R.drawable.avenger02,
            R.drawable.avenger03,
            R.drawable.avenger04,
            R.drawable.avenger05,
            R.drawable.avenger06,
            R.drawable.avenger07
    };

    String [] nomes = {
            "Gaviao Arqueiro",
            "War Machine",
            "Thor",
            "Nick Fury",
            "Loki",
            "Iron Man",
            "Hulk"
    };

    String [] descricao = {
            "Nenhum",
            "Ter uma amigo Rico",
            "slc",
            "Saco do Gerenciar Egos",
            "Trolador",
            "Filantropo",
            "Paciencia"
    };


    private void gerarDados() {
        arrau_list_produtos = new ArrayList<>();

        for (int i = 0; i < fotos.length; i++) {
            Constantes hmAux = new Constantes();

            hmAux.put(Constantes.ID, String.valueOf(i+1));
            hmAux.put(Constantes.TEXTO_01, String.valueOf(fotos[i]));
            hmAux.put(Constantes.TEXTO_02, nomes[i]);
            hmAux.put(Constantes.TEXTO_03, descricao[i]);

            arrau_list_produtos.add(hmAux);
        }
    }

}
