package website.asteroit.iaknewsapps.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import website.asteroit.iaknewsapps.R;
import website.asteroit.iaknewsapps.model.ArticlesItem;

/**
 * Created by Wahyu on 06/08/2017.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {

    private List<ArticlesItem> mNewsList = new ArrayList<>();

    public NewsListAdapter() {
    }

    @Override
    public NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false);
        return new NewsListViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {
        ArticlesItem currentNews = mNewsList.get(position);
        holder.bind(currentNews);
    }

    @Override
    public int getItemCount() {
        if (mNewsList == null) return 0;
        return mNewsList.size();
    }

    public void setNewsListData(List<ArticlesItem> newsList) {
        mNewsList.clear();
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    public class NewsListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_news_list_photo) ImageView mIvNewsListPhoto;
        @BindView(R.id.tv_news_list_title) TextView mTvNewsListTitle;
        @BindView(R.id.tv_news_list_description) TextView mTvNewsListDescription;

        public NewsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ArticlesItem newsItem) {
            Glide.with(mIvNewsListPhoto.getContext()).load(newsItem.getUrlToImage()).into(mIvNewsListPhoto);
            mTvNewsListTitle.setText(newsItem.getTitle());
            mTvNewsListDescription.setText(newsItem.getDescription());
        }
    }
}
