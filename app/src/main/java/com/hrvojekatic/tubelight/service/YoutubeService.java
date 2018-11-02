package com.hrvojekatic.tubelight.service;

import com.hrvojekatic.tubelight.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface YoutubeService {
	String BASE_URL = "https://www.googleapis.com/youtube/v3/";

	@GET("search?part=snippet&maxResults=50")
	Call<SearchResponse> searchFor(@Query("q") String query, @Query("key") String key);

	@GET("search?part=snippet&maxResults=50")
	Call<SearchResponse> searchFor(@Query("q") String query, @Query("pageToken") String pageToken, @Query("key") String key);
}
