package beerback.trabahodegraduacao.com.beerbackfinal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Finaliza_compra extends Activity {

    private Spinner mesvencimento,anovencimento;
    private EditText nome,numero_cartao,codigoseguranca,cpftitular;
    private Button finalizacompra;
    private TextView pedido,valorpedido;
    DatabaseReference databasepedido, databasebeercoins;
    String valor_pedido_passado_final2;
    FirebaseUser user;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaliza_compra);
        inicializa();
        adapita();
        eventoclick();
        recebe();





    }

    private void recebe() {
        Intent recebe = getIntent();

        Bundle params = recebe.getExtras();

        if (recebe != null) {
            String pedidopassado = params.getString("chave_pedido_quantidade");
            String valor_pedido_passado = params.getString("chave_pedido_valor");
            pedido.setText(pedidopassado.toString());
            valorpedido.setText(valor_pedido_passado.toString());

            String replace = valor_pedido_passado.toString().replace("R$", "");
            String replace2 = replace.replace(",", ".");

            double beercoinspassando = Double.parseDouble(replace2) * 0.08;

            String valor_pedido_passado_final = String.valueOf(beercoinspassando);
            System.setProperty("chave", valor_pedido_passado_final);
            valor_pedido_passado_final2=valor_pedido_passado_final;







        }
    }

    private void inicializa()
    {
        mesvencimento=(Spinner)findViewById(R.id.mesvencimentoid);
        nome=(EditText)findViewById(R.id.nomedotitularid);
        numero_cartao=(EditText)findViewById(R.id.numerodocartaoid);
        codigoseguranca=(EditText)findViewById(R.id.codigosegurancaid);
        anovencimento=(Spinner)findViewById(R.id.anovencimentoid);
        finalizacompra=(Button)findViewById(R.id.finalizacompraid);
        cpftitular=(EditText)findViewById(R.id.cpfcompraid);
        pedido=(TextView)findViewById(R.id.pedidopassadoid);
        valorpedido=(TextView)findViewById(R.id.valorpassadoid);

        cpftitular.addTextChangedListener(MaskEditUtil.mask(cpftitular, MaskEditUtil.FORMAT_CPF));
        codigoseguranca.addTextChangedListener(MaskEditUtil.mask(codigoseguranca, MaskEditUtil.FORMAT_SEGURANCA));
        numero_cartao.addTextChangedListener(MaskEditUtil.mask(numero_cartao, MaskEditUtil.FORMAT_CARTAO));

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);


        databasepedido = FirebaseDatabase.getInstance().getReference("Cliente").child("Pedido");
        databasebeercoins= FirebaseDatabase.getInstance().getReference("Cliente").child("Pedido").child("Beercoins");

    }

    private void eventoclick()
    {
        finalizacompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nome.getText().length() == 0) {
                    nome.setError("Campo obrigatório");
                }
                if (numero_cartao.getText().length() == 0) {
                    numero_cartao.setError("Campo obrigatório");
                }
                if (codigoseguranca.getText().length() == 0) {
                    codigoseguranca.setError("Campo obrigatório");

                }
                if (cpftitular.getText().length() == 0) {
                    cpftitular.setError("Campo obrigatório");

                } else {

                    progressDialog.setMessage("Finalizando compra");

                    showDialog();
                    Intent intent = new Intent(Finaliza_compra.this, Agradecimentos.class);
                    Adiconapedido();
                    AdiconaBeer();
                    finish();
                    startActivity(intent);

                }
            }
        });
    }

    private void adapita()
    {
        ArrayAdapter adapter =ArrayAdapter.createFromResource(this , R.array.mes_vencimento,android.R.layout.simple_spinner_item);
        mesvencimento.setAdapter(adapter);

        ArrayAdapter adapitaano = ArrayAdapter.createFromResource(this, R.array.ano_vencimento, android.R.layout.simple_spinner_item);
        anovencimento.setAdapter(adapitaano);
    }


    private void Adiconapedido()
    {
        String pedidobanco = pedido.getText().toString().trim();
        String valorbanco = valorpedido.getText().toString().trim();
        String cpfbanco =cpftitular.getText().toString().trim();

        if(!TextUtils.isEmpty(pedidobanco))
        {
            String id =databasepedido.push().getKey();



            PedidoClasse pedido = new PedidoClasse( id,pedidobanco,valorbanco,cpfbanco);


            databasepedido.child(id).setValue(pedido);

            Toast.makeText(Finaliza_compra.this, "Pedido adiconado", Toast.LENGTH_SHORT).show();
            
        }
        else{
            Toast.makeText(Finaliza_compra.this, "Finalize o pedido ", Toast.LENGTH_SHORT).show();
        }
    }



    public void AdiconaBeer()
    {
        String replace = valorpedido.toString().replace("R$", "");
        String replace2 = replace.replace(",", ".");

        String beer = System.getProperty("chave",null);
        System.out.println("dddddddd: " + beer);

        if(!TextUtils.isEmpty(beer))
        {
            String id = databasebeercoins.push().getKey();

            Beercoinsclasse beercoins = new Beercoinsclasse(id, beer);

            databasebeercoins.child(id).setValue(beer);

            Toast.makeText(Finaliza_compra.this, "Seus beercoins foram atualizados", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(Finaliza_compra.this, "Erro ao atualizar seus beercoins ", Toast.LENGTH_SHORT).show();
        }


    }


    private void showDialog(){
        if(!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private  void  hideDialog() {
        if(progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }






}


