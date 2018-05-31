package ro.irian.well.well_delivery.repository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface Repository<T, Q> {
    public Flowable<T> getOne(String ID);
    public Flowable<T> observeOne(String ID);

    public Flowable<List<T>> getList();
    public Flowable<List<T>> observeList();

    public Flowable<List<T>> getList(Q query);
    public Flowable<List<T>> observeList(Q query);
}
