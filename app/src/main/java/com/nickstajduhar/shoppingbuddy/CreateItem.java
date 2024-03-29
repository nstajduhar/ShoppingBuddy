package com.nickstajduhar.shoppingbuddy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateItem.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateItem extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    EditText name;
    EditText price;
    EditText isle;
    EditText itemImg;

    FragmentManager fm;


    private OnFragmentInteractionListener mListener;

    public CreateItem() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateLocationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateItem newInstance(String param1, String param2) {
        CreateItem fragment = new CreateItem();
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
        View view = inflater.inflate(R.layout.fragment_create_item, container, false);
        price = (EditText) view.findViewById(R.id.itemPrice);
        isle = (EditText) view.findViewById(R.id.itemIsle);
        name = (EditText) view.findViewById(R.id.itemName);
        itemImg = (EditText) view.findViewById(R.id.itemImg);

        Button submit = (Button) view.findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Build up the location
                double newprice = Double.parseDouble(price.getText().toString());
                int isleNumber = Integer.parseInt(isle.getText().toString());

                //Create the item object
                Item item = new Item(name.getText().toString(), isleNumber, newprice, itemImg.getText().toString());
                Toast.makeText(getContext(), name.getText() + " has been added to the database",
                        Toast.LENGTH_SHORT).show();
                //Grab an instance of the database
                DatabaseHandler db = new DatabaseHandler(getContext());
                //Add the location to the database
                db.addItem(item);
                //Close the database
                db.close();
                price.setText("");
                isle.setText("");
                name.setText("");
                itemImg.setText("");
                //Grab the fragment manager and move us back a page/fragment
                fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
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
