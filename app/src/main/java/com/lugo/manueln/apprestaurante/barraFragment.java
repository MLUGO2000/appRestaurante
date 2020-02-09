package com.lugo.manueln.apprestaurante;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link barraFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link barraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class barraFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private BottomNavigationView navigationView;

    public barraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter pedir.
     * @return A new instance of fragment barraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static barraFragment newInstance(String param1, String param2) {
        barraFragment fragment = new barraFragment();
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
        View vista= inflater.inflate(R.layout.fragment_barra, container, false);

        navigationView=vista.findViewById(R.id.b_navigation);

        navigationView.setOnNavigationItemSelectedListener(this);
        return vista;
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment miFragment=null;
        boolean selecFragment=false;
        switch (item.getItemId()){
            case R.id.itemPedir:


                miFragment=new pedir_fragment();
                selecFragment=true;

                item.setChecked(true);
                break;

            case R.id.itemMenu:


                miFragment=new menu_fragment();
                selecFragment=true;

                item.setChecked(true);

                break;

            case R.id.itemCarrito:


                miFragment=new carritoFragment();
                selecFragment=true;

                item.setChecked(true);


                break;
        }

        if(miFragment!=null) {

            Fragment fragmentQuitar=getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_auxiliar);

            FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right);

            if(fragmentQuitar!=null){
                fragmentTransaction.remove(fragmentQuitar);
            }
           fragmentTransaction.replace(R.id.fragment_base, miFragment).commit();
        }

        return false;
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
