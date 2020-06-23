package com.example.project_ln.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("creator")
    @Expose
    private String creator;
    @SerializedName("price")
    @Expose
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @SerializedName("__v")
    @Expose
    private Integer v;

    public Book (String id, String title, String content, String imagePath, String creator,String price) {
        this.creator = creator;
        this.title = title;
        this.price = price;
        this.content = content;
        this.imagePath = imagePath;
        this.id = id;


    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Post{" +
                "user Id=" + creator +
                ", Title ='" + title + '\'' +
                ", Price ='" + price + '\'' +
                ", Text=" + content + '\'' +
                ", imagepath=" + imagePath + '\'' +
                ", id ='" + id +
                '}';
    }
}
