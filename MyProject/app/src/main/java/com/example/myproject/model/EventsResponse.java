package com.example.myproject.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventsResponse implements Serializable{

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("next")
    @Expose
    private Object next;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
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

    public class Result implements Serializable {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("site_url")
        @Expose
        private String siteUrl;
        @SerializedName("subway")
        @Expose
        private String subway;
        @SerializedName("is_closed")
        @Expose
        private boolean isClosed;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("has_parking_lot")
        @Expose
        private boolean hasParkingLot;

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

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSiteUrl() {
            return siteUrl;
        }

        public void setSiteUrl(String siteUrl) {
            this.siteUrl = siteUrl;
        }

        public String getSubway() {
            return subway;
        }

        public void setSubway(String subway) {
            this.subway = subway;
        }

        public boolean isIsClosed() {
            return isClosed;
        }

        public void setIsClosed(boolean isClosed) {
            this.isClosed = isClosed;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public boolean isHasParkingLot() {
            return hasParkingLot;
        }

        public void setHasParkingLot(boolean hasParkingLot) {
            this.hasParkingLot = hasParkingLot;
        }

    }

}



