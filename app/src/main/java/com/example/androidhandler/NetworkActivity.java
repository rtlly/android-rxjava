package com.example.androidhandler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        Button getData = findViewById(R.id.get_data);

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromNetwork();
            }
        });
    }


    private void getDataFromNetwork() {
        final OkHttpClient client = new OkHttpClient();
        Observable.defer(new Callable<ObservableSource<PersonResponse>>() {
            @Override
            public ObservableSource<PersonResponse> call() {
                try {
                    Response response = client.newCall(new Request.Builder()
                            .url("https://twc-android-bootcamp.github.io/fake-data/data/default.json")
                            .build()).execute();
                    Gson gson = new Gson();
                    PersonResponse persons = gson.fromJson(response.body().string(), PersonResponse.class);
                    return Observable.just(persons);
                } catch (IOException e) {
                    return Observable.error(e);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseObserver());
    }

    private Observer<PersonResponse> responseObserver() {
        return new Observer<PersonResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PersonResponse response) {
                if (response.getData().size() >= 1 && response.getData().get(0) != null) {
                    Toast.makeText(getApplicationContext(), response.getData().get(0).getName(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
