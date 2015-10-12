package zafer.celaloglu.com.popcorncrunch;

import android.app.Application;

import com.squareup.okhttp.OkHttpClient;

import zafer.celaloglu.com.popcorncrunch.networking.TheMovieDBApi;

/**
 * Created by zafer on 9.10.15.
 */
public class PopcornCrunchApplication extends Application {

    private static PopcornCrunchApplication mInstance;

    public final OkHttpClient client = new OkHttpClient();

    public static synchronized PopcornCrunchApplication getInstance(){return mInstance;}
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        TheMovieDBApi.create(Keys.THE_MOVIE_DB);
    }
}
