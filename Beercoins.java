package beerback.trabahodegraduacao.com.beerbackfinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Beercoins extends AppCompatActivity {

    private TextView valorbeercoins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beercoins);
        Inicializa();
        recebe();

        envia();


    }

    private void Inicializa(){
        valorbeercoins= (TextView)findViewById(R.id.valoraresgatarid);



    }

    public void recebe()
    {
        String valor_pedido_passado  = System.getProperty("chave",null);

        valorbeercoins.setText(valor_pedido_passado);
    }





    public void envia(){

        System.setProperty("chave_desconto_final", String.valueOf(valorbeercoins));

    }








}
