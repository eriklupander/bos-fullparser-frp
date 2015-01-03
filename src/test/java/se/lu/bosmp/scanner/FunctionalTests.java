package se.lu.bosmp.scanner;

import org.testng.annotations.Test;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.observables.GroupedObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-16
 * Time: 23:34
 * To change this template use File | Settings | File Templates.
 */
@Test
public class FunctionalTests {

    private Observer<List<Dto>> listObserver = new Observer<List<Dto>>() {

        private List<Dto> flatMapped = new ArrayList<>();

        @Override
        public void onCompleted() {
            System.out.println(flatMapped.toString());
        }

        @Override
        public void onError(Throwable throwable) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onNext(List<Dto> o) {
            flatMapped.addAll(o);
        }
    };

    @Test
    public void testGroupByRxJava() {
        List<Dto> list = seedList();
        long start = System.currentTimeMillis();
       // list.stream().forEach(dto -> System.out.println(dto));

        start = System.currentTimeMillis();
        Observable<GroupedObservable<Integer,Dto>> groupedObservableObservable = Observable.from(list).groupBy(dto -> dto.category);



        Subscription subscribe = groupedObservableObservable.subscribe(g -> g.toList().subscribe(listObserver));

        System.out.println("Observable took " + (System.currentTimeMillis() - start) + " ms");
    }


    @Test
    public void testAddStuff() {
        Observable<Dto> observable = Observable.create(new Observable.OnSubscribe<Dto>() {
            @Override
            public void call(Subscriber<? super Dto> subscriber) {
                System.out.println("Someone subscribed");
            }
        });
        observable.subscribe(d -> System.out.println("HEJ"));
        for(Dto dto : seedList()) {
            observable.startWith(dto);
        }

    }

    private List<Dto> seedList() {
        List<Dto> list = new ArrayList<>();
        for(int a = 0; a < 1000; a++) {
            Dto dto = new Dto();
            dto.id = a;
            dto.category = (int) Math.floor(a / 100);
            list.add(dto);
        }
        return list;
    }

    private class Dto {
        public Integer id;
        public Integer category;

        @Override
        public String toString() {
            return "Dto{" +
                    "id=" + id +
                    ", category=" + category +
                    '}';
        }
    }
}
