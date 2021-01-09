package android.bignerdranch.imdb.mvc.genreController;

import android.bignerdranch.imdb.mvc.model.Movie;
import android.bignerdranch.imdb.mvc.searchView.GenreResultsFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.bignerdranch.imdb.R;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GenreSearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Button mButton;
    Movie movie = new Movie();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_genre_search, container, false);

        Spinner genreSpinner = (Spinner) root.findViewById(R.id.genre_spinner);
        genreSpinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Comedy");
        categories.add("Sci-Fi");
        categories.add("Horror");
        categories.add("Animation");
        categories.add("Thriller");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext()
                , android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        genreSpinner.setAdapter(dataAdapter);

        Spinner yearSpinner = (Spinner) root.findViewById(R.id.year_spinner);
        yearSpinner.setOnItemSelectedListener(this);
        List<String> years = new ArrayList<String>();
        years.add("2019");
        years.add("2020");
        ArrayAdapter<String> yearDataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext()
                , android.R.layout.simple_spinner_item, years);
        yearDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearDataAdapter);

        mButton = (Button) root.findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                GenreResultsFragment genreResultsFragment = new GenreResultsFragment();

                Bundle bundle = new Bundle();

                bundle.putSerializable("movie", movie);
                genreResultsFragment.setArguments(bundle);
                ft.replace(android.R.id.content, genreResultsFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item;
        switch (parent.getId()){
            case R.id.genre_spinner:
                item = parent.getItemAtPosition(position).toString();
                movie.setGenre(item);
                break;
            case R.id.year_spinner:
                item = parent.getItemAtPosition(position).toString();
                movie.setYear(item);
                break;

        }

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}