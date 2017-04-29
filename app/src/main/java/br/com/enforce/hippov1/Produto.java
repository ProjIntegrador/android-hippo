package br.com.enforce.hippov1;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Produto extends AppCompatActivity {

    Context context;
    private String nome;
    private String preco;
    private ListView lv_produto;
    private ArrayList<String> produto;
    private ArrayAdapter<String>  adapter_produto;
    //metodo improvisado para puxar categorias
    private String categoriaid ="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        inicializaVariavel();
        inicializaAcao();
    }

    private void inicializaVariavel() {

        context = getBaseContext();

        lv_produto = (ListView) findViewById(R.id.lv_produto);



        adapter_produto = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                produto
        );

        lv_produto.setAdapter(adapter_produto);
    }




    private void inicializaAcao() {
        NetworkCall myCall = new NetworkCall();
        // Executa a thread, passando null como parâmetro
        myCall.execute("http://hippows.azurewebsites.net/g1/webservices/categoria?idCategoria="+categoriaid);



    }




    public class NetworkCall extends AsyncTask<String, Void, String> {

        // Esse é o método que executa a tarefa em segundo plano
        @Override
        protected String doInBackground(String... params) {
            try {
                // Cria o objeto de conexão
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(params[0]).openConnection();

                // Executa a requisição pegando os dados
                InputStream in = urlConnection.getInputStream();

                // Cria um leitor para ler a resposta
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                StringBuilder resultado = new StringBuilder();
                String linha = bufferedReader.readLine();

                // Lê linha a linha a resposta e armazena no StringBuilder
                while (linha != null) {
                    resultado.append(linha);
                    linha = bufferedReader.readLine();
                }

                // Transforma o StringBuilder em String, que contém a resposta final
                String respostaCompleta = resultado.toString();

                // Retorna a string final contendo a resposta retornada
                return respostaCompleta;

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Caso tenha dado algum erro, retorna null
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                // Cria um objeto JSON a partir da resposta
                JSONObject json = new JSONObject(result);

                // Pega um dado do JSON
                produto = new ArrayList<>();

                int i=0;
                while(i<json.length()){
                    JSONObject obj = json.getJSONObject(String.valueOf(i));

                    nome = (String) obj.get("nome");
                    preco = (String) obj.get("preco");

                    produto.add(nome+" - R$="+preco);
                    i++;
                }



            } catch (Exception e) {
                e.printStackTrace();
                showDialog("Erro ao obter os  Produtos", "Erro");
            }
        }

    }




    private void showDialog(String val, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Produto.this);
        builder.setMessage(val);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
