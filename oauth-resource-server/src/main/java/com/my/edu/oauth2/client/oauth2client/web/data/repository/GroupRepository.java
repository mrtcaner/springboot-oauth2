package com.my.edu.oauth2.client.oauth2client.web.data.repository;


import com.my.edu.oauth2.client.oauth2client.web.data.model.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo510 on 29.10.2018.
 */
@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

    Group findByName(String name);

    List<Group> findAllByUserId(String id);

}
