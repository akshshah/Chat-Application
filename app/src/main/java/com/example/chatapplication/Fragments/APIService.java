package com.example.chatapplication.Fragments;

import com.example.chatapplication.Notifications.MyResponse;
import com.example.chatapplication.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA3GDCU8g:APA91bFAJ0-gLgAyUufcNcxkTLWzzegk767V4Uh80JDPNtfrFjkaRpN6XKc5H0GgMMfQtdXZmmMOrJyrFCuxMCk77V6aiUiKlOFZQBbXYzkqHtFdqyAjHmUniVXA5vHvP-UCgPlbsaiW"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
