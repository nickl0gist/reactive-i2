package org.example;

import org.reactivestreams.Publisher;
import publisher.PublisherImpl;
import subscriber.SubscriberImpl;

public class App
{
    public static void main( String[] args )
    {
        Publisher<String> publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl(11);

        publisher.subscribe(subscriber);
        subscriber.request(3);
        subscriber.request(3);
        subscriber.request(3);
        subscriber.request(3);

    }
}
