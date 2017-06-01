package br.com.enforce.hippov1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.R.id.message;

public class ReadQr extends AppCompatActivity {

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
    }
    }
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
                @Override
                public void handleResult(Result result) {
                    String url = result.getText();

                    Intent intent = new Intent(ReadQr.this, DescricaoProduto.class);
                    intent.putExtra("idCategoria", url);
                    startActivity(intent);

                }


            });
        mScannerView.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_DENIED) {
                Toast toast = Toast.makeText(ReadQr.this, "Não é possível utilizar a aplicação " +
                        "sem permissão de acesso a câmera. Saindo...", Toast.LENGTH_LONG);
                toast.show();
                finish();
            }
        }
    }

}

