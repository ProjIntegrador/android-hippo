package br.com.enforce.hippov1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

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

        addItem1("https://s-media-cache-ak0.pinimg.com/originals/80/6d/3b/806d3bffaaa73470dd38b5eaccd47f23.png");

        addItem1("https://s-media-cache-ak0.pinimg.com/originals/80/6d/3b/806d3bffaaa73470dd38b5eaccd47f23.png");

        addItem2("https://s-media-cache-ak0.pinimg.com/originals/80/6d/3b/806d3bffaaa73470dd38b5eaccd47f23.png");

        addItem2("https://s-media-cache-ak0.pinimg.com/originals/80/6d/3b/806d3bffaaa73470dd38b5eaccd47f23.png");

    }

    private void addItem1(String urls) {


        CardView cardView =(CardView) LayoutInflater.from(this).inflate(R.layout.activity_itens_cards_sobre,cadsimages1,false);
        ImageView imagens = (ImageView) cardView.findViewById(R.id.imageView);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(urls, imagens);
        cadsimages1.addView(cardView);
    }

    private void addItem2(String urls) {


        CardView cardView =(CardView) LayoutInflater.from(this).inflate(R.layout.activity_itens_cards_sobre,cadsimages2,false);
        ImageView imagens = (ImageView) cardView.findViewById(R.id.imageView);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(urls, imagens);
        cadsimages2.addView(cardView);
    }
    }
