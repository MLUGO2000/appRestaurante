package com.lugo.manueln.apprestaurante;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lugo.manueln.apprestaurante.adaptadores.adapterCarrito;
import com.lugo.manueln.apprestaurante.instancias.ConexBBBDHelper;
import com.lugo.manueln.apprestaurante.instancias.platoCarrito;
import com.lugo.manueln.apprestaurante.instancias.utilidades;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link carritoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link carritoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class carritoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<platoCarrito> ordenes;
    private OnFragmentInteractionListener mListener;

    private TextView txtTotal;

    public carritoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter pedir.
     * @return A new instance of fragment carritoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static carritoFragment newInstance(String param1, String param2) {
        carritoFragment fragment = new carritoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_carrito, container, false);

        txtTotal=vista.findViewById(R.id.txtTotalCarrito);

        final ConexBBBDHelper miConex=new ConexBBBDHelper(getContext(),"orden",null,1);

        Button buttonEnviar=vista.findViewById(R.id.b_enviar);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final AlertDialog.Builder miDialog=new AlertDialog.Builder(getContext());
                miDialog.setTitle("Esta Seguro");
                miDialog.setMessage("Esta seguro en enviar el Pedido,Por favor Verifique sus pedidos");
                miDialog.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        SQLiteDatabase bd=miConex.getWritableDatabase();

                        bd.execSQL("DELETE FROM " + utilidades.TABLA_ORDENES);


                        bd.close();

                        Toast.makeText(getContext(), "Orden Enviada", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(getContext(),fragmentActivity.class);
                        startActivity(intent);
                    }
                });
                miDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                miDialog.show();


            }
        });

        ordenes=new ArrayList<>();

        cargarOrdenes(miConex);

        RecyclerView miRecycler=vista.findViewById(R.id.recyclerOrden);

        LinearLayoutManager manager=new LinearLayoutManager(getContext());

        miRecycler.setLayoutManager(manager);

        miRecycler.setHasFixedSize(true);

        adapterCarrito adapter=new adapterCarrito(ordenes,getContext());

        miRecycler.setAdapter(adapter);




        return  vista;
    }

    private void cargarOrdenes(ConexBBBDHelper miConex) {

        Double total=0.0;
        SQLiteDatabase bd = miConex.getReadableDatabase();


        Cursor miCursor=bd.rawQuery("SELECT * FROM " + utilidades.TABLA_ORDENES,null);

        if(miCursor!=null){


            while(miCursor.moveToNext()){

                platoCarrito miOrden=new platoCarrito();


                miOrden.setId(miCursor.getInt(0));
                miOrden.setNombre(miCursor.getString(1));
                miOrden.setUrlImageCarrito(miCursor.getString(2));
                miOrden.setCantidad(miCursor.getInt(3));
                miOrden.setPriceTotal(miCursor.getDouble(4));

                total+=miCursor.getDouble(4);
                ordenes.add(miOrden);

            }
        }


        txtTotal.setText("Total: " + total);


        bd.close();


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
