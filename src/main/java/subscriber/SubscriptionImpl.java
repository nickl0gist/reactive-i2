package subscriber;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SubscriptionImpl implements Subscription {

    private final Subscriber<? super String> subscriber;
    private final Faker faker = Faker.instance();

    private boolean isSubscribed;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.isSubscribed = true;
    }

    @Override
    public void request(long l) {
        if(isSubscribed) {
            System.out.println("Subscriber has requested " + l + " data.");
            int i = 0;
            for (; i < l; i++) {
                subscriber.onNext(faker.name().fullName());
            }
            System.out.println("Publisher has emitted all " + i + " data");
            subscriber.onComplete();
        } else {
            System.out.println("Subscription is not active!");
        }
    }

    @Override
    public void cancel() {
        this.isSubscribed = false;
        System.out.println("Subscriber has canceled the subscription");
    }
}
