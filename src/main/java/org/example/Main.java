package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//        String url = "https://jsonplaceholder.typicode.com/todos/1";
//
//        Request request= new Request.Builder()
//                .url(url)
//                .build();
//
//        try(Response response = client.newCall(request).execute()){
//            if(!response.isSuccessful()){
//                System.out.println("Something went wrong");
//            }
//            System.out.println(response.body().string());
//        }catch(IOException ex){
//            ex.printStackTrace();
//        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ToDoService service = retrofit.create(ToDoService.class);
        retrofit2.Response<ToDo> response = service.getResponseById(3).execute();

        if (response.isSuccessful() && response.body() != null) {
            System.out.println(response.body());
        } else {
            System.out.println("Request failed");
        }

    }
}

//OkHttp is a third party library, this library helps up to make http call easily
//***For some fake api: fake store api, jsonplaceholder api, etc
//The client object above, is responsible for making the TCP connection and above that TCP connection the request is made

