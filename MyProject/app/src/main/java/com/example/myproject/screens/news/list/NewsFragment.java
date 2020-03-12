package com.example.myproject.screens.news.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.model.NewsResponse;
import com.example.myproject.network.NetworkService;
import com.example.myproject.screens.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class NewsFragment extends BaseFragment{
    private String extraFields = "id,title,slug,publication_date,body_text,images,place";
    private String textFormat = "plain";

    @BindView(R.id.recyclerView)
    RecyclerView myRecyclerView;
    private Unbinder unbinder;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    //Interface creation
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment, container, false);//Находим xml
        unbinder = ButterKnife.bind(this, view);
        updateActivityTitle(getString(R.string.news));
        init();
        return view;
    }

    private void init(){
        //Create subscriber, that returns a sevres response.
        Observable<NewsResponse> newsResponseObservable = loadData();

        //Create a subscriber
        Disposable disposable = newsResponseObservable
                .subscribe(newsResponse -> updateViews(newsResponse));

        compositeDisposable.add(disposable);
    }

    //When there is response from server - using this method.
    //NewsResponse - server response.
    public void updateViews(NewsResponse newsResponse) {
        if (newsResponse != null && newsResponse.getResults() != null) {
            //Adaptor creation
            NewsAdapter newsAdapter = new NewsAdapter(getContext(), newsResponse.getResults());
            myRecyclerView.setAdapter(newsAdapter);

        }
    }

    //Making a server request. Create publisher, who send back an answer.
    public Observable<NewsResponse> loadData(){
        Observable<NewsResponse> responseObservable = NetworkService.getInstance()//создание HTTP клиента и вызов метода с сервера
                .getJSONApi()
                .getNews(extraFields, textFormat)
                .subscribeOn(Schedulers.io())//where executes
                .observeOn(AndroidSchedulers.mainThread());//where we get an answer

        return responseObservable;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        compositeDisposable.dispose();
        super.onDestroyView();
    }
}
