package subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SubscriberImpl implements Subscriber<String> {


    private Subscription subscription;
    private long maxQty = 10L;

    public SubscriberImpl(long maxQty) {
        this.maxQty = maxQty;
    }

    public SubscriberImpl() {
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("Subscription is happened!");
        this.subscription = subscription;
    }

    @Override
    public void onNext(String s) {
        System.out.println("onNext called... data received " + s);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {
        System.out.println("onComplete method is called!");
    }

    public void request(long l){
        maxQty -= l;
        if (maxQty > 0) {
            subscription.request(l);
        } else if (maxQty < 0) {
            System.out.println("Requesting last " + -maxQty + " element(-s) and cancelling subscription.");
            subscription.request(-maxQty);
            subscription.cancel();
        } else {
            System.out.println("The maximum of the subscription has been reached. Cancelling subscription.");
            subscription.cancel();
        }
    }
}
