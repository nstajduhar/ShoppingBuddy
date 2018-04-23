package com.nickstajduhar.shoppingbuddy;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.aboutlibraries.ui.LibsFragment;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreFragment extends Fragment implements GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener, OnMapAndViewReadyListener.OnGlobalLayoutAndMapReadyListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private static final LatLng ZehrsLasalle = new LatLng(42.254642, -83.058414);
    private static final LatLng ZehrsWindsor = new LatLng(42.317608, -82.938343);
    private static final LatLng ZehrsTecumseh = new LatLng(42.315408, -82.867652);
    private static final LatLng ZehrsKingsville = new LatLng(42.039685, -82.725777);
    private static final LatLng SuperStoreWindsor = new LatLng(42.257210, -82.967627);
    private static final LatLng SuperStoreChatham = new LatLng(42.433181, -82.217740);
    private static final LatLng SuperStoreWindsor1 = new LatLng(42.288557, -83.023828);
    private static final LatLng SuperStoreLeamington = new LatLng(42.056277, -82.586805);

    private GoogleMap mMap = null;

    /**
     * Keeps track of the selected marker.
     */
    private Marker mSelectedMarker;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreFragment newInstance(String param1, String param2) {
        MoreFragment fragment = new MoreFragment();
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
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        final FragmentManager fm = getActivity().getSupportFragmentManager();


        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        new OnMapAndViewReadyListener(mapFragment, this);

        Button credits = (Button) view.findViewById(R.id.credits);
        Button settings = (Button) view.findViewById(R.id.settingsButton);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction().replace(R.id.content_main, new SettingsFragment()).commit();

            }
        });

        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button Pressed", "Pressed");
                new LibsBuilder()
                        .withLibraries("picasso")
                        .withAutoDetect(true)
                        .withLicenseShown(true)
                        .withVersionShown(true)
                        .withActivityTitle("Credits")
                        .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                        .start(getActivity());
            }
        });


        final CardView myLayout
                = (CardView) view.findViewById(R.id.tweetView);

        final long tweetId = 943570105375580161L;
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                myLayout.addView(new CompactTweetView(getContext(), result.data,
                        R.style.tw__TweetDarkWithActionsStyle));
            }

            @Override
            public void failure(TwitterException exception) {
                // Toast.makeText(...).show();
            }
        });
        return view;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Hide the zoom controls.
        mMap.getUiSettings().setZoomControlsEnabled(false);

        // Add lots of markers to the map.
        addMarkersToMap();

        // Set listener for marker click event.  See the bottom of this class for its behavior.
        mMap.setOnMarkerClickListener(this);

        // Set listener for map click event.  See the bottom of this class for its behavior.
        mMap.setOnMapClickListener(this);

        // Override the default content description on the view, for accessibility mode.
        // Ideally this string would be localized.
        map.setContentDescription("All Zehrs locations in your area");

        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(ZehrsLasalle)
                .include(ZehrsWindsor)
                .include(ZehrsTecumseh)
                .include(ZehrsKingsville)
                .include(SuperStoreWindsor)
                .include(SuperStoreChatham)
                .include(SuperStoreWindsor1)
                .include(SuperStoreLeamington)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
    }

    private void addMarkersToMap() {
        mMap.addMarker(new MarkerOptions()
                .position(ZehrsLasalle)
                .title("Zehrs")
                .snippet("5890 Malden Rd, Mon-Sun: 8a.m.–11p.m."));

        mMap.addMarker(new MarkerOptions()
                .position(ZehrsWindsor)
                .title("Zehrs")
                .snippet("7201 Tecumseh Rd E, Mon-Sun: 8a.m.–11p.m."));

        mMap.addMarker(new MarkerOptions()
                .position(ZehrsTecumseh)
                .title("Zehrs")
                .snippet("400 Manning Rd, Mon-Sun: 8a.m.–11p.m."));

        mMap.addMarker(new MarkerOptions()
                .position(ZehrsKingsville)
                .title("Zehrs")
                .snippet("300 Main St E, Mon-Sun: 8a.m.–11p.m."));
        mMap.addMarker(new MarkerOptions()
                .position(SuperStoreWindsor)
                .title("Superstore Walker")
                .snippet("4371 Walker Rd, Mon-Sun: 7a.m.–11p.m."));
        mMap.addMarker(new MarkerOptions()
                .position(SuperStoreWindsor1)
                .title("Superstore Dougall")
                .snippet("2430 Dougall Ave, Mon-Sun: 7a.m.–11p.m."));
        mMap.addMarker(new MarkerOptions()
                .position(SuperStoreLeamington)
                .title("Superstore Leamington")
                .snippet("201 Talbot St E, Mon-Sun: 7a.m.–10p.m."));
        mMap.addMarker(new MarkerOptions()
                .position(SuperStoreChatham)
                .title("Superstore Windsor")
                .snippet("791 St Clair St N, Mon-Sun: 7a.m.–10p.m."));
    }

    @Override
    public void onMapClick(final LatLng point) {
        // Any showing info window closes when the map is clicked.
        // Clear the currently selected marker.
        mSelectedMarker = null;
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        // The user has re-tapped on the marker which was already showing an info window.
        if (marker.equals(mSelectedMarker)) {
            // The showing info window has already been closed - that's the first thing to happen
            // when any marker is clicked.
            // Return true to indicate we have consumed the event and that we do not want the
            // the default behavior to occur (which is for the camera to move such that the
            // marker is centered and for the marker's info window to open, if it has one).
            mSelectedMarker = null;
            return true;
        }

        mSelectedMarker = marker;

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur.
        return false;
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
