package esprit.tn.services;

import java.util.List;

public interface IService<T> {  // Add the generic type parameter T

    void add(T t);
    void update(T t);
    void delete(T t);
    List<T> getAll();
    T getOne();
}
