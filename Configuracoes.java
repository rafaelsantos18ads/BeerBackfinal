package beerback.trabahodegraduacao.com.beerbackfinal;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Configuracoes extends AppCompatActivity {


    private EditText nomeatualiza,sobrnomeatualiza,cpfatualiza,telefoneatualiza,emailatualiza,senhaatualiza;
    private Button atualizarid;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        Inicializa();
        ConectaFirebase();
        verificaUser();
        eventoclick();
    }






    private void Inicializa(){
        nomeatualiza=(EditText)findViewById(R.id.nomeatualizaid);
        sobrnomeatualiza=(EditText)findViewById(R.id.sobrenomeatualizaid);
        cpfatualiza=(EditText)findViewById(R.id.cpfatualizaid);
        telefoneatualiza=(EditText)findViewById(R.id.telefoneatualizaid);
        atualizarid=(Button)findViewById(R.id.atualizardadosid);
        emailatualiza=(EditText)findViewById(R.id.emailatualizaid);
        senhaatualiza=(EditText)findViewById(R.id.senhaatualizaid);



        telefoneatualiza.addTextChangedListener(MaskEditUtil.mask(telefoneatualiza, MaskEditUtil.FORMAT_FONE));
        cpfatualiza.addTextChangedListener(MaskEditUtil.mask(cpfatualiza, MaskEditUtil.FORMAT_CPF));
    }

    private void ConectaFirebase(){
        firebaseAuth=Conexao.getFirebaseAuth();
        firebaseUser=Conexao.getFirebaseUser();
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();

    }

    public void verificaUser()
    {
        if (firebaseUser==null){
            Toast.makeText(Configuracoes.this, "sem conexão", Toast.LENGTH_SHORT).show();
        }
        else{
            emailatualiza.setText(firebaseUser.getEmail());

            final  String email = firebaseUser.getEmail();

            databaseReference= FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Cliente").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists())
                    {
                        for (DataSnapshot data : dataSnapshot.getChildren()){
                            if (email.equals(data.child("usuariocadastro").getValue(String.class))){
                                String email= data.child("usuariocadastro").getValue(String.class);
                                String nome = data.child("nomecadastro").getValue(String.class);
                                String sobrenome= data.child("sobrenomecadastro").getValue(String.class);
                                String cpf=data.child("cpfcadastro").getValue(String.class);
                                String telefone=data.child("telefonecadastro").getValue(String.class);
                                String senha = data.child("senhacadastro").getValue(String.class);

                                emailatualiza.setText(email);
                                nomeatualiza.setText(nome);
                                sobrnomeatualiza.setText(sobrenome);
                                cpfatualiza.setText(cpf);
                                telefoneatualiza.setText(telefone);
                                senhaatualiza.setText(senha);
                            }

                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }



 public  void eventoclick()
 {
     atualizarid.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {


             String id = databaseReference.push().getKey();
             String nome = nomeatualiza.getText().toString().trim();
             String sobrenome = sobrnomeatualiza.getText().toString().trim();
             String cpf = cpfatualiza.getText().toString().trim();
             String telefone = telefoneatualiza.getText().toString().trim();
             String email = emailatualiza.getText().toString().trim();
             String senha = senhaatualiza.getText().toString().trim();


             if(TextUtils.isEmpty(nome)){
                 nomeatualiza.setError("nome obrigatorio");
                 return;
             }

             atualizadados(id,nome,sobrenome,cpf,telefone,email,senha);



         }
     });
 }




    private boolean atualizadados(String id, String nome,String sobrenome, String cpf,String telefone,String email, String senha){



       DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Cliente").child(id);

        ClienteClass cliente = new ClienteClass(id, nome, sobrenome, cpf, telefone, email, senha);

        databaseReference.setValue(cliente);

        Toast.makeText(Configuracoes.this, "Atualização de dados concluida", Toast.LENGTH_SHORT).show();

        return true;
    }


}
