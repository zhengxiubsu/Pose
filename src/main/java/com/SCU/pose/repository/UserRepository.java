package com.SCU.pose.repository;

import com.SCU.pose.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // 通过用户名查找用户
    Optional<User> findByUsername(String username);

    // 通过邮箱查找用户
    Optional<User> findByEmail(String email);

    // 可以根据需要添加更多查询方法
}
