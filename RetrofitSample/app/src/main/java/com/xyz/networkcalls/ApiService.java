package com.xyz.networkcalls;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Write all the API calls in this interface
 */
public interface ApiService {

    // Make an Api call and specify the type of HTTP request (i.e GET, POST ....) along with the end points.
    @GET("/photos")
    Call<List<PhotosResponse>> getPhotos();
}
