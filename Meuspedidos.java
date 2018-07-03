package beerback.trabahodegraduacao.com.beerbackfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Meuspedidos extends AppCompatActivity {


    private ListView listapedido;
    private List<PedidoClasse> lista = new ArrayList<PedidoClasse>();
    private ArrayAdapter<PedidoClasse> arrayAdapterpedido;
    private Query query;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meuspedidos);


        inicializa();
        adiciona_lista();


    }




    public void inicializa() {
        listapedido = (ListView) findViewById(R.id.listapedidoid);
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }


    public void adiciona_lista() {
        query=databaseReference.child("Cliente").child("Pedido").orderByChild("descricao");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot objdata : dataSnapshot.getChildren()) {
                    PedidoClasse pedidoClasse = objdata.getValue(PedidoClasse.class);
                    lista.add(pedidoClasse);
                }
                arrayAdapterpedido = new ArrayAdapter<PedidoClasse>(getApplicationContext(), android.R.layout.simple_list_item_1,lista);
                listapedido.setAdapter(arrayAdapterpedido);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    }







