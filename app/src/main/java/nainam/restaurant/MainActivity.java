package nainam.restaurant;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nainam.restaurant.Model.Restaurant;
import nainam.restaurant.Model.RestaurantResponse;
import nainam.restaurant.adapter.CustomListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private List<Restaurant> restaurants = new ArrayList<Restaurant>();
    private CustomListAdapter adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this,R.layout.list_row, restaurants);
        listView.setAdapter(adapter);
        getRestaurants();
    }


    private void getRestaurants(){
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

        RestaurantService restaurantService= ApiUtils.createService(RestaurantService.class);
        Call<RestaurantResponse> call= restaurantService.getRestaurants();
        call.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                loading.dismiss();
                if(response.isSuccessful()){
                    RestaurantResponse restaurantResponse =  response.body();

                    adapter.setGridData(restaurantResponse.getLists());
                    Toast.makeText(MainActivity.this,"Successsss!!!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Not Successsss", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
