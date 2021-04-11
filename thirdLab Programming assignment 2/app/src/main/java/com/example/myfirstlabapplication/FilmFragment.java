package com.example.myfirstlabapplication;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.example.myfirstlabapplication.entities.Film;
import com.example.myfirstlabapplication.entities.Search;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilmFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FilmFragment() {
        // Required empty public constructor

    }

    private ArrayList<Film> parseData(){
        ObjectMapper mapper = new ObjectMapper();
        String data = "{\"Search\":[{\"Title\":\"Long title that want to break your layout. Long title that want to break your layout. Long title that want to break your layout. Long title that want to break your layout\",\"Year\":\"2020\",\"imdbID\":\"noid\",\"Type\":\"test\",\"Poster\":\"\"},{\"Title\":\"Star Wars: Episode IV - A New Hope Star Wars: Episode IV - A New Hope \",\"Year\":\"1977\",\"imdbID\":\"tt0076759\",\"Type\":\"movie\",\"Poster\":\"poster_01.jpg\"},{\"Title\":\"Star Wars: Episode V - The Empire Strikes Back\",\"Year\":\"1980\",\"imdbID\":\"tt0080684\",\"Type\":\"movie\",\"Poster\":\"poster_02.jpg\"},{\"Title\":\"Star Wars: Episode VI - Return of the Jedi\",\"Year\":\"1983\",\"imdbID\":\"tt0086190\",\"Type\":\"movie\",\"Poster\":\"poster_03.jpg\"},{\"Title\":\"Star Wars: Episode VII - The Force Awakens\",\"Year\":\"\",\"imdbID\":\"tt2488496\",\"Type\":\"movie\",\"Poster\":\"\"},{\"Title\":\"Star Wars: Episode I - The Phantom Menace\",\"Year\":\"1999\",\"imdbID\":\"tt0120915\",\"Type\":\"movie\",\"Poster\":\"poster_05.jpg\"},{\"Title\":\"Star Wars: Episode III - Revenge of the Sith\",\"Year\":\"2005\",\"imdbID\":\"tt0121766\",\"Type\":\"movie\",\"Poster\":\"poster_06.jpg\"},{\"Title\":\"Star Wars: Episode II - Attack of the Clones\",\"Year\":\"2002\",\"imdbID\":\"tt0121765\",\"Type\":\"movie\",\"Poster\":\"poster_07.jpg\"},{\"Title\":\"Star Trek\",\"Year\":\"2009\",\"imdbID\":\"tt0796366\",\"Type\":\"movie\",\"Poster\":\"poster_08.jpg\"},{\"Title\":\"Star Wars: Episode VIII - The Last Jedi\",\"Year\":\"2017\",\"imdbID\":\"tt2527336\",\"Type\":\"\",\"Poster\":\"\"},{\"Title\":\"Rogue One: A Star Wars Story\",\"Year\":\"2016\",\"imdbID\":\"tt3748528\",\"Type\":\"movie\",\"Poster\":\"poster_10.jpg\"}]}";
        ArrayList<Film> arrayList = new ArrayList<>();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Search search = null;
        try {
            //search = mapper.readValue(new File("/home/lolitech/mobile/labs/FourthWonderfulApp/app/src/main/res/MoviesList.txt") , Search.class);
            search = mapper.readValue(data, Search.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert search != null;
        Collections.addAll(arrayList, search.getFilms());
        return arrayList;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FilmsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilmFragment newInstance(String param1, String param2) {
        FilmFragment fragment = new FilmFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_film, container,
                false);

        ScrollView scrollView = rootView.findViewById(R.id.scrollView);
        ArrayList<Film> films = parseData();
        films.sort(Comparator.comparing(Film::getTitle));
        LinearLayout linearLayout =  rootView.findViewById(R.id.layout);



        for (Film film: films) {
            if(!film.Type.equals("test")) {

                LinearLayout horizontalLayout = new LinearLayout(getActivity().getApplicationContext());
                horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 10, 10, 10);
                horizontalLayout.setLayoutParams(params);
                ImageView img = new ImageView(getActivity().getApplicationContext());
                switch (film.Poster){
                    case "poster_01.jpg":
                        img.setImageResource(R.drawable.poster_01);
                        break;
                    case "poster_02.jpg":
                        img.setImageResource(R.drawable.poster_02);
                        break;
                    case "poster_03.jpg":
                        img.setImageResource(R.drawable.poster_03);
                        break;
                    case "poster_05.jpg":
                        img.setImageResource(R.drawable.poster_05);
                        break;
                    case "poster_06.jpg":
                        img.setImageResource(R.drawable.poster_06);
                        break;
                    case "poster_07.jpg":
                        img.setImageResource(R.drawable.poster_07);
                        break;
                    case "poster_08.jpg":
                        img.setImageResource(R.drawable.poster_08);
                        break;
                    case "poster_10.jpg":
                        img.setImageResource(R.drawable.poster_10);
                        break;
                    default:
                        img.setImageResource(R.drawable.no_image_cut);
                }
                horizontalLayout.addView(img);

                TextView textView = new TextView(getActivity().getApplicationContext());
                String title = film.Title;
                if(film.imdbID.equals("tt0076759")){
                    title = film.Title.substring(0, (int)film.Title.length()/2);
                }
                String year = String.valueOf(film.Year);
                if(film.Year==0){
                    year = "dont known year";
                }
                textView.setText(title+"\n"+year+"\n"+film.Type);
                textView.setLayoutParams(params);
                textView.setTextColor(getResources().getColor(R.color.black));
                horizontalLayout.addView(textView);

                linearLayout.addView(horizontalLayout);
            }

        }

        return rootView;
    }
}