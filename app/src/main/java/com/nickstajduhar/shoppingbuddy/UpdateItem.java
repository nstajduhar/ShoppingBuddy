package com.nickstajduhar.shoppingbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpdateItem.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpdateItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateItem extends Fragment {
    FragmentManager fm;
    static Spinner spin;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Item mParam1;

    private OnFragmentInteractionListener mListener;

    public UpdateItem() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment UpdateItem.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateItem newInstance(Parcelable param1) {
        UpdateItem fragment = new UpdateItem();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable(ARG_PARAM1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_item, container, false);

        Log.d("mParam", mParam1 + "");

         spin = view.findViewById(R.id.spinner);

        //Grab all the locations from the database
        final DatabaseHandler db = new DatabaseHandler(getContext());
        ArrayList<Item> list = db.getAllItemsName();
        Log.d("NICK", db.getAllItemsName().toString() + "");
        //Link the ArrayList with the spinner
        ArrayAdapter adapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_dropdown_item, list);
        spin.setAdapter(adapter);

        Log.d("Nickspin", spin.getSelectedItem().toString() + "");

        final EditText itemName = (EditText) view.findViewById(R.id.itemName);
        final EditText itemPrice = (EditText) view.findViewById(R.id.itemPrice);
        final EditText itemIsle = (EditText) view.findViewById(R.id.itemIsle);
        final EditText itemImg = (EditText) view.findViewById(R.id.itemImg);
        Button selectButton = (Button) view.findViewById(R.id.selectButton);
        if(mParam1 != null){
            itemName.setText(mParam1.getName());
            //itemPrice.setText(mParam1.getPrice());
            itemIsle.setText(mParam1.getIsle());
            itemImg.setText(mParam1.getItemImg());
        }

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isle = String.valueOf(db.getAllBakery().get(spin.getSelectedItemPosition()).getIsle());
                String price = String.valueOf(db.getAllBakery().get(spin.getSelectedItemPosition()).getPrice());
                itemName.setText(db.getAllBakery().get(spin.getSelectedItemPosition()).getName());
                itemIsle.setText(isle);
                itemPrice.setText(price);
                itemImg.setText(db.getAllBakery().get(spin.getSelectedItemPosition()).getItemImg());
                }

        });

        Button update = (Button) view.findViewById(R.id.updateButton);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("COLUMN_NAME",db.getAllBakery().get(spin.getSelectedItemPosition()).setName(itemName.getText().toString()));
                //cv.put("COLUMN_ISLE", db.getAllBakery().get(spin.getSelectedItemPosition()).setIsle(Integer.parseInt(itemIsle.getText().toString());
                //cv.put("COLUMN_PRICE",db.getAllBakery().get(spin.getSelectedItemPosition()).setPrice(Double.parseDouble(itemPrice.getText().toString())));
                //cv.put("COLUMN_ITEMIMG",db.getAllBakery().get(spin.getSelectedItemPosition()).setItemImg(itemImg.getText().toString()));
                db.updateBakery(cv);
                fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }

        });



        return view;
    }


    public static void getSpinnerSelection(){
        spin.getSelectedItem().toString();
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
