package website.asteroit.iaknewsapps.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import website.asteroit.iaknewsapps.BuildConfig;
import website.asteroit.iaknewsapps.R;
import website.asteroit.iaknewsapps.adapter.NewsListAdapter;
import website.asteroit.iaknewsapps.model.ArticlesItem;
import website.asteroit.iaknewsapps.model.NewsApiResponse;
import website.asteroit.iaknewsapps.rest.ApiClient;
import website.asteroit.iaknewsapps.rest.ApiService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.rv_news_list) RecyclerView mRvNewsList;

    private LinearLayoutManager mLayoutManager;
    private NewsListAdapter mNewsListAdapterDummy;
    private NewsListAdapter mNewsListAdapter;
    private List<ArticlesItem> mListArticle = new ArrayList<>();

    private static final String NEWS_SOURCE = "techcrunch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvNewsList.setLayoutManager(mLayoutManager);

        mNewsListAdapterDummy = new NewsListAdapter();
        mNewsListAdapter = new NewsListAdapter();

//        mRvNewsList.setAdapter(mNewsListAdapterDummy);
        mRvNewsList.setAdapter(mNewsListAdapter);

        getData();
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

    private void getData() {
        ApiService apiService = ApiClient.getRetrofitClient().create(ApiService.class);
        Call<NewsApiResponse> newsApiResponseCall = apiService.getArticle(
                NEWS_SOURCE,
                BuildConfig.API_KEY
        );

        newsApiResponseCall.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                NewsApiResponse apiResponse = response.body();
                if (apiResponse != null) {
                    mListArticle = apiResponse.getArticles();
                    mNewsListAdapter.setNewsListData(mListArticle);
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error retrieving data from internet : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "OnFailure : " + t.getMessage());
            }
        });
    }
}
