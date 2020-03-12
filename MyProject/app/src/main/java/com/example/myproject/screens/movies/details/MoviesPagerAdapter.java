package com.example.myproject.screens.movies.details;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myproject.model.MoviesResponse;

import java.util.List;

public class MoviesPagerAdapter extends FragmentStatePagerAdapter {

    private List<MoviesResponse.Image> images;

    public MoviesPagerAdapter(FragmentManager fm, List<MoviesResponse.Image> images) {
        super(fm);
        this.images = images;
    }

    //create View for one element of the ViewPager
    @Override
    public Fragment getItem(int position) {
        return GalleryViewPagerFragment.newInstance(images.get(position).getImage());
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
