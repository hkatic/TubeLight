package com.hrvojekatic.tubelight.view;

import android.os.Bundle;
import android.widget.Toast;

import com.hrvojekatic.tubelight.R;
import com.hrvojekatic.tubelight.databinding.ActivityPlayerBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class PlayerActivity extends AppCompatActivity {
	private ActivityPlayerBinding binding;
	private String videoId = null;
	private boolean playing = false;
	private float currentTime = 0.0F;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_player);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			videoId = extras.getString("videoId");
		}
		getLifecycle().addObserver(binding.youtubePlayerView);
		binding.youtubePlayerView.initialize(new YouTubePlayerInitListener() {
			@Override
			public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
				initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
					@Override
					public void onReady() {
						super.onReady();
						if (videoId != null) {
							initializedYouTubePlayer.loadVideo(videoId, 0);
							setButtonClickListeners(initializedYouTubePlayer);
						}
					}

					@Override
					public void onStateChange(@NonNull PlayerConstants.PlayerState state) {
						super.onStateChange(state);
						if (state == PlayerConstants.PlayerState.ENDED) {
							Toast.makeText(PlayerActivity.this, R.string.end, Toast.LENGTH_SHORT).show();
						} else if (state == PlayerConstants.PlayerState.PAUSED) {
							playing = !playing;
							binding.playButton.setText(R.string.play);
						} else if (state == PlayerConstants.PlayerState.PLAYING) {
							playing = true;
							binding.playButton.setText(R.string.pause);
						}
					}

					@Override
					public void onPlaybackQualityChange(@NonNull PlayerConstants.PlaybackQuality playbackQuality) {
						super.onPlaybackQualityChange(playbackQuality);
					}

					@Override
					public void onPlaybackRateChange(@NonNull PlayerConstants.PlaybackRate rate) {
						super.onPlaybackRateChange(rate);
					}

					@Override
					public void onError(@NonNull PlayerConstants.PlayerError error) {
						super.onError(error);
					}

					@Override
					public void onApiChange() {
						super.onApiChange();
					}

					@Override
					public void onCurrentSecond(float second) {
						super.onCurrentSecond(second);
						currentTime = second;
					}

					@Override
					public void onVideoDuration(float duration) {
						super.onVideoDuration(duration);
					}

					@Override
					public void onVideoLoadedFraction(float fraction) {
						super.onVideoLoadedFraction(fraction);
					}

					@Override
					public void onVideoId(@NonNull String videoId) {
						super.onVideoId(videoId);
					}
				});
			}
		}, true);
	}

	private void setButtonClickListeners(YouTubePlayer player) {
		binding.playButton.setOnClickListener(v -> {
			if (playing) {
				player.pause();
			} else {
				player.play();
			}
		});
		binding.rewindButton.setOnClickListener(v -> player.seekTo(currentTime - 10.0F));
		binding.fastForwardButton.setOnClickListener(v -> player.seekTo(currentTime + 10.0F));
		binding.moveToBeginningButton.setOnClickListener(v -> player.seekTo(0.0F));
	}
}