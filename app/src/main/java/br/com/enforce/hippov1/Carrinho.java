package br.com.enforce.hippov1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import br.com.enforce.hippov1.entities.Item;
import br.com.enforce.hippov1.tempdata.SingletonHippo;

public class Carrinho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_carrinho);

        List<Item> itens = SingletonHippo.Instance().getItens();
        for (Item item : itens){
            addTableRow(item);
        }
    }

    private void addTableRow(Item item) {
        final TableLayout table = (TableLayout) findViewById(R.id.tl_carrinho);
        final TableRow tr = (TableRow) getLayoutInflater().inflate(R.layout.tela_carrinho_table_row, null);

        TextView tv;
        tv = (TextView) tr.findViewById(R.id.nomeProduto);
        tv.setText(item.getIdProduto().toString());

        tv = (TextView) tr.findViewById(R.id.precProduto);
        tv.setText(item.getPrecoVendaItem().toString());

        table.addView(tr);
    }


    private void deleteItem(Integer idProduto){
        List<Item> itens = SingletonHippo.Instance().getItens();
        for (int i = 0; i < itens.size(); i++ ){
            if(itens.get(i).getIdProduto().equals(idProduto)) {
                itens.remove(i);
            }
        }


    }

    public void continue_shop(View v) {
        Intent intent = new Intent(Carrinho.this, Categorias.class);
        startActivity(intent);
    }

    public void checkout(View v) {
        Intent intent = new Intent(Carrinho.this, Pagamento.class);
        startActivity(intent);
    }

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