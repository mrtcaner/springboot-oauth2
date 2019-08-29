package com.my.edu.oauth2.client.oauth2client.web.data.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

/**
 * Created by lenovo510 on 29.10.2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;
    private Instant date;
    private String title;
    private String description;
    @ManyToMany
    private Set<User> attendees;
}
