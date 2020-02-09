package com.lugo.manueln.apprestaurante.adaptadores;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
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
import com.lugo.manueln.apprestaurante.RedondeaImagen;
import com.lugo.manueln.apprestaurante.instancias.VolleySingleton;
import com.lugo.manueln.apprestaurante.instancias.plato;

import java.util.List;

public class adapterMenu extends RecyclerView.Adapter<adapterMenu.MyViewHolder> {

    List<plato> miListPlatos;

    Context contexto;

    public adapterMenu(List<plato> miListPlatos, Context miContexto) {
        this.miListPlatos = miListPlatos;
        contexto=miContexto;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista=LayoutInflater.from(parent.getContext()).inflate(R.layout.plato_list,parent,false);

        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        vista.setLayoutParams(layoutParams);

        return new MyViewHolder(vista);
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

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txtNombre.setText(miListPlatos.get(position).getNombre().toString());
        holder.txtPrecio.setText(miListPlatos.get(position).getPrecio()+" $");
        holder.txtDescripcion.setText(miListPlatos.get(position).getDescripcion().toString());

        if(miListPlatos.get(position).getUrlImagen()!=null){

            cargarImagenWeb(miListPlatos.get(position).getUrlImagen(),holder);
        }else {
            Log.i("Error","Error cargando imagen");
        }
       // holder.imageView.setImageResource(R.drawable.imagen);
    }

    private void cargarImagenWeb(String urlImagen, final MyViewHolder holder) {

        String ip=contexto.getString(R.string.ip);
        String url=ip + urlImagen;

        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                Bitmap reEscalado=Bitmap.createScaledBitmap(response,700,500,false);

              //  holder.imageView.setImageBitmap(RedondeaImagen.suavizarEsquinas(reEscalado,50));

                holder.imageView.setImageBitmap(reEscalado);

            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("errorImagen","Error Cargando la Imagen response");
            }
        });

        VolleySingleton.getIntanciaVolley(contexto).addToRequestQueue(imageRequest);
    }

    @Override
    public int getItemCount() {
        return miListPlatos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre,txtPrecio,txtDescripcion;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtNombre= itemView.findViewById(R.id.txt_nombre);
            txtPrecio=itemView.findViewById(R.id.txtPrecioMenu);
            txtDescripcion=itemView.findViewById(R.id.txtDesc);
            imageView=itemView.findViewById(R.id.image);

        }
    }

}
