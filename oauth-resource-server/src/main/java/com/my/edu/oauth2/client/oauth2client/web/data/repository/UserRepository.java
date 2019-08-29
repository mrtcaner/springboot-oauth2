package com.my.edu.oauth2.client.oauth2client.web.data.repository;


import com.my.edu.oauth2.client.oauth2client.web.data.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}