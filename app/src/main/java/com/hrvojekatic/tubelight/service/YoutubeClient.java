package com.hrvojekatic.tubelight.service;

import com.hrvojekatic.tubelight.BuildConfig;
import com.hrvojekatic.tubelight.model.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YoutubeClient {
	private static YoutubeService youtubeService;
	private static YoutubeClient youtubeClient;
	private final MutableLiveData<List<SearchResponse>> data = new MutableLiveData<>();
	private String pageToken;

	private YoutubeClient() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(YoutubeService.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		youtubeService = retrofit.create(YoutubeService.class);
	}

	public static YoutubeClient getInstance() {
		if (youtubeClient == null) {
			youtubeClient = new YoutubeClient();
		}
		return youtubeClient;
	}

	public MutableLiveData<List<SearchResponse>> searchFor(String query) {
		List<SearchResponse> allData = new ArrayList<>();
		youtubeService.searchFor(query, BuildConfig.YoutubeApiKey)
				.enqueue(new Callback<SearchResponse>() {
					@Override
					public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
						List<SearchResponse> results = new ArrayList<>();
						if (response.isSuccessful()) {
							results.add(response.body());
							pageToken = response.body().getNextPageToken();
							allData.addAll(results);
//							data.setValue(allData);
						}
					}

					@Override
					public void onFailure(Call<SearchResponse> call, Throwable t) {
						t.getLocalizedMessage();
					}
				});

		youtubeService.searchFor(query, pageToken, BuildConfig.YoutubeApiKey)
				.enqueue(new Callback<SearchResponse>() {
					@Override
					public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
						List<SearchResponse> results = new ArrayList<>();
						if (response.isSuccessful()) {
							results.add(response.body());
							pageToken = response.body().getNextPageToken();
							allData.addAll(results);
							data.setValue(allData);
						}
					}

					@Override
					public void onFailure(Call<SearchResponse> call, Throwable t) {
						t.getLocalizedMessage();
					}
				});
		return data;
	}
}
