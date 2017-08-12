package website.asteroit.iaknewsapps.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import website.asteroit.iaknewsapps.R;
import website.asteroit.iaknewsapps.adapter.NewsListAdapter;
import website.asteroit.iaknewsapps.model.ArticlesItem;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_news_list) RecyclerView mRvNewsList;

    private LinearLayoutManager mLayoutManager;
    private NewsListAdapter mNewsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvNewsList.setLayoutManager(mLayoutManager);

        mNewsListAdapter = new NewsListAdapter(getDummyData());
        mRvNewsList.setAdapter(mNewsListAdapter);
    }

    private List<ArticlesItem> getDummyData() {
        List<ArticlesItem> dummyList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ArticlesItem dummyNews = new ArticlesItem();

            dummyNews.setUrlToImage("https://tctechcrunch2011.files.wordpress.com/2017/08/aug_chart_1.png?w=764&h=400&crop=1");
            dummyNews.setTitle("News " + i);
            dummyNews.setDescription("Lorem ipsum dolor sit amet constecteuer adispicing elit, Lorem ipsum dolor sit amet constecteuer adispicing elit");

            dummyList.add(dummyNews);
        }

        return dummyList;
    }
}
