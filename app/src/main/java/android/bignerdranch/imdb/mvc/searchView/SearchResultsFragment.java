package android.bignerdranch.imdb.mvc.searchView;

import android.bignerdranch.imdb.mvc.model.Movie;
import android.bignerdranch.imdb.mvc.model.Search;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bignerdranch.imdb.R;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchResultsFragment extends Fragment {

    RecyclerView mRecyclerView;
    Adapter Adapter;
    ArrayList<Movie> mMovieList = new ArrayList<>();

    public SearchResultsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Adapter = new Adapter(mMovieList);

        Bundle bundle = getArguments();
        Search search = (Search) bundle.getSerializable("search");

        final View rootView = inflater.inflate(R.layout.fragment_title_results, container, false);

        String base = search.getBase();
        String title = search.getTitle();
        String type = "";
        if(search.getType() != null){
            type = search.getType();
        }
        String year = "";
        if(search.getYear() != null){
            year = search.getYear();
        }

        String url = base + title + type + year;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject root = new JSONObject(response);
                            JSONArray returnedMovies = root.getJSONArray("Search");
                            int numOfMovies = returnedMovies.length();
                            for (int i = 0; i < numOfMovies; i++) {
                                JSONObject movies = returnedMovies.getJSONObject(i);

                                Movie movie = new Movie();

                                // faculty img
                                movie.setPoster(movies.getString("Poster"));

                                // faculty text
                                movie.setTitle(movies.getString("Title"));
                                movie.setYear(movies.getString("Year"));
                                movie.setUrl(movies.getString("imdbID"));

                                mMovieList.add(movie);

                            }
                            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(Adapter);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(stringRequest);

        return rootView;
    }

}