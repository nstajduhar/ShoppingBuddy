package com.nickstajduhar.shoppingbuddy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nickstajduhar.shoppingbuddy.ForRecycleView.itemAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link search.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class search extends Fragment {

    private SearchView searchView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment search.
     */
    // TODO: Rename and change types and number of parameters
    public static search newInstance(String param1, String param2) {
        search fragment = new search();
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
        final View view = inflater.inflate(R.layout.fragment_search, container, false);

         searchView = (SearchView) view.findViewById(R.id.searchArea);

        final RecyclerView list = view.findViewById(R.id.itemList);

        final DatabaseHandler db = new DatabaseHandler(getContext());

        //These functions query the search bar for their text. If the text is updates it runs the second function

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ArrayList<Item> allItems = db.getSearchItems(searchView.getQuery().toString());
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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Item> allItems = db.getSearchItems(searchView.getQuery().toString());
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
                return false;
            }
        });


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
