package nainam.restaurant;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import io.realm.RealmObject;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Anik on 3/30/2016.
 */
public class ApiUtils {

    //public static String BASE_URL = "http://192.168.0.108/";
   // public static String BASE_URL = "http://192.168.1.22:8080/";
    public static String BASE_URL = "http://192.168.10.103:8080/";
    public static String API = "api/";


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL + API)
            .addConverterFactory(GsonConverterFactory.create(getGson()));

    public static String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    private static OkHttpClient getOkHttp() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("User-Agent", "Droid-app")
                        .build();
                return chain.proceed(newRequest);
            }
        };

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    private static Gson getGson() {
        return new GsonBuilder()
                .setDateFormat(ISO_FORMAT)
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();
    }


    public static <S> S createService(Class<S> serviceClass) {

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }










}
