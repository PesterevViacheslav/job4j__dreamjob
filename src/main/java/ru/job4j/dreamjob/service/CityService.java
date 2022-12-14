package ru.job4j.dreamjob.service;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.City;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@ThreadSafe
public class CityService {
    private final Map<Integer, City> cities = new ConcurrentHashMap<Integer, City>();

    public CityService() {
        cities.put(0, new City(0, "Москва"));
        cities.put(1, new City(1, "СПб"));
        cities.put(2, new City(2, "Екб"));
    }

    public List<City> getAllCities() {
        return new ArrayList<>(cities.values());
    }

    public City findById(int id) {
        return cities.get(id);
    }
}