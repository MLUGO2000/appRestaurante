package com.lugo.manueln.apprestaurante;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lugo.manueln.apprestaurante.instancias.ConexBBBDHelper;
import com.lugo.manueln.apprestaurante.instancias.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,fragment_inter {

    Fragment miFragment;
    Button b_iniciar;

    String usuario,password;

    EditText editUsuario,editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUsuario=findViewById(R.id.editUser);
        editPassword=findViewById(R.id.editPassword);


        b_iniciar=findViewById(R.id.b_iniciar);

        b_iniciar.setOnClickListener(this);

        recuperarPreferencias();


    }



    public void onClick(View view) {

        usuario=editUsuario.getText().toString();
        password=editPassword.getText().toString();

        if(!usuario.isEmpty() & !password.isEmpty()){
            String ip=getString(R.string.ip);

            String d_ip=ip+"/WebRestaurante/jsonInicioSesion.php";

            validarUsuario(d_ip);
        }else {
            Toast.makeText(this, "No Se Permiten Campos Vacios", Toast.LENGTH_SHORT).show();
        }




    }

    private void validarUsuario(String url) {


        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(!response.isEmpty()){
                    Intent i=new Intent(MainActivity.this,fragmentActivity.class);
                    startActivity(i);
                    guardarPreferencias();
                    finish();
                }else {
                    Toast.makeText(MainActivity.this, "Usuario o Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();

                parametros.put("usuario",usuario);
                parametros.put("password",password);
                return parametros;

            }
        };

        VolleySingleton.getIntanciaVolley(this).addToRequestQueue(request);

    }

   private void guardarPreferencias(){
       SharedPreferences misPreferencias=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);

       SharedPreferences.Editor editor=misPreferencias.edit();

       editor.putString("usuario",usuario);
       editor.putString("password",password);
       editor.putBoolean("sesion",true);
       editor.commit();
   }

   private void recuperarPreferencias(){
       SharedPreferences misPreferencias=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);

       editUsuario.setText(misPreferencias.getString("usuario","correo@gmail.com"));
       editPassword.setText(misPreferencias.getString("password","123456"));


   }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
