package br.com.enforce.hippov1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class Sobre extends Activity {
    private ViewGroup cadsimages1;
    private ViewGroup cadsimages2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        cadsimages1 = (ViewGroup) findViewById(R.id.container1);
        cadsimages2 = (ViewGroup) findViewById(R.id.conteiner2);
        //add henrique
        addItem1("https://s-media-cache-ak0.pinimg.com/originals/80/6d/3b/806d3bffaaa73470dd38b5eaccd47f23.png","Henrique","henrique-prieto-rigio-02253a123/");
        //add daniel
        addItem1("https://s-media-cache-ak0.pinimg.com/originals/80/6d/3b/806d3bffaaa73470dd38b5eaccd47f23.png","Daniel","linkd");
        //add renato
        addItem2("https://s-media-cache-ak0.pinimg.com/originals/80/6d/3b/806d3bffaaa73470dd38b5eaccd47f23.png","Renato","linkr");
        //add cinthia
        addItem2("https://s-media-cache-ak0.pinimg.com/originals/80/6d/3b/806d3bffaaa73470dd38b5eaccd47f23.png","Cinthia","linkc");

    }


    //Create Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Carrega o menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ophome) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.opcar) {
            Intent intent = new Intent(this, Carrinho.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.opabout) {
            Intent intent = new Intent(this, Sobre.class);
            startActivity(intent);
            return true;}

        return super.onOptionsItemSelected(item);
    }

    private void addItem1(String urls,String nome,String link) {


        CardView cardView =(CardView) LayoutInflater.from(this).inflate(R.layout.activity_itens_cards_sobre,cadsimages1,false);

        ImageView imagens = (ImageView) cardView.findViewById(R.id.imageView);
        TextView nomeparam=(TextView) cardView.findViewById(R.id.nomeSobre);
        nomeparam.setText("Nome : "+nome);
        TextView linkparam=(TextView) cardView.findViewById(R.id.linkedinSobre);
        linkparam.setText("Linkedin : "+link);
        final String fLink = link;
        cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/"+fLink));
                startActivity(i);
            }
        });
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(urls, imagens);
        cadsimages1.addView(cardView);
    }

    private void addItem2(String urls,String nome,String link) {


        CardView cardView =(CardView) LayoutInflater.from(this).inflate(R.layout.activity_itens_cards_sobre,cadsimages2,false);
        ImageView imagens = (ImageView) cardView.findViewById(R.id.imageView);
        TextView nomeparam=(TextView) cardView.findViewById(R.id.nomeSobre);
        nomeparam.setText("Nome : "+nome);
        TextView linkparam=(TextView) cardView.findViewById(R.id.linkedinSobre);
        linkparam.setText("Linkedin : "+link);
        final String fLink = link;
        cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/"+fLink));
                startActivity(i);
            }
        });
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(urls, imagens);
        cadsimages2.addView(cardView);
    }



}
