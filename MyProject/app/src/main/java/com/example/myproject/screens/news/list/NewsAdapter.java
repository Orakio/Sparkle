package com.example.myproject.screens.news.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.NewsResponse;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    //Издатель для кликов по элементам списка
    private List<NewsResponse.Result> rData;//Список
    private Context context;//Нужен, чтобы мы ниже смогли найти лейоут

    public NewsAdapter(Context context, List<NewsResponse.Result> data) {
        this.rData = data;
        this.context = context;
    }

    // Метод, который вызывается чтобы в первый раз создать нашу View для элемента списка
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {//Когда создаем ячейку
        LayoutInflater inflater = LayoutInflater.from(context);//Объект, который поможет нам найти лейоут
        View view = inflater.inflate(R.layout.news_adapter_element, viewGroup, false);//находим лейоут
        return new ViewHolder(view);//создаем ячейку
    }

    // Метод, который вызывается, чтобы в созданную View перезаписать новое значение при прокрутке (скроллинге)
    // ViewHolder holder - объект, который содержит наши View
    // int position - позиция в списке
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {//когда переиспользуем ячейку
        final NewsResponse.Result result = rData.get(position);//Берем нужные данные из списка
        holder.textViewNews.setText(result.getTitle());//Перезаписываем текст в TextView при прокрутке
        loadImage(result.getImages(), holder);
    }

    //Загрузка картинки из интернета
    private void loadImage(List<NewsResponse.Image> images, ViewHolder holder){
        if (images != null && !images.isEmpty()){
            Glide.with(context)
                    .load(images.get(0).getImage())
                    .into(holder.imageNews);
        }
    }

    //Метод, который говорит, сколько у нас будет элементов в списке
    @Override
    public int getItemCount() {
        return rData.size();
    }

    //Класс, который хранит наши View, чтобы их показывать на экране и переипользовать в случае чего
    public class ViewHolder extends RecyclerView.ViewHolder {//класс для ячейку
        private TextView textViewNews;
        private ImageView imageNews;

        ViewHolder(View itemView) {
            super(itemView);
            textViewNews = itemView.findViewById(R.id.text_title);
            imageNews = itemView.findViewById(R.id.imageView);
        }

    }
}
