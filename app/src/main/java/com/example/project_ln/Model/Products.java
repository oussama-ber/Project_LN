package com.example.project_ln.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Products {
    @SerializedName("books")
    @Expose
    private List<Book> products ;

    public List<Book> getPosts() {
        return products;
    }

    public void setPosts(ArrayList<Book> products) {
        this.products = products;
    }



    @Override
    public String toString() {
        return "posts{" +
                "posts=" + products +
                '}';
    }
}
