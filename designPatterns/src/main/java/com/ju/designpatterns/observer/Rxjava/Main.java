package com.ju.designpatterns.observer.Rxjava;

public class Main {
    public static void main(String[] args) {

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscribe<? super Integer> subscribe) {

                for (int i = 0; i < 5; i++) {
                    System.out.println("OnSubscribe@ "+Thread.currentThread().getName());
                    subscribe.onNext(i);
                }
            }
        })
//                .map(new Observable.Transformer<Integer, String>() {
//                    @Override
//                    public String call(Integer from) {
//                        return "maping " + from;
//                    }
//                })
                //.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscribe<Integer>() {

                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("onNext:  " + value);
                        System.out.println("Subscriber@ "+Thread.currentThread().getName());
                    }

                });
    }
}
