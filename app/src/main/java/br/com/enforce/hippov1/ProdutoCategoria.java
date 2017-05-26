package br.com.enforce.hippov1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class ProdutoCategoria extends MainActivity {

    private ViewGroup cadProduto1;
    private ViewGroup cadProduto2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_categoria);
        cadProduto1 = (ViewGroup) findViewById(R.id.cat_produto1);
        cadProduto2 = (ViewGroup) findViewById(R.id.cat_produto2);

        int idCategoria = getIntent().getExtras().getInt("idCategoria");

        if(idCategoria > 0 ){

        }else {
//            idcat = paramCat.getString("idCategoria");
        }



        addProd1("https://s-media-cache-ak0.pinimg.com/originals/80/6d/3b/806d3bffaaa73470dd38b5eaccd47f23.png");

        addProd1("https://s-media-cache-ak0.pinimg.com/originals/80/6d/3b/806d3bffaaa73470dd38b5eaccd47f23.png");

        addProd2("https://s-media-cache-ak0.pinimg.com/originals/80/6d/3b/806d3bffaaa73470dd38b5eaccd47f23.png");

        addProd2("https://s-media-cache-ak0.pinimg.com/originals/80/6d/3b/806d3bffaaa73470dd38b5eaccd47f23.png");

    }

    private void addProd1(String urls) {


        CardView cardView =(CardView) LayoutInflater.from(this).inflate(R.layout.activity_itens_cards_sobre,cadProduto1,false);
        ImageView imagens = (ImageView) cardView.findViewById(R.id.imageView);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(urls, imagens);
        cadProduto1.addView(cardView);
    }

    private void addProd2(String urls) {


        CardView cardView =(CardView) LayoutInflater.from(this).inflate(R.layout.activity_itens_cards_sobre,cadProduto2,false);
        ImageView imagens = (ImageView) cardView.findViewById(R.id.imageView);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(urls, imagens);
        cadProduto2.addView(cardView);
    }
}
