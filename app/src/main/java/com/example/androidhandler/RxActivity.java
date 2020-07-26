package com.example.androidhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {
    private Button showNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        showNumber = findViewById(R.id.show_number);
        showNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNumberInButton();
            }
        });
    }

    private void showNumberInButton() {
        Observable numbers = getNumbersToBeShown();
        numbers
                .map(new Function<Integer, String>() {
            @Override
            public String apply(Integer number) throws Exception {
                return "The current number is ".concat(number.toString());
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createButtonObserver());
    }


    private Observable getNumbersToBeShown() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                int count = 0;
                while (count < 10) {
                    SystemClock.sleep(1000);
                    emitter.onNext(++count);
                }
                emitter.onComplete();
            }
        });
    }

    private Observer createButtonObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                showNumber.setClickable(false);
            }

            @Override
            public void onNext(String value) {
                showNumber.setText(value);
            }

            @Override
            public void onError(Throwable e) {
                showNumber.setClickable(true);
            }

            @Override
            public void onComplete() {
                showNumber.setClickable(true);
            }
        };
    }
}
