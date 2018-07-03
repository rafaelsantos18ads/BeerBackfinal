package beerback.trabahodegraduacao.com.beerbackfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Compras extends AppCompatActivity {


    private ImageButton brahmalitrao,skollitrao,brahma600,skol600,brahma300,skol300,brahmalata,skollata;
    private TextView preco,litros,marca,marcalitraoskol,litrosltraoskol,precolitraoskol,marcabrahma600,litrosbrahma600,precobrahma600,marcaskol600,litrosskol600,precoskol600,
    marcabrahma300,litrosbrahma300,precobrahma300,marcaskol300,litrosskol300,precoskol300,marcabrhamalata,litrosbrahmalata,precobrahmalata,marcaskollata,litrosskollata,precoskollata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);
        inicializa();
        eventoclick();
    }

    private void eventoclick()
    {
        brahmalitrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marcas = marca.getText().toString();
                String litro= litros.getText().toString();
                String valor = preco.getText().toString();



                Intent intent = new Intent(Compras.this,Tela_pedido.class);
                Bundle params = new Bundle();

                params.putString("chave_marca",marcas);
                params.putString("chave_litro",litro);
                params.putString("chave_valor",valor);
                params.putDouble("preco", 10);



                intent.putExtras(params);

                startActivity(intent);

            }
        });


        skollitrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marcas = marcalitraoskol.getText().toString();
                String litro= litrosltraoskol.getText().toString();
                String valor = precolitraoskol.getText().toString();


                Intent intent = new Intent(Compras.this,Tela_pedido.class);
                Bundle params = new Bundle();

                params.putString("chave_marca",marcas);
                params.putDouble("preco", 10);
                params.putString("chave_litro",litro);
                params.putString("chave_valor",valor);


                intent.putExtras(params);

                startActivity(intent);

            }
        });

        brahma600.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marcas = marcabrahma600.getText().toString();
                String litro= litrosbrahma600.getText().toString();
                String valor = precobrahma600.getText().toString();


                Intent intent = new Intent(Compras.this,Tela_pedido.class);
                Bundle params = new Bundle();

                params.putString("chave_marca",marcas);
                params.putString("chave_litro",litro);
                params.putDouble("preco",  6.50);
                params.putString("chave_valor",valor);


                intent.putExtras(params);

                startActivity(intent);

            }
        });
        skol600.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marcas = marcaskol600.getText().toString();
                String litro= litrosskol600.getText().toString();
                String valor = precoskol600.getText().toString();


                Intent intent = new Intent(Compras.this,Tela_pedido.class);
                Bundle params = new Bundle();

                params.putString("chave_marca",marcas);
                params.putString("chave_litro",litro);
                params.putDouble("preco",  6.00);
                params.putString("chave_valor",valor);


                intent.putExtras(params);

                startActivity(intent);

            }
        });
        brahma300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marcas = marcabrahma300.getText().toString();
                String litro= litrosbrahma300.getText().toString();
                String valor = precobrahma300.getText().toString();


                Intent intent = new Intent(Compras.this,Tela_pedido.class);
                Bundle params = new Bundle();

                params.putString("chave_marca",marcas);
                params.putString("chave_litro",litro);
                params.putDouble("preco",  1.99);
                params.putString("chave_valor",valor);


                intent.putExtras(params);

                startActivity(intent);

            }
        });
        skol300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marcas = marcaskol300.getText().toString();
                String litro= litrosskol300.getText().toString();
                String valor = precoskol300.getText().toString();


                Intent intent = new Intent(Compras.this,Tela_pedido.class);
                Bundle params = new Bundle();

                params.putString("chave_marca",marcas);
                params.putString("chave_litro",litro);
                params.putString("chave_valor",valor);
                params.putDouble("preco",1.80);


                intent.putExtras(params);

                startActivity(intent);

            }
        });

        brahmalata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marcas = marcabrhamalata.getText().toString();
                String litro= litrosbrahmalata.getText().toString();
                String valor = precobrahmalata.getText().toString();


                Intent intent = new Intent(Compras.this,Tela_pedido.class);
                Bundle params = new Bundle();

                params.putString("chave_marca",marcas);
                params.putString("chave_litro",litro);
                params.putDouble("preco", 2.20);
                params.putString("chave_valor",valor);


                intent.putExtras(params);

                startActivity(intent);

            }
        });
        skollata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marcas = marcaskollata.getText().toString();
                String litro= litrosskollata.getText().toString();
                String valor = precoskollata.getText().toString();


                Intent intent = new Intent(Compras.this,Tela_pedido.class);
                Bundle params = new Bundle();

                params.putString("chave_marca",marcas);
                params.putString("chave_litro",litro);
                params.putDouble("preco",  2.10);
                params.putString("chave_valor",valor);


                intent.putExtras(params);

                startActivity(intent);

            }
        });

    }





    private void inicializa()
    {
        brahmalitrao=(ImageButton)findViewById(R.id.brahmalitraoid);
        skollitrao=(ImageButton)findViewById(R.id.skollitraoid);
        brahma600=(ImageButton)findViewById(R.id.brahma600id);
        skol600=(ImageButton)findViewById(R.id.skol600id);
        brahma300=(ImageButton)findViewById(R.id.brahma300id);
        skol300=(ImageButton)findViewById(R.id.skol300id);
        brahmalata=(ImageButton)findViewById(R.id.brahmalataid);
        skollata=(ImageButton)findViewById(R.id.skollataid);

        preco=(TextView)findViewById(R.id.precolitraobrahmaid);
        litros=(TextView)findViewById(R.id.litroslitraobrahmaid);
        marca=(TextView)findViewById(R.id.marcalitraobrahmaid);

        marcalitraoskol=(TextView)findViewById(R.id.marcalitraoskolid);
        litrosltraoskol=(TextView)findViewById(R.id.litroslitraoskolid);
        precolitraoskol=(TextView)findViewById(R.id.precolitraoskolid);

        marcabrahma600=(TextView)findViewById(R.id.marcabrahma600id);
        litrosbrahma600=(TextView)findViewById(R.id.litrosbrahma600id);
        precobrahma600=(TextView)findViewById(R.id.precobrahma600id);


        marcaskol600=(TextView)findViewById(R.id.marcaskol600id);
        litrosskol600=(TextView)findViewById(R.id.litrosskol600id);
        precoskol600=(TextView)findViewById(R.id.precoskol600id);


        marcabrahma300=(TextView)findViewById(R.id.marcabrahama300id);
        litrosbrahma300=(TextView)findViewById(R.id.litrosbrahama300id);
        precobrahma300=(TextView)findViewById(R.id.precobrahma300id);

        marcaskol300=(TextView)findViewById(R.id.marcaskol300id);
        litrosskol300=(TextView)findViewById(R.id.litrosskol300id);
        precoskol300=(TextView)findViewById(R.id.precoskol300id);

        marcabrhamalata=(TextView)findViewById(R.id.marcabrahmalataid);
        litrosbrahmalata=(TextView)findViewById(R.id.litrosbrahmalataid);
        precobrahmalata=(TextView)findViewById(R.id.precobrahmalataid);

        marcaskollata=(TextView)findViewById(R.id.marcaskollataid);
        litrosskollata=(TextView)findViewById(R.id.litrosskollataid);
        precoskollata=(TextView)findViewById(R.id.precoskollataid);
    }
}
