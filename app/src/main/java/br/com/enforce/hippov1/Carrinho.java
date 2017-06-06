package br.com.enforce.hippov1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import br.com.enforce.hippov1.entities.Item;
import br.com.enforce.hippov1.tempdata.SingletonHippo;

public class Carrinho extends AppCompatActivity {

    private TextView totalAgregado;

    public BigDecimal itemvalue;
    public BigDecimal sum;
    public int qtd;

    private boolean clean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_carrinho);

        BigDecimal total = BigDecimal.ZERO;

        List<Item> itens = SingletonHippo.Instance().getItens();
        if (itens.isEmpty()){
            Toast toast = Toast.makeText(Carrinho.this, "O carrinho esta vazio", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            clean = true;
        } else {
            for (Item item : itens) {
                BigDecimal totalItem = item.getPrecoVendaItem().multiply(new BigDecimal(item.getQtdProduto()));
                addTableRow(item);
                total = total.add(totalItem);
            }
            Log.i("Carrinho", "total " + total);

            TextView totalAgregado = (TextView) findViewById(R.id.total_carrinho);
            String valorMonetario = String.format(Locale.getDefault(), "%.2f", total);
            totalAgregado.setText(valorMonetario);
            clean = false;
        }

    }

    private void addTableRow(final Item item) {
        final TableLayout table = (TableLayout) findViewById(R.id.tl_carrinho);
        final TableRow tr = (TableRow) getLayoutInflater().inflate(R.layout.tela_carrinho_table_row, null);

        TextView tv;
//        tv = (TextView) tr.findViewById(R.id.);
//        tv.setText(item.getIdProduto().toString());

        tv = (TextView) tr.findViewById(R.id.nomeProduto);
        tv.setText(item.getNomeProduto());

        tv = (TextView) tr.findViewById(R.id.precProduto);
        String valorMonetario = String.format(Locale.getDefault(), "%.2f", item.getPrecoVendaItem());
        tv.setText("R$ "+valorMonetario);


        //  Solução para obter o Spinner da activity DescricaoProduto
        Spinner spinQtdCarrinho = (Spinner) tr.findViewById(R.id.spin_carrinho_td);
        Integer[] intArray = new Integer[SingletonHippo.Instance().getQtdEstoque()];
        for (int i = 0; i < SingletonHippo.Instance().getQtdEstoque(); i++) {
            intArray[i] = i;
        }
        //  Instanciado o Adapter para criar as opçoes em base do Array de inteiros
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(Carrinho.this, android.R.layout.simple_spinner_dropdown_item, intArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.notifyDataSetChanged();
//        spinQtdCarrinho.setSelection(1,false);
        spinQtdCarrinho.setAdapter(adapter);

        if (item.getQtdProduto() > 0) {
            spinQtdCarrinho.setSelection(item.getQtdProduto());
        }

        //  Evento de alternância nos valores do Spinner em função do Total do Carrinho
        spinQtdCarrinho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                BigDecimal total = BigDecimal.ZERO;
//                Toast toast = Toast.makeText(Carrinho.this, "Voce alterou para " + parent.getSelectedItem() +
//                        " unidades do produto" + item.getNomeProduto(), Toast.LENGTH_LONG);
//                toast.setGravity(Gravity.CENTER, 0, 0);
//                toast.show();
                BigDecimal totalItem = item.getPrecoVendaItem().multiply(new BigDecimal(String.valueOf(parent.getSelectedItem())));
                total = total.add(totalItem);
                Log.i("Carrinho", "total " + total);
                TextView totalAgregado = (TextView) findViewById(R.id.total_carrinho);
                String valorMonetario = String.format(Locale.getDefault(), "%.2f", total);
                totalAgregado.setText(valorMonetario);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
        //  Fim Solução para o Spinner

        table.addView(tr);
    }

    //  Botão de Voltar às compras
    public void continue_shop(View v) {
        Intent intent = new Intent(Carrinho.this, Categorias.class);
        startActivity(intent);
    }

    //  Botão de Finalizar a Compra
    public void checkout(View v) {
        //  VALIDAÇÕES PARA PROSSEGUIR AO CHECKOUT
        if (clean == true){ //  AQUI VERIFICAMOS SE O CARRINHO POSSUI PRODUTOS
            Toast toast = Toast.makeText(Carrinho.this, "Para finalizar a compra , é preciso adicionar produtos no Carrinho. " +
                    "Você está sendo redirecionado ! ", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            Intent i = new Intent(Carrinho.this, MainActivity.class);
            startActivity(i);

        } else if (SingletonHippo.Instance().getCliente() != null){ // CASO TENHA PASSADO NAS VALIDAÇÕES ANTERIORES , É AUTORIZADO PAGAMENTO
            Intent intent = new Intent(Carrinho.this, Pagamento.class);
            startActivity(intent);

        } else {    //  FORÇA O CLIENTE A ESTAR LOGADO
            Intent intent = new Intent(Carrinho.this, Login.class);
            startActivity(intent);

        }

    }


    //  MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ophome) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.opcar) {
            Intent intent = new Intent(this, Carrinho.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.opabout) {
            Intent intent = new Intent(this, Sobre.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}