package website.asteroit.iaknewsapps.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wahyu on 12/08/2017.
 */

public class ApiClient {

    private static final String BASE_URL = "https://newsapi.org/v1/";
    private static Retrofit sRetrofit;

    public static Retrofit getRetrofitClient() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return sRetrofit;
    }

}
