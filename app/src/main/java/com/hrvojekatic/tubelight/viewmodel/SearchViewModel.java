package com.hrvojekatic.tubelight.viewmodel;

import android.app.Application;

import com.hrvojekatic.tubelight.service.YoutubeClient;
import com.hrvojekatic.tubelight.model.SearchResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class SearchViewModel extends AndroidViewModel {
	private MutableLiveData<String> searchQueryObservable;
	private LiveData<List<SearchResponse>> searchResponseObservable;

	public SearchViewModel(@NonNull Application application) {
		super(application);
	}

	public LiveData<String> getSearchQueryObservable() {
		if (searchQueryObservable == null) {
			searchQueryObservable = new MutableLiveData<>();
		}
		return searchQueryObservable;
	}

	public void searchForNewQuery(String query) {
		searchQueryObservable.setValue(query);
		searchResponseObservable = YoutubeClient.getInstance().searchFor(searchQueryObservable.getValue());
	}

	public void searchForLastQuery() {
		searchResponseObservable = YoutubeClient.getInstance().searchFor(searchQueryObservable.getValue());
	}

	public List<SearchResponse> getSearchResponse() {
		return searchResponseObservable.getValue();
	}

	public LiveData<List<SearchResponse>> getSearchResponseObservable() {
		return searchResponseObservable;
	}
}
