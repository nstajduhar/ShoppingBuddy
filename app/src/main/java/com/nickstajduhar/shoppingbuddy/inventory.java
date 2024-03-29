package com.nickstajduhar.shoppingbuddy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.nickstajduhar.shoppingbuddy.ForRecycleView.itemAdapter;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link inventory.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link inventory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class inventory extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public inventory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment inventory.
     */
    // TODO: Rename and change types and number of parameters
    public static inventory newInstance(String param1, String param2) {
        inventory fragment = new inventory();
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
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

       final Spinner spin = view.findViewById(R.id.spinner);

        Button deleteButton = (Button) view.findViewById(R.id.deleteButton);


        final DatabaseHandler db = new DatabaseHandler(getContext());
        ArrayList<Item> list1 = db.getAllIFavtemsName();
        //Link the ArrayList with the spinner
        ArrayAdapter adapter1 = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_dropdown_item, list1);
        spin.setAdapter(adapter1);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), spin.getSelectedItem().toString() + " has been deleted from the list",
                        Toast.LENGTH_SHORT).show();
                db.deleteFavItems(spin.getSelectedItem().toString());
            }
        });



        Button calendar = (Button) view.findViewById(R.id.calendar);


        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Go Shopping with ShoppingBuddy!";
                String location = "Zehrs";
                long startMillis = 0;
                long endMillis = 0;
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2018,9,14,7,30);
                startMillis = beginTime.getTimeInMillis();
                Calendar endTime = Calendar.getInstance();
                endTime.set(2018,9,14,9,30);
                endMillis = endTime.getTimeInMillis();


                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE, title);
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis);
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });





        RecyclerView list = view.findViewById(R.id.itemList);

        ArrayList<Item> allItems = db.getAllFavItems();
        db.close();
        itemAdapter adapter = new itemAdapter(allItems);
        list.setAdapter(adapter);
        //Create a custom
        //LinearLayoutManager that supports predictiveItemAnimations
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext()){
                    @Override
                    public boolean supportsPredictiveItemAnimations() {
                        return true;
                    }
                };
        //Use that layout manager
        list.setLayoutManager(layoutManager);
        //Set the item animator which controls how the animations look.
        list.setItemAnimator(new DefaultItemAnimator());

        return view;
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
