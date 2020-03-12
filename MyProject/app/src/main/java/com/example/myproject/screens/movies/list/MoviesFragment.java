package com.example.myproject.screens.movies.list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.model.MoviesResponse;
import com.example.myproject.network.NetworkService;
import com.example.myproject.screens.BaseFragment;
import com.example.myproject.screens.movies.details.MoviesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MoviesFragment extends BaseFragment {
    String MOVIES_FIELDS = "images,stars,id,title,poster,body_text,locale,running_time,age_restriction,country";
    int PAGE_SIZE = 50;

    @Nullable
    @BindView(R.id.recyclerView)
    RecyclerView moviesRecyclerView;
    private Unbinder unbinder;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();
        return fragment;
    }

    //Load layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment, container, false);//Находим xml
        unbinder = ButterKnife.bind(this, view);
        //Refresh page head name
        updateActivityTitle(getString(R.string.movies));
        init();
        return view;
    }


    private void init(){
        //Create subscriber, that returns a sevres response.
        Observable<MoviesResponse> moviesResponseObservable = loadData();

        //Create a subscriber
        Disposable disposable = moviesResponseObservable
                .subscribe(moviesResponse -> updateViews(moviesResponse));

        compositeDisposable.add(disposable);
    }

    public void updateViews(MoviesResponse moviesResponse) {
        if (moviesResponse != null && moviesResponse.getResults() != null) {
            //Adapter creation
            MoviesAdapter moviesAdapter = new MoviesAdapter(getContext(), moviesResponse.getResults());
            moviesRecyclerView.setAdapter(moviesAdapter);
            Disposable disposable = moviesAdapter.getClickItemObservable().subscribe(this::openDetails);
            compositeDisposable.add(disposable);
        }
    }

    public Observable<MoviesResponse> loadData(){
        Observable<MoviesResponse> responseObservable = NetworkService.getInstance()//создание HTTP клиента и вызов метода с сервера
                .getJSONApi()
                .getMovies(MOVIES_FIELDS)
                .subscribeOn(Schedulers.io())//где выполняется
                .observeOn(AndroidSchedulers.mainThread());//гд получаем ответ

        return responseObservable;
    }

    private void openDetails(MoviesResponse.Result result){
        if (result != null){
            MoviesActivity.launchActivity(getContext(), result);
        }
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        compositeDisposable.dispose();
        super.onDestroyView();
    }
}
