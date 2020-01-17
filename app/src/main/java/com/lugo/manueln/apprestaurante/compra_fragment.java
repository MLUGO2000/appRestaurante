package com.lugo.manueln.apprestaurante;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.lugo.manueln.apprestaurante.instancias.ConexBBBDHelper;
import com.lugo.manueln.apprestaurante.instancias.VolleySingleton;
import com.lugo.manueln.apprestaurante.instancias.platoCarrito;
import com.lugo.manueln.apprestaurante.instancias.utilidades;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link compra_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link compra_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class compra_fragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "id";
    private static final String ARG_PARAM2 = "name";
    private static final String ARG_PARAM3 = "image";
    private static final String ARG_PARAM4 = "precio";

    // TODO: Rename and change types of parameters
    private int mParam1Id;
    private String mParam2Name;
    private String mParam3Url;
    private double mParam4Precio;

    private Button b_listo,b_mas;
    private TextView txtName,txtPrecio;
    private EditText editCantidad;
    private OnFragmentInteractionListener mListener;

    private ImageView imageCompra;


    public compra_fragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter pedir.
     * @return A new instance of fragment compra_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static compra_fragment newInstance(String param1, String param2,String param3) {
        compra_fragment fragment = new compra_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1Id = getArguments().getInt(ARG_PARAM1);
            mParam2Name = getArguments().getString(ARG_PARAM2);
            mParam3Url = getArguments().getString(ARG_PARAM3);
            mParam4Precio = getArguments().getDouble(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_compra_fragment, container, false);

        txtName=view.findViewById(R.id.nombreCompra);
        txtPrecio=view.findViewById(R.id.txtPrecioTotal);
        editCantidad=view.findViewById(R.id.editCantidad);

        imageCompra=view.findViewById(R.id.compraImage);

        cargarImagenCompra(mParam3Url);

        txtName.setText(mParam2Name);

        double precio=Integer.parseInt(editCantidad.getText().toString())*mParam4Precio;

        txtPrecio.setText(precio + "");

       editCantidad.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void afterTextChanged(Editable editable) {

               //Toast.makeText(getContext(), editable.toString(), Toast.LENGTH_SHORT).show();
              if(editable.length()!=0){
                  double precio2=Integer.parseInt(editable.toString())*mParam4Precio;
               txtPrecio.setText(precio2+"");
              }else if(editable.length()==0){
                  double precio2=1*mParam4Precio;
                  txtPrecio.setText(precio2+"");
              }
           }
       });



        b_listo=view.findViewById(R.id.bListo);
        b_mas=view.findViewById(R.id.bMas);

       b_listo.setOnClickListener(this);
       b_mas.setOnClickListener(this);
        return view;
    }

    private void cargarImagenCompra(String url) {

        ImageRequest miImage=new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                Bitmap reEscalado=Bitmap.createScaledBitmap(response,700,500,false);
                imageCompra.setImageBitmap(RedondeaImagen.suavizarEsquinas(reEscalado,50));


            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("ERROR iMAGEN COMPRA","Error cargando imagen de compra");
            }
        });

        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(miImage);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        int id;
        String nombre,urlImage;
        int cantidad;
        double precio;

        switch (view.getId()){

            case R.id.bListo:
                //Toast.makeText(getContext(), "Boton Listo", Toast.LENGTH_SHORT).show();

                id=mParam1Id;
                nombre=mParam2Name;
                urlImage=mParam3Url;
                cantidad=Integer.parseInt(editCantidad.getText().toString());
                precio=mParam4Precio*cantidad;

                cargarDatosCarrito(id,nombre,urlImage,cantidad,precio);

                Fragment fragmentOcultar=getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_auxiliar);
                Fragment f_carrito=new carritoFragment();
                FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right);
                fragmentTransaction.hide(fragmentOcultar).replace(R.id.fragment_base,f_carrito).commit();


                //Toast.makeText(getContext(), "Listo", Toast.LENGTH_SHORT).show();
                break;

            case R.id.bMas:

                //Toast.makeText(getContext(),"Boton Mas",Toast.LENGTH_SHORT).show();

                id=mParam1Id;
                nombre=mParam2Name;
                urlImage=mParam3Url;
                cantidad=Integer.parseInt(editCantidad.getText().toString());
                precio=mParam4Precio*cantidad;

                cargarDatosCarrito(id,nombre,urlImage,cantidad,precio);

                getActivity().onBackPressed();

                break;
        }

    }

    private void cargarDatosCarrito(int id,String name,String urlImagen,int cantidad,double precio) {

        platoCarrito miPlato=verificarDatoExistente(id);

        boolean existeReceta=miPlato.getPlatoExistente();
        ConexBBBDHelper miConex = new ConexBBBDHelper(getContext(), "orden", null, 1);

        SQLiteDatabase db = miConex.getWritableDatabase();
        if(!existeReceta) {

            ContentValues values = new ContentValues();

            values.put(utilidades.CAMPO_ID, id);
            values.put(utilidades.CAMPO_NOMBRE, name);
            values.put(utilidades.CAMPO_URL, urlImagen);
            values.put(utilidades.CAMPO_CANTIDAD, cantidad);
            values.put(utilidades.CAMPO_TOTAL, precio);

            long result = db.insert(utilidades.TABLA_ORDENES, utilidades.CAMPO_ID, values);

           // Toast.makeText(getContext(), "Registro: " + result, Toast.LENGTH_SHORT).show();

        }else {

            ContentValues values = new ContentValues();

            values.put(utilidades.CAMPO_NOMBRE, name);
            values.put(utilidades.CAMPO_URL, urlImagen);
            values.put(utilidades.CAMPO_CANTIDAD, miPlato.getCantidad()+cantidad);
            values.put(utilidades.CAMPO_TOTAL, miPlato.getPriceTotal()+precio);

            int result=db.update(utilidades.TABLA_ORDENES,values,"id = ?" ,new String[]{""+id});

           // Toast.makeText(getContext(), "Registro: " + result, Toast.LENGTH_SHORT).show();

        }

        db.close();




    }

    private platoCarrito verificarDatoExistente(int id) {

        platoCarrito miEdicion=new platoCarrito();

        miEdicion.setPlatoExistente(true);

        ConexBBBDHelper miConex= new ConexBBBDHelper(getContext(),"orden",null,1);

        SQLiteDatabase db = miConex.getReadableDatabase();

        Cursor miCursor=db.rawQuery("SELECT * FROM " + utilidades.TABLA_ORDENES + " WHERE " +utilidades.CAMPO_ID +  "= ?",new String[]{""+id});

        if(miCursor.moveToNext()){
            //Toast.makeText(getContext(), "Se Encontro un Registro con el mismo id", Toast.LENGTH_SHORT).show();
            double precioActual=miCursor.getDouble(4);
            int cantidadActual=miCursor.getInt(3);

            miEdicion.setPriceTotal(precioActual);
            miEdicion.setCantidad(cantidadActual);
            miEdicion.setPlatoExistente(true);
        }else {
            //Toast.makeText(getContext(), "No hay ningun Registro con esa id", Toast.LENGTH_SHORT).show();

            miEdicion.setPlatoExistente(false);
        }

return miEdicion;

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
