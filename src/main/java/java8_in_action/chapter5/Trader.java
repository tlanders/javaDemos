package java8_in_action.chapter5;

/**
 * Created by tlanders on 4/6/2016.
 */
public class Trader {
    protected final String name;
    protected final  String city;

    public Trader(String n, String c) {
        name = n;
        city = c;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
