package com.xtuonline.xtu_backend.repository;

import com.xtuonline.xtu_backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; // 导入 Optional

@Repository // 标记这是一个 Spring Data 仓库接口
public interface UserRepository extends JpaRepository<User, Long> { // 继承 JpaRepository，获得基本的 CRUD 方法

    /**
     * 根据用户名查找用户
     * Spring Data JPA 会自动根据方法名生成查询语句 (SELECT u FROM User u WHERE u.username = ?)
     * @param username 用户名
     * @return 返回一个 Optional<User>，可能包含用户，也可能为空
     */
    Optional<User> findByUsername(String username);

    /**
     * 检查具有给定用户名的用户是否存在
     * Spring Data JPA 会自动根据方法名生成查询语句 (SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.username = ?)
     * @param username 用户名
     * @return 如果用户存在则返回 true，否则返回 false
     */
    Boolean existsByUsername(String username);

} 