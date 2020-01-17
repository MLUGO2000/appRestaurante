package com.lugo.manueln.apprestaurante.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.lugo.manueln.apprestaurante.R;
import com.lugo.manueln.apprestaurante.RedondeaImagen;
import com.lugo.manueln.apprestaurante.instancias.VolleySingleton;
import com.lugo.manueln.apprestaurante.instancias.platoCarrito;

import org.w3c.dom.Text;

import java.util.List;

public class adapterCarrito extends RecyclerView.Adapter<adapterCarrito.MyViewHolder> {

    List<platoCarrito> platos;
    Context context;

    public adapterCarrito(List<platoCarrito> list, Context c){

        context=c;
        platos=list;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista=LayoutInflater.from(parent.getContext()).inflate(R.layout.carrito_list,parent,false);

        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        vista.setLayoutParams(layoutParams);

        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txtNombre.setText(platos.get(position).getNombre());
        holder.txtCantidad.setText(platos.get(position).getCantidad()+ "");
        holder.txtPrecioTotal.setText(platos.get(position).getPriceTotal()+"");

        if(platos.get(position).getUrlImageCarrito()!=null){
            cargarImagenCarrito(platos.get(position).getUrlImageCarrito(),holder);
        }

    }

    private void cargarImagenCarrito(String url, final MyViewHolder holder) {

        ImageRequest miImageR=new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                Bitmap reEscalado=Bitmap.createScaledBitmap(response,700,500,false);
                holder.imgPlato.setImageBitmap(RedondeaImagen.suavizarEsquinas(reEscalado,50));
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(miImageR);

    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre,txtCantidad,txtPrecioTotal;
        ImageView imgPlato;
        public MyViewHolder(View itemView) {
            super(itemView);

            txtNombre=itemView.findViewById(R.id.txtNombre);
            txtCantidad=itemView.findViewById(R.id.txtCantidad);
            txtPrecioTotal=itemView.findViewById(R.id.txtTotal);
            imgPlato=itemView.findViewById(R.id.imageCarrito);


        }
    }
}
