package com.example.myproject.screens.movies.details;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myproject.R;
import com.example.myproject.model.MoviesResponse;
import com.example.myproject.screens.BaseActivity;
import com.example.myproject.screens.movies.list.MoviesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesActivity extends BaseActivity {

    private static final String RESULT_KEY = "RESULT_KEY";
    @BindView(R.id.movie_text)
    TextView movieText;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.movie_country)
    TextView movieCountry;
    @BindView(R.id.movie_age)
    TextView movieAge;
    @BindView(R.id.movie_stars)
    TextView movieStars;
    @BindView(R.id.movie_description)
    TextView movieDescription;
    @BindView(R.id.view_pager_indicator)
    TextView viewPagerIndicator;

    private MoviesResponse.Result result;

    //Activity launch with MoviesResponse.Result.
    public static void launchActivity(Context context, MoviesResponse.Result result) {
        Intent myIntent = new Intent(context, MoviesActivity.class);
        myIntent.putExtra(RESULT_KEY, result); //Optional parameters
        context.startActivity(myIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        //get MoviesResponse.Result from Intent
        result = (MoviesResponse.Result) getIntent().getSerializableExtra(RESULT_KEY);


        //Set value for Views in activity (server can have a null response)
        if (!TextUtils.isEmpty(result.getCountry())) {
            movieCountry.setText(result.getCountry());
            updateActivityTitle(result.getTitle());
        }

        initImagesGallery();


        if (!TextUtils.isEmpty(result.getAgeRestriction())) {
            movieAge.setText(result.getAgeRestriction());
        }

        if (!TextUtils.isEmpty(result.getTitle())) {
            movieText.setText(result.getTitle());
        }

        setupDescription();
    }


    //Connect gallery to ViewPager
    private void initImagesGallery(){
        if (result.getImages() != null) {
            viewPagerIndicator.setText(1 + "/" + result.getImages().size());
            MoviesPagerAdapter moviePagerAdapter = new MoviesPagerAdapter(getSupportFragmentManager(), result.getImages());
            viewPager.setAdapter(moviePagerAdapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {}

                @Override
                public void onPageSelected(int i) {
                    //Changing number during image sliding
                    viewPagerIndicator.setText((i + 1) + "/" + result.getImages().size());
                }

                @Override
                public void onPageScrollStateChanged(int i) {}
            });
        }
    }

    private void setupDescription() {
        if (!TextUtils.isEmpty(result.getStars())) {
            movieStars.setText(result.getStars());
        }

        if (!TextUtils.isEmpty(result.getBodyText())) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                movieDescription.setText(Html.fromHtml(result.getBodyText(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                movieDescription.setText(Html.fromHtml(result.getBodyText()));
            }
        }
    }
}
