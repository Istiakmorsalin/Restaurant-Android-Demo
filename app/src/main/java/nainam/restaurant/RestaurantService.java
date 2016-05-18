package nainam.restaurant;

/**
 * Created by istiak on 5/12/2016.
 */
import nainam.restaurant.Model.RestaurantResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface RestaurantService {

    @GET("restaurants")
    Call <RestaurantResponse> getRestaurants();




}
