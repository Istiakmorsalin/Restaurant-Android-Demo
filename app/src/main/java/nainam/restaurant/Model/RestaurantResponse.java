package nainam.restaurant.Model;

import java.util.List;

/**
 * Created by istiak on 5/12/2016.
 */
public class RestaurantResponse {

    private int total;

    private List<Restaurant> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Restaurant> getLists() {
        return list;
    }

    public void setLists(List<Restaurant> lists) {
        this.list = lists;
    }
}
