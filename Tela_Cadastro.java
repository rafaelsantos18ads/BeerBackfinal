package beerback.trabahodegraduacao.com.beerbackfinal;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tela_Cadastro extends Activity  {

    public EditText nome,sobrenome,cpf,telefone,usuario_cadastro,senha_cadastro;
    private Button cadastrar;
    private FirebaseAuth auth;
    DatabaseReference databaseCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__cadastro);

        inicializa();
        eventoclicks();

    }


    private void eventoclicks()

    {
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (nome.getText().length() == 0) {
                    nome.setError("Campo obrigatório");
                }
                if (sobrenome.getText().length() == 0) {
                    sobrenome.setError("Campo obrigatório");
                }
                if (cpf.getText().length() == 0) {
                    cpf.setError("Campo obrigatório");
                }
                if (telefone.getText().length() == 0) {
                    telefone.setError("Campo obrigatório");
                }
                if (usuario_cadastro.getText().length() == 0) {
                    usuario_cadastro.setError("Campo obrigatório");
                }
                if (senha_cadastro.getText().length() == 0) {
                    senha_cadastro.setError("Campo obrigatório");
                } else {
                    String nomecadastro = nome.getText().toString().trim();
                    String sobrenomecadastro = sobrenome.getText().toString().trim();
                    String cpfcadastro = cpf.getText().toString().trim();
                    String telefonecadastro = telefone.getText().toString().trim();
                    String usuariocadastro = usuario_cadastro.getText().toString().trim();
                    String senhacadastro = senha_cadastro.getText().toString().trim();

                    criausuario(nomecadastro, sobrenomecadastro, cpfcadastro, telefonecadastro, usuariocadastro, senhacadastro);
                    Adicionausuario();
                }
            }

        });
    }

    private void criausuario( String nomecadastro, String sobrenomecadastro, String cpfcadastro, String telefonecadastro, String usuariocadastro, String senhacadastro){

        auth.createUserWithEmailAndPassword(usuariocadastro,senhacadastro).addOnCompleteListener(Tela_Cadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Tela_Cadastro.this, "Usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Tela_Cadastro.this, Tela_menu.class);
                    startActivity(intent);
                } else

                {
                    Toast.makeText(Tela_Cadastro.this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }



    private void inicializa(){
        nome=(EditText)findViewById(R.id.nome_cadastro_id);
        sobrenome=(EditText)findViewById(R.id.sobrenome_cadastro_id);
        cpf=(EditText)findViewById(R.id.cpf_cadastro_id);
        telefone=(EditText)findViewById(R.id.telefone_cadastro_id);
        usuario_cadastro=(EditText)findViewById(R.id.usuario_cadastro_id);
        senha_cadastro=(EditText)findViewById(R.id.senha_cadastro_id);
        cadastrar=(Button)findViewById(R.id.cadastrarid);

        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_FONE));
        cpf.addTextChangedListener(MaskEditUtil.mask(cpf, MaskEditUtil.FORMAT_CPF));

        databaseCliente= FirebaseDatabase.getInstance().getReference("Cliente");


    }


    public void onStart()
    {
        super.onStart();
        auth=Conexao.getFirebaseAuth();
    }



    public void Adicionausuario()
    {
        String nomebanco = nome.getText().toString().trim();
        String sobrenomebanco = sobrenome.getText().toString().trim();
        String cpfbanco = cpf.getText().toString().trim();
        String telefonebanco = telefone.getText().toString().trim();
        String usuariobanco = usuario_cadastro.getText().toString().trim();
        String senhabanco = senha_cadastro.getText().toString().trim();

        if(!TextUtils.isEmpty(usuariobanco)){

            String id = databaseCliente.push().getKey();

            ClienteClass cliente = new ClienteClass (id,nomebanco, sobrenomebanco,cpfbanco,telefonebanco,usuariobanco,senhabanco);

            databaseCliente.child(id).setValue(cliente);

            Toast.makeText(Tela_Cadastro.this, "Adicionado com sucesso", Toast.LENGTH_SHORT).show();
        }

    }
}
