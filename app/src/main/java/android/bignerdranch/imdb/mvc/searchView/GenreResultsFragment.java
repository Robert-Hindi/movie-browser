package android.bignerdranch.imdb.mvc.searchView;

import android.bignerdranch.imdb.mvc.model.Movie;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.bignerdranch.imdb.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GenreResultsFragment extends Fragment {

    RecyclerView mRecyclerView;
    Adapter Adapter;
    ArrayList<Movie> mMovieList = new ArrayList<>();

    public GenreResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Adapter = new Adapter(mMovieList);

        Bundle bundle = getArguments();
        Movie movie_search = (Movie) bundle.getSerializable("movie");

        final View rootView = inflater.inflate(R.layout.fragment_genre_results, container, false);

        try {
            JSONObject root = new JSONObject(loadJSONFromAsset());
            JSONArray returnedMovies = root.getJSONArray("Movie");
            int numOfMovies = returnedMovies.length();
            for (int i = 0; i < numOfMovies; i++) {
                JSONObject movies = returnedMovies.getJSONObject(i);

                Movie movie = new Movie();

                movie.setPoster(movies.getString("Poster"));

                // movie text
                movie.setTitle(movies.getString("Title"));
                movie.setGenre(movies.getString("Genre"));
                movie.setYear(movies.getString("Year"));


                if(movie_search.getGenre().equals(movie.getGenre()) &&
                    movie_search.getYear().equals(movie.getYear())) {
                    mMovieList.add(movie);
                }
            }

            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(Adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open("Movie.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}