package beerback.trabahodegraduacao.com.beerbackfinal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Tela_menu extends Activity {

    private ImageButton compras,beercoins,meuspedidos,configuracoes,sobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu);
        inicializa();
        eventoclicks();
    }


    private void eventoclicks()
    {
        compras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_menu.this, Compras.class);
                startActivity(intent);
            }
        });

        beercoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_menu.this, Beercoins.class);
                startActivity(intent);
            }
        });

        meuspedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_menu.this, Meuspedidos.class);
                startActivity(intent);
            }
        });

        configuracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_menu.this, Configuracoes.class);
                startActivity(intent);
            }
        });

        sobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Tela_menu.this, Sobre.class);
                startActivity(intent);
            }
        });


    }



    private void inicializa()
    {
        compras=(ImageButton)findViewById(R.id.comprasid);
        beercoins=(ImageButton)findViewById(R.id.meusbeercoinsid);
        meuspedidos=(ImageButton)findViewById(R.id.meuspedidosid);
        configuracoes=(ImageButton)findViewById(R.id.configuracoesid);
        sobre=(ImageButton)findViewById(R.id.sobreid);
    }
}
