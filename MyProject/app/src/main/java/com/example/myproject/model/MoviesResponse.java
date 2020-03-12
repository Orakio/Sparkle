package com.example.myproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesResponse implements Serializable {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();
    private final static long serialVersionUID = -5470024213735585813L;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }


    public class Image implements Serializable
    {
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("source")
        @Expose
        private Source source;
        private final static long serialVersionUID = 6085898425302506962L;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

    }

    public class Poster implements Serializable
    {
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("source")
        @Expose
        private Source source;
        private final static long serialVersionUID = -655786091635388028L;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

    }


    public class Result implements Serializable
    {
        @SerializedName("images")
        @Expose
        private List<Image> images = new ArrayList<Image>();
        @SerializedName("stars")
        @Expose
        private String stars;
        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("body_text")
        @Expose
        private String bodyText;
        @SerializedName("locale")
        @Expose
        private String locale;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("running_time")
        @Expose
        private int runningTime;
        @SerializedName("age_restriction")
        @Expose
        private String ageRestriction;
        @SerializedName("trailer")
        @Expose
        private String trailer;
        @SerializedName("poster")
        @Expose
        private Poster poster;
        private final static long serialVersionUID = -1167082045505986557L;

        public List<Image> getImages() {
            return images;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBodyText() {
            return bodyText;
        }

        public void setBodyText(String bodyText) {
            this.bodyText = bodyText;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getRunningTime() {
            return runningTime;
        }

        public void setRunningTime(int runningTime) {
            this.runningTime = runningTime;
        }

        public String getAgeRestriction() {
            return ageRestriction;
        }

        public void setAgeRestriction(String ageRestriction) {
            this.ageRestriction = ageRestriction;
        }

        public String getTrailer() {
            return trailer;
        }

        public void setTrailer(String trailer) {
            this.trailer = trailer;
        }

        public Poster getPoster() {
            return poster;
        }

        public void setPoster(Poster poster) {
            this.poster = poster;
        }

    }

    public class Source implements Serializable
    {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("link")
        @Expose
        private String link;
        private final static long serialVersionUID = -7089908777712707344L;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

    }


}



