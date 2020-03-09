package com.ankit.callretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Example> listExample;
    private ApiInterface apiInterface;
    private DataAdapter adapter;
    private static Retrofit retrofit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
//        if (android.os.Build.VERSION.SDK_INT > 9)
//        {
//            StrictMode.ThreadPolicy policy = new
//                    StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
        listExample = new ArrayList<>();
        getJSONResponse();

    }


    private void handleError(Throwable error) {
        Toast.makeText(this, "Error " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    private void handleSuccess() {
        Toast.makeText(this, "Get data success! ", Toast.LENGTH_SHORT).show();
    }
    private void getJSONResponse() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Example>> call = apiInterface.getAllPost();

        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                listExample = response.body();
                adapter = new DataAdapter(MainActivity.this,listExample);
                listView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {

            }
        });

    }
    public interface GetDataService {
        @GET("/todos?fbclid=IwAR3VMGjDqNOIg-qlgdetr3LqfLllY8AzRNhNNGJdzZz1dwPrfKunUkckeqc")
        Call<List<Example>> getAllPhotos();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }
}
