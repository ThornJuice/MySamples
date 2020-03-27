package com.ju.designpatterns.observer.rxjava;

//订阅源(被观察者)
public class Observable<T> {
    OnSubscribe onSubscribe;

    private Observable(OnSubscribe onSubscribe) {
        this.onSubscribe = onSubscribe;
    }


    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe) {
        return new Observable<T>(onSubscribe);
    }

    public <R> Observable<R> map(final Transformer<? super T, ? extends R> transformer) {
        return create(new OnSubscribe<R>() {
            @Override
            public void call(final Subscribe<? super R> subscribe) {
                Observable.this.subscribe(new Subscribe<T>() {
                    @Override
                    public void onCompleted() {
                        subscribe.onCompleted();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        subscribe.onError(throwable);
                    }

                    @Override
                    public void onNext(T value) {
                        subscribe.onNext(transformer.call(value));
                    }
                });
            }
        });
    }

    public Observable<T> subscribeOn(final Scheduler scheduler) {
        return create(new OnSubscribe<T>() {
            @Override
            public void call(final Subscribe<? super T> subscribe) {
                scheduler.createWork().schedule(new Runnable() {
                    @Override
                    public void run() {
                        Observable.this.onSubscribe.call(subscribe);
                    }
                });
            }
        });
    }

    public Observable<T> observeOn(final Scheduler scheduler) {
        return create(new OnSubscribe<T>() {
            @Override
            public void call(final Subscribe<? super T> subscribe) {
                final Scheduler.Worker worker = scheduler.createWork();
                Observable.this.subscribe(new Subscribe<T>() {
                    @Override
                    public void onCompleted() {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscribe.onCompleted();
                            }
                        });
                    }

                    @Override
                    public void onError(final Throwable throwable) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscribe.onError(throwable);
                            }
                        });
                    }

                    @Override
                    public void onNext(final T value) {

                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscribe.onNext(value);
                            }
                        });
                    }
                });
            }
        });
    }

    public void subscribe(Subscribe<? super T> subscribe) {
        subscribe.onStart();
        onSubscribe.call(subscribe);
    }

    public interface OnSubscribe<T> {
        void call(Subscribe<? super T> subscribe);
    }

    public interface Transformer<T, R> {
        R call(T from);
    }
}
