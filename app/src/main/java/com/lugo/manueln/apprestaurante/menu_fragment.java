package com.lugo.manueln.apprestaurante;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lugo.manueln.apprestaurante.adaptadores.adapterMenu;
import com.lugo.manueln.apprestaurante.instancias.VolleySingleton;
import com.lugo.manueln.apprestaurante.instancias.plato;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link menu_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link menu_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class menu_fragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<plato> miList;

    private RecyclerView miRecycler;

    public menu_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter pedir.
     * @return A new instance of fragment menu_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static menu_fragment newInstance(String param1, String param2) {
        menu_fragment fragment = new menu_fragment();
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
        View vista= inflater.inflate(R.layout.fragment_menu_fragment, container, false);


        miRecycler=vista.findViewById(R.id.recyclerMenu);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this.getContext());

        miRecycler.setLayoutManager(layoutManager);

        miRecycler.setHasFixedSize(true);

        miList= new ArrayList<plato>();

        cargarWebService();

        /*ArrayList<plato> miList=new ArrayList<>();

        for (int i=0;i<10;i++){
            plato miPlato=new plato();
            miPlato.setNombre("NombrePlato " + (i+1));
            miPlato.setId((i+1) + "");
            miPlato.setDescripcion("Descripcion del plato numero " + (i+1));

            miList.add(miPlato);
        }*/








        return vista;
    }

    private void cargarWebService() {

        String ip=getString(R.string.ip);

        String d_ip=ip+"/WebRestaurante/jsonCargarPlatos.php";

        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET,d_ip,null,this,this);

        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(objectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {

        plato miPlato=null;

        JSONArray jsonArray=response.optJSONArray("plato");

        try {

            for (int i = 0; i < jsonArray.length(); i++) {

                miPlato = new plato();

                JSONObject object = jsonArray.optJSONObject(i);

                miPlato.setId(object.optInt("id"));
                miPlato.setNombre(object.optString("nombre"));
                miPlato.setDescripcion(object.optString("descripcion"));
                miPlato.setUrlImagen(object.optString("imagen"));
                miPlato.setPrecio(object.optInt("precio"));

                miList.add(miPlato);

            }

            adapterMenu miAdapter=new adapterMenu(miList,getContext());

            miRecycler.setAdapter(miAdapter);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"No se ha podido establecer conexion con el servidor" +
                    " " + response,Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getContext(),"Error de Tipo" + error.toString(),Toast.LENGTH_SHORT).show();
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
