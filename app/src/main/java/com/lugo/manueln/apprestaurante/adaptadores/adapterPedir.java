package com.lugo.manueln.apprestaurante.adaptadores;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.lugo.manueln.apprestaurante.R;
import com.lugo.manueln.apprestaurante.compra_fragment;
import com.lugo.manueln.apprestaurante.instancias.VolleySingleton;
import com.lugo.manueln.apprestaurante.instancias.plato;
import com.lugo.manueln.apprestaurante.pedir_fragment;

import java.util.List;

public class adapterPedir extends RecyclerView.Adapter<adapterPedir.MyViewHolder> {

    List<plato> miListPlatos;
    Context context;
    FragmentActivity fragmentActivity;

    public adapterPedir(List<plato> miListPlatos, Context miContexto, FragmentActivity activity) {
        this.miListPlatos = miListPlatos;
        context=miContexto;
        fragmentActivity=activity;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        AnimacionCircular(holder.itemView);
    }


    public static void AnimacionCircular (View view){

        int centerX=0;
        int centerY=0;
        int startRadius=0;
        int endRadius=Math.max(view.getWidth(),view.getHeight());
        Animator animacion=ViewAnimationUtils.createCircularReveal(view,centerX,centerY,startRadius,endRadius);
        view.setVisibility(View.VISIBLE);
        animacion.start();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista=LayoutInflater.from(parent.getContext()).inflate(R.layout.pedir_list,parent,false);

        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        vista.setLayoutParams(layoutParams);

        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final String nombre=miListPlatos.get(position).getNombre();


        holder.txtNombre.setText(miListPlatos.get(position).getNombre().toString());

        final plato miPlato=new plato();

        if(miListPlatos.get(position).getUrlImagen()!=null){
            cargarImagen(miListPlatos.get(position).getUrlImagen(),holder,miPlato);
        }else{ holder.imageView.setImageResource(R.drawable.imagen);}





        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context, "Nombre " + nombre, Toast.LENGTH_SHORT).show();


                    compra_fragment miFragnment=new compra_fragment();

                    Bundle miBundle=new Bundle();

                    miBundle.putInt("id",miListPlatos.get(position).getId());

                    miBundle.putString("name",miListPlatos.get(position).getNombre());

                    miBundle.putString("image",miListPlatos.get(position).getUrlImagen());

                    miBundle.putDouble("precio",miListPlatos.get(position).getPrecio());

                    miFragnment.setArguments(miBundle);

                FragmentTransaction fragmentTransaction= fragmentActivity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_top,R.anim.exit_to_bottom,R.anim.enter_from_top,R.anim.exit_to_bottom);
                   fragmentTransaction.replace(R.id.fragment_auxiliar,miFragnment).commit();




            }
        });

    }

    private void cargarImagen(String urlImagen, final MyViewHolder holder, final plato plato) {

        String ip=context.getString(R.string.ip);
        String url=ip + urlImagen;

        ImageRequest imageRequest=new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                Bitmap reEscalado=Bitmap.createScaledBitmap(response,900,605,false);
                holder.imageView.setImageBitmap(reEscalado);


            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Error","error cargando imagen");
            }
        });

        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(imageRequest);
    }

    @Override
    public int getItemCount() {
        return miListPlatos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        ImageView imageView;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtNombre= itemView.findViewById(R.id.p_nombre);
            imageView=itemView.findViewById(R.id.p_image);
            cardView=itemView.findViewById(R.id.myCard);

        }
    }

}
