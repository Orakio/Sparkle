package com.example.myproject.screens.movies.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.MoviesResponse;

import java.util.List;

import io.reactivex.subjects.BehaviorSubject;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{

    private BehaviorSubject<MoviesResponse.Result> clickItemObservable = BehaviorSubject.create();
    private List<MoviesResponse.Result> movie;//Список
    private Context context;

    public MoviesAdapter(Context context, List<MoviesResponse.Result> data) {
        this.movie = data;
        this.context = context;
    }

    // Метод, который вызывается чтобы в первый раз создать нашу View для элемента списка
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {//Когда создаем ячейку
        LayoutInflater inflater = LayoutInflater.from(context);//Объект, который поможет нам найти лейоут
        View view = inflater.inflate(R.layout.movies_adapter_layout, viewGroup, false);//находим лейоут
        return new ViewHolder(view);//создаем ячейку
    }

    // Метод, который вызывается, чтобы в созданную View перезаписать новое значение при прокрутке (скроллинге)
    // ViewHolder holder - объект, который содержит наши View
    // int position - позиция в списке
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {//когда переиспользуем ячейку
        final MoviesResponse.Result result = movie.get(position);//Берем нужные данные из списка
        holder.textViewMovie.setText(result.getTitle());//Перезаписываем текст в TextView при прокрутке
        holder.itemView.setOnClickListener(v -> clickItemObservable.onNext(result));
        loadImage(result.getImages(), holder);
    }

    public BehaviorSubject<MoviesResponse.Result> getClickItemObservable() {
        return clickItemObservable;
    }

    private void loadImage(List<MoviesResponse.Image> images, ViewHolder holder){
        if (images != null && !images.isEmpty()) {
            String imageUrl = images.get(0).getImage();
            Glide.with(context)
                    .load(imageUrl)
                    .into(holder.imageView);
        }
    }

    //Метод, который говорит, сколько у нас будет элементов в списке
    @Override
    public int getItemCount() {
        return movie.size();
    }

    //Класс, который хранит наши View, чтобы их показывать на экране и переипользовать в случае чего
    public class ViewHolder extends RecyclerView.ViewHolder {//класс для ячейку
        private TextView textViewMovie;
        private ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            textViewMovie = itemView.findViewById(R.id.text_title);
            imageView = itemView.findViewById(R.id.imageView);
        }

    }
}
