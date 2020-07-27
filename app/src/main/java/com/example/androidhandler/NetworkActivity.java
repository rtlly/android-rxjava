package com.example.androidhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        Observable.defer(new Callable<ObservableSource<Response>>() {
            @Override
            public ObservableSource<Response> call() {
                Response response = null;
                try {
                    response = client.newCall(new Request.Builder()
                            .url("https://twc-android-bootcamp.github.io/fake-data/data/default.json")
                            .build()).execute();
                    return Observable.just(response);
                } catch (IOException e) {
                    return Observable.error(e);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseObserver());
    }

    private Observer<Response> responseObserver() {
        return new Observer<Response>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response response) {
                try {
                    String jsonData = response.body().string();
                    JSONObject data = new JSONObject(jsonData);
                    JSONArray dataArray = data.getJSONArray("data");
                    Toast toast = Toast.makeText(getApplicationContext(), dataArray.get(0).toString(), Toast.LENGTH_LONG);
                    toast.show();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
