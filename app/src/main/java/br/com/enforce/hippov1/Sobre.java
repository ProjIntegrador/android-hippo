package br.com.enforce.hippov1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class Sobre extends AppCompatActivity {

    private ViewGroup cadsimages1;
    private ViewGroup cadsimages2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cadsimages1 = (ViewGroup) findViewById(R.id.container1);
        cadsimages2 = (ViewGroup) findViewById(R.id.conteiner2);

        //add henrique
        addItem1("https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAArcAAAAJDhlMTc5MmExLWZjYjItNDY1Ny05NDBkLWM4Y2RlOTU1YWViZQ.jpg", "Henrique", "hprigio");
        //add daniel
        addItem1("https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAdeAAAAJDFlNjI4ODE3LTQwZjktNGJmYS1iMDExLTExNmQ0OWJjMzYzZg.jpg", "Daniel", "daniel-xsousa");
        //add renato
        addItem2("https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAinAAAAJDA3YWViZjJmLTE0N2ItNDNmMC04N2FlLTk0NWJkNjE4ZjUyNA.jpg", "Renato", "renatoea");
        //add cinthia
        addItem2("https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAA2OAAAAJGU4ODU4YjBlLWVkN2MtNDU5Ni05ZjYyLTY3OGM4MDViNjM1OQ.jpg", "Cinthia", "cinthia-inhaia-388060142");

    }

    private void addItem1(String urls, String nome, String link) {

        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.activity_itens_cards_sobre, cadsimages1, false);

        ImageView imagens = (ImageView) cardView.findViewById(R.id.imageView);
        TextView nomeparam = (TextView) cardView.findViewById(R.id.nomeSobre);
        nomeparam.setText("Nome : " + nome);
        TextView linkparam = (TextView) cardView.findViewById(R.id.linkedinSobre);
        linkparam.setText("Linkedin : " + link);
        final String fLink = link;
        cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/" + fLink));
                startActivity(i);
            }
        });
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(urls, imagens);
        cadsimages1.addView(cardView);
    }

    private void addItem2(String urls, String nome, String link) {

        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.activity_itens_cards_sobre, cadsimages2, false);
        ImageView imagens = (ImageView) cardView.findViewById(R.id.imageView);
        TextView nomeparam = (TextView) cardView.findViewById(R.id.nomeSobre);
        nomeparam.setText("Nome : " + nome);
        TextView linkparam = (TextView) cardView.findViewById(R.id.linkedinSobre);
        linkparam.setText("Linkedin : " + link);
        final String fLink = link;
        cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/" + fLink));
                startActivity(i);
            }
        });
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(urls, imagens);
        cadsimages2.addView(cardView);

    }

    //  MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);

        //  FAB  -   Floating Action Button
        FloatingActionButton fabqr = (FloatingActionButton) findViewById(R.id.fab_qrcode);
        fabqr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sobre.this, ReadQr.class);
                startActivity(intent);
            }
        });
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