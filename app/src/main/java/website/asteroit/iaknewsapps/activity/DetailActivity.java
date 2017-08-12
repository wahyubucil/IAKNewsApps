package website.asteroit.iaknewsapps.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import website.asteroit.iaknewsapps.R;
import website.asteroit.iaknewsapps.model.ArticlesItem;

public class DetailActivity extends AppCompatActivity {

    private static final String KEY_EXTRA_NEWS = "news";
    private ArticlesItem mArticlesItem;

    public static void start(Context context, String newsJson) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_EXTRA_NEWS, newsJson);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getIntent().hasExtra(KEY_EXTRA_NEWS)) {
            String newsJson = getIntent().getStringExtra(KEY_EXTRA_NEWS);
            mArticlesItem = new ArticlesItem().fromJson(newsJson);
            Toast.makeText(this, mArticlesItem.getTitle(), Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }
    }
}
