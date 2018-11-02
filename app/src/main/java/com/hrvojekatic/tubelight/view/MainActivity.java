package com.hrvojekatic.tubelight.view;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.hrvojekatic.tubelight.R;
import com.hrvojekatic.tubelight.databinding.ActivityMainBinding;
import com.hrvojekatic.tubelight.model.Item;
import com.hrvojekatic.tubelight.model.SearchResponse;
import com.hrvojekatic.tubelight.utils.SoftKeyboardUtils;
import com.hrvojekatic.tubelight.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialize();
	}

	private void initialize() {
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		binding.searchResultsRecyclerView.setHasFixedSize(true);
		binding.searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		final SearchViewModel searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
		searchViewModel.getSearchQueryObservable().observe(this, s -> binding.searchForEditText.setText(s));
		searchViewModel.searchForLastQuery();
		searchViewModel.getSearchResponseObservable().observe(this, this::displaySearchResults);
		binding.searchButton.setOnClickListener(v -> {
			binding.searchForEditText.setVisibility(View.VISIBLE);
			binding.searchForEditText.selectAll();
			SoftKeyboardUtils.showSoftKeyboard(this, binding.searchForEditText);
		});
		binding.searchForEditText.setOnEditorActionListener((v, actionId, event) -> {
			if (actionId == EditorInfo.IME_ACTION_SEARCH) {
				SoftKeyboardUtils.hideSoftKeyboard(this, binding.searchForEditText);
				doSearch(searchViewModel);
				binding.searchForEditText.setVisibility(View.GONE);
				return true;
			}
			return false;
		});
	}

	private void doSearch(SearchViewModel searchViewModel) {
		searchViewModel.searchForNewQuery(binding.searchForEditText.getText().toString());
	}

	private void displaySearchResults(List<SearchResponse> results) {
		List<Item> items = new ArrayList<>();
		for (SearchResponse response : results) {
			items.addAll(response.getItems());
		}
		SearchResultsAdapter searchResultsAdapter = new SearchResultsAdapter(this, items);
		binding.searchResultsRecyclerView.setAdapter(searchResultsAdapter);
		searchResultsAdapter.notifyDataSetChanged();
	}

}