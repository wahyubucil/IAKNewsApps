package website.asteroit.iaknewsapps.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import website.asteroit.iaknewsapps.model.NewsApiResponse;

/**
 * Created by Wahyu on 12/08/2017.
 */

public interface ApiService {

    @GET("articles")
    Call<NewsApiResponse> getArticle(
            @Query("source") String source,
            @Query("apiKey") String apiKey
    );

}
