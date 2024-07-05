package web.service;

import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(User user);
        List<User> listUsers();
    //    Car getCarBySeriesAndModel (int series, String model);
    //    User getUserByCarParams (int insertseries, String insertmodel);

    Optional<User> read(Long id);

    void update(Long id, User user);

    void delete(User user);
}
