package com.my.edu.oauth2.client.oauth2client.data.repository;

import com.my.edu.oauth2.client.oauth2client.data.model.Event;
import com.my.edu.oauth2.client.oauth2client.data.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lenovo510 on 29.10.2018.
 */
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByName(String name);

    List<Group> findAllByUserId(String id);

}
