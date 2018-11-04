package com.my.edu.oauth2.client.oauth2client.data.repository;

import com.my.edu.oauth2.client.oauth2client.data.model.Event;
import com.my.edu.oauth2.client.oauth2client.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}