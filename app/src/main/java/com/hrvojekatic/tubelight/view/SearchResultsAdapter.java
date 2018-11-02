package com.hrvojekatic.tubelight.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hrvojekatic.tubelight.R;
import com.hrvojekatic.tubelight.databinding.SearchResultItemBinding;
import com.hrvojekatic.tubelight.model.Item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchResultViewHolder> {
	private final List<Item> resultsList;
	private Context context;
	private LayoutInflater layoutInflater;

	public SearchResultsAdapter(Context context, List<Item> resultsList) {
		this.context = context;
		this.resultsList = resultsList;
	}

	@NonNull
	@Override
	public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		if (layoutInflater == null) {
			layoutInflater = LayoutInflater.from(parent.getContext());
		}
		SearchResultItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.search_result_item, parent, false);
		return new SearchResultViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
		Item searchResult = resultsList.get(position);
		holder.binding.videoTitleTextView.setText(searchResult.getSnippet().getTitle());
		holder.binding.channelTitleTextView.setText(context.getString(R.string.from_channel, searchResult.getSnippet().getChannelTitle()));
		holder.binding.publishedAtTextView.setText(context.getString(R.string.published_at, searchResult.getSnippet().getPublishedAt()));
	}

	@Override
	public int getItemCount() {
		return resultsList.size();
	}

	public class SearchResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private final SearchResultItemBinding binding;

		SearchResultViewHolder(SearchResultItemBinding binding) {
			super(binding.getRoot());
			this.binding = binding;
			this.itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			String videoId = resultsList.get(getLayoutPosition()).getId().getVideoId();
			Intent intent = new Intent(v.getContext(), PlayerActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("videoId", videoId);
			v.getContext().startActivity(intent);
		}
	}
}