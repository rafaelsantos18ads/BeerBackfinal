package beerback.trabahodegraduacao.com.beerbackfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_pedido extends AppCompatActivity {

    private TextView marcas,litroscompras,precocompras,quantidade,valoriten;
    private Button continuar;
    private SeekBar quantidadeseek;
    private ImageView voltar_beercoins;
    private ImageView imagempedido;

    public double preco_c=0;
    public String preco_i="";
    public String preco_f = "";
    public double preco_final=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pedido);
        inicializa();
        recebe();
        eventoclick();
        recebedesconto();
        setarimagem();







    }

    private void setarimagem()
    {
        if(marcas.getText().toString().equals("Litrão Skol"))
        {
            imagempedido.setBackgroundResource(R.drawable.litraoskol);
        }
        if(marcas.getText().toString().equals("Litrão Brahma"))
        {
            imagempedido.setBackgroundResource(R.drawable.litraobrahma);
        }
        if(precocompras.getText().toString().equals("R$ 6,50"))
        {
            imagempedido.setBackgroundResource(R.drawable.brahama600);
        }

        if(precocompras.getText().toString().equals("R$ 6,00"))
        {
            imagempedido.setBackgroundResource(R.drawable.skol600);
        }
        if(marcas.getText().toString().equals("Brahma "))
        {
            imagempedido.setBackgroundResource(R.drawable.brahama300);
        }
        if(marcas.getText().toString().equals("Skol"))

        {
            imagempedido.setBackgroundResource(R.drawable.skol300);
        }

        if(marcas.getText().toString().equals("Brahma lata"))
        {
            imagempedido.setBackgroundResource(R.drawable.latabrahama);
        }
        if(marcas.getText().toString().equals("Skol lata")){
            imagempedido.setBackgroundResource(R.drawable.lataskol);
        }
    }









    private void inicializa()
    {
        marcas=(TextView)findViewById(R.id.marcaid);
        litroscompras=(TextView)findViewById(R.id.litrosid);
        precocompras=(TextView)findViewById(R.id.precoid);
        continuar=(Button)findViewById(R.id.continuarid);
        quantidade=(TextView)findViewById(R.id.quantidadeitensid);
        valoriten=(TextView)findViewById(R.id.valortotoalid);
        quantidadeseek=(SeekBar)findViewById(R.id.determinaquantidadeid);
        imagempedido=(ImageView)findViewById(R.id.imagempedidoid);
        voltar_beercoins=(ImageView)findViewById(R.id.repassarbeercoins_id);



    }


    private void recebe(){
        Intent recebe = getIntent();

        Bundle params = recebe.getExtras();

        if (params != null)
        {

            int total = params.getInt("chave_total");
            String litro = params.getString("chave_litro");
            String marca = params.getString("chave_marca");
            String valor = params.getString("chave_valor");
            marcas.setText(marca.toString());
            litroscompras.setText(litro.toString());
            precocompras.setText(valor.toString());

            preco_i = String.format("%.2f",params.getDouble("preco"));

            preco_f = preco_i.replace(",",".");

            System.out.println("preco:"+preco_f);
            preco_final = Double.parseDouble(preco_f);
            System.setProperty("chave_valor_desconto",preco_i);
        }
    }



    private void  eventoclick()
    {

        quantidadeseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                recebe();
                quantidade.setText("Quantidade:"+i);
                valoriten.setText("R$" +  String.format("%.2f",(i*preco_final)));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Tela_pedido.this, "Quantidade estabelecida", Toast.LENGTH_SHORT).show();

            }
        });




        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pedido_quantidade = quantidade.getText().toString();
                String pedido_valor = valoriten.getText().toString();

                Intent intent = new Intent(Tela_pedido.this, Finaliza_compra.class);

                Bundle params = new Bundle();

                params.putString("chave_pedido_quantidade",pedido_quantidade);
                params.putString("chave_pedido_valor",pedido_valor);

                intent.putExtras(params);





                startActivity(intent);
            }
        });

        voltar_beercoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }


    public void recebedesconto()
    {
        Intent recebe = getIntent();

        Bundle parans = recebe.getExtras();


        if(recebe !=null)
        {
            String recebendovalor = parans.getString("chave_desconto");

            String capturavalor= System.getProperty("chave_valor_desconto",null);

            valoriten.setText("Valor R$"+capturavalor);




        }
    }
}
