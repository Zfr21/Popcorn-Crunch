package zafer.celaloglu.com.popcorncrunch.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import zafer.celaloglu.com.popcorncrunch.R;
import zafer.celaloglu.com.popcorncrunch.adapters.MovieDetailListAdapter;
import zafer.celaloglu.com.popcorncrunch.models.MovieDetails;
import zafer.celaloglu.com.popcorncrunch.networking.TheMovieDBDetailsApi;

/**
 * Created by zafer on 26.10.15.
 */
public class DetailsActivity extends BaseActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView backdrop;
    private RecyclerView recyclerView;
    private MovieDetailListAdapter adapter;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detail;
    }

    @Override
    protected boolean getDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        backdrop = (ImageView)findViewById(R.id.backdrop);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int id = getIntent().getIntExtra("ID", 0);

        TheMovieDBDetailsApi.getInstance().getMovieDetails(id, new TheMovieDBDetailsApi.NetworkResponseListener(){

            @Override
            public void onSuccess(final MovieDetails details) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(details);
                    }
                });
            }

            @Override
            public void onFailure(Request request, IOException e) {

            }
        });
    }
    private void setData(MovieDetails details){
        collapsingToolbarLayout.setTitle(details.getTitle());
        Picasso.with(this).load("http://image.tmdb.org/t/p/w780" + details.getBackdrop_path()).into(backdrop);
        adapter = new  MovieDetailListAdapter(details);
        recyclerView.setAdapter(adapter);
    }
}
