package com.nickstajduhar.shoppingbuddy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link adminPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link adminPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adminPage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public adminPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adminPage.
     */
    // TODO: Rename and change types and number of parameters
    public static adminPage newInstance(String param1, String param2) {
        adminPage fragment = new adminPage();
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
        View view =  inflater.inflate(R.layout.fragment_admin_page, container, false);

        Button createItems = (Button) view.findViewById(R.id.createItems);
        Button updateItems = (Button) view.findViewById(R.id.updateItems);
        Button deleteItems = (Button) view.findViewById(R.id.deleteItems);
        Button premadeItems = (Button) view.findViewById(R.id.insertItems);

        Button logoutButton = (Button) view.findViewById(R.id.logoutButton);


       final DatabaseHandler db = new DatabaseHandler(getContext());


        final FragmentManager fm = getActivity().getSupportFragmentManager();

        createItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction().replace(R.id.content_main, new CreateItem()).commit();

            }
        });

        updateItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction().replace(R.id.content_main, new UpdateItem()).commit();

            }
        });

        deleteItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction().replace(R.id.content_main, new DeleteFragment()).commit();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account.isLoggedIn = false;
                fm.beginTransaction().replace(R.id.content_main, new account()).commit();
            }
        });

        premadeItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating all default items. 20 items were chosen for minimal viable product
                Item item = new Item("Clementines", 1, 6.99 , "https://assets.shop.loblaws.ca/products/20054025001/b1/en/front/20054025001_front_a01.png" );
                Item item1 = new Item("Mandarins", 1, 1.12, "https://assets.shop.loblaws.ca/products/20124308001/b1/en/front/20124308001_front_a01.png" );
                Item item2= new Item("Navel Oranges", 1, 5.49 , "https://assets.shop.loblaws.ca/products/20162945001/b1/en/front/20162945001_front_a01.png" );
                Item item3 = new Item("Minneola Tangelos", 1, 0.98, "https://assets.shop.loblaws.ca/products/20179252001/b1/en/front/20179252001_front_a01.png" );
                Item item4 = new Item("Bananas/ Bunch", 1, 2.40, "https://assets.shop.loblaws.ca/products/20139509001/b1/en/front/20139509001_front_a01.png" );
                Item item5 = new Item("Plantains", 1, 0.94, "https://assets.shop.loblaws.ca/products/20111926001/b1/en/front/20111926001_front_a01.png" );
                Item item6 = new Item("Nectarines", 1, 1.54, "https://assets.shop.loblaws.ca/products/20319426001/b1/en/front/20319426001_front_a01.png" );
                Item item7 = new Item("Peaches", 1, 1.76, "https://assets.shop.loblaws.ca/products/20002646001/b1/en/front/20002646001_front_a01.png" );
                Item item8 = new Item("Black Plums", 1, 0.88, "https://assets.shop.loblaws.ca/products/20316478001/b1/en/front/20316478001_front_a01.png" );
                Item item9 = new Item("Plain Bagles", 3, 2.49, "https://assets.shop.loblaws.ca/products/20716348/b1/en/front/20716348_front_a01.png" );
                Item item10 = new Item("Everything Bagles", 3, 3.49, "https://assets.shop.loblaws.ca/products/21082301/b1/en/front/21082301_front_a01.png" );
                Item item11 = new Item("Chocolate Banana Bread", 3, 6.99, "https://assets.shop.loblaws.ca/products/NoImage/b1/en/front/NoImage_front_a01_@2.png" );
                Item item12 = new Item("German Salami", 2, 3.99, "https://assets.shop.loblaws.ca/products/20297499/b1/en/front/20297499_front_a01.png" );
                Item item13 = new Item("Salami Chips (Spicy)", 2, 4.00, "https://assets.shop.loblaws.ca/products/20990313/b1/en/front/20990313_front_a01.png" );
                Item item14 = new Item("Chorizo Salami", 2, 6.13, "https://assets.shop.loblaws.ca/products/20996150/b1/en/front/20996150_front_a01.png" );
                Item item15 = new Item("Original Pepperoni", 2, 8.99, "https://assets.shop.loblaws.ca/products/20320897/b1/en/front/20320897_front_a01.png" );
                Item item16 = new Item("Salami", 2, 5.99, "https://assets.shop.loblaws.ca/products/20923998/b1/en/front/20923998_front_a01.png" );
                Item item17 = new Item("GF French Baguettes", 5, 6.69, "https://assets.shop.loblaws.ca/products/20712048/b1/en/front/20712048_front_a01.png" );
                Item item18 = new Item("GF Bread Crumbs", 5, 3.99, "https://assets.shop.loblaws.ca/products/20832719/b1/en/front/20832719_front_a01.png" );
                Item item19 = new Item("GF White Bread", 5, 5.49, "https://assets.shop.loblaws.ca/products/20794358/b1/en/front/20794358_front_a01.png" );
                Item item20 = new Item("GF Cinnamon Raisin Loaf", 5, 5.49, "https://assets.shop.loblaws.ca/products/20760921/b1/en/front/20760921_front_a01.png" );

                //Adding the created items to the database
                db.addItem(item);
                db.addItem(item1);
                db.addItem(item2);
                db.addItem(item3);
                db.addItem(item4);
                db.addItem(item5);
                db.addItem(item6);
                db.addItem(item7);
                db.addItem(item8);
                db.addItem(item9);
                db.addItem(item10);
                db.addItem(item11);
                db.addItem(item12);
                db.addItem(item13);
                db.addItem(item14);
                db.addItem(item15);
                db.addItem(item16);
                db.addItem(item17);
                db.addItem(item18);
                db.addItem(item19);
                db.addItem(item20);


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
