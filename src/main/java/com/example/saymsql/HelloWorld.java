package com.example.saymsql;

/**
 * Created by simon on 05/02/19.
 */
import org.joda.time.LocalTime;
public class HelloWorld {
    public static void main(String[] args) {
        LocalTime currentTime = new LocalTime();
        System.out.println("The current local time is: " + currentTime);

        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());
    }
}
