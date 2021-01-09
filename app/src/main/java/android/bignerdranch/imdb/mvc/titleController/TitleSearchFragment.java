package android.bignerdranch.imdb.mvc.titleController;

import android.bignerdranch.imdb.mvc.model.Search;
import android.bignerdranch.imdb.mvc.searchView.SearchResultsFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.bignerdranch.imdb.R;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TitleSearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Button mButton;
    Search search = new Search();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_title_search, container, false);
        final EditText title = (EditText) root.findViewById(R.id.title_et);
        final EditText year = (EditText) root.findViewById(R.id.year_et);

        Spinner genreSpinner = (Spinner) root.findViewById(R.id.type_spinner);
        genreSpinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("");
        categories.add("Movie");
        categories.add("Series");
        categories.add("Episode");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext()
                , android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        genreSpinner.setAdapter(dataAdapter);


        mButton = (Button) root.findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                SearchResultsFragment searchResultsFragment = new SearchResultsFragment();

                Bundle bundle = new Bundle();

                if(title.getText().toString().length() == 0){
                    title.setError("Title required!");
                } else {
                    search.setBase("http://www.omdbapi.com/?apikey=8e08df7b");
                    search.setTitle(title.getText().toString());
                    if(year.getText().toString().length() != 0)
                        search.setYear(year.getText().toString());
                    bundle.putSerializable("search", search);
                    searchResultsFragment.setArguments(bundle);
                    ft.replace(android.R.id.content, searchResultsFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }

            }
        });
        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String item = parent.getItemAtPosition(position).toString();
        search.setType(item);
        // Showing selected spinner item
        if(!item.isEmpty())
            Toast.makeText(parent.getContext(), item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
