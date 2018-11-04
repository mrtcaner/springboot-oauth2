package com.my.edu.oauth2.client.oauth2client;

import com.my.edu.oauth2.client.oauth2client.data.model.Event;
import com.my.edu.oauth2.client.oauth2client.data.model.Group;
import com.my.edu.oauth2.client.oauth2client.data.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * Created by lenovo510 on 29.10.2018.
 */
@Component
public class Initializer implements CommandLineRunner{

    @Autowired
    private GroupRepository repository;

    @Override
    public void run(String... strings) throws Exception {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG").forEach(name ->
                repository.save(new Group(name))
        );

        Group djug = repository.findByName("Denver JUG");
        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);
    }
}
