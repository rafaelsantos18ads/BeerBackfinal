package beerback.trabahodegraduacao.com.beerbackfinal;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Tela_login extends AppCompatActivity {

    private ProgressDialog pDialog;

    private EditText usuario;
    private EditText senha;
    private Button cadastrar;
    private Button acessar;
    private AlertDialog.Builder alert;
    private FirebaseAuth auth;
    private TextView resetar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        inicializa();
        eventoclicks();

    }




    private void eventoclicks(){
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert = new AlertDialog.Builder(Tela_login.this);

                alert.setMessage("Você possui mais de 18 anos ?");

                alert.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Tela_login.this, Tela_Cadastro.class);
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Tela_login.this, "Proibida a venda de bebidas alcóolicas para menores de 18 anos ", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.create();
                alert.show();

            }
        });

        acessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (usuario.getText().length() == 0 && senha.getText().length() == 0) {
                    Toast.makeText(Tela_login.this, "insira seu login e senha ", Toast.LENGTH_SHORT).show();
                }
                 if(usuario.getText().length()==0){
                    Toast.makeText(Tela_login.this, "Campo usuario em branco", Toast.LENGTH_SHORT).show();
                }
                 else if(senha.getText().length()==0){
                     Toast.makeText(Tela_login.this, "Campo senha em branco", Toast.LENGTH_SHORT).show();
                 }
                else {
                    String email = usuario.getText().toString().trim();
                    String senhalogin = senha.getText().toString().trim();

                    entrar(email, senhalogin);




                }
            }

        });




    }

    private void showDialog(){
        if(!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    private  void  hideDialog() {
        if(pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }


    private void entrar(String email, String senhalogin)
    {
        pDialog.setMessage("Autenticando usuario");

        showDialog();
        auth.signInWithEmailAndPassword(email, senhalogin)
                .addOnCompleteListener(Tela_login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        hideDialog();
                        if (task.isSuccessful())
                        {
                            Intent i = new Intent(Tela_login.this, Tela_menu.class);

                            startActivity(i);

                        }
                        else{
                            Toast.makeText(Tela_login.this, "Usuario ou senha incorretos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }




    private void inicializa()
    {
        usuario= (EditText)findViewById(R.id.usuarioid);
        senha= (EditText)findViewById(R.id.senhaid);
        cadastrar=(Button)findViewById(R.id.solicita_cadastro_id);
        acessar=(Button)findViewById(R.id.acessarid);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
    }

    protected void  onStart()
    {
        super.onStart();
        auth= Conexao.getFirebaseAuth();
    }



   /* private void exibeprogresso(boolean exibir)
    {
        progressBar.setVisibility(exibir? View.VISIBLE : View.GONE);
    }*/





    }

