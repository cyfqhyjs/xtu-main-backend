package com.xtuonline.xtu_backend.model.entity;

import jakarta.persistence.*; // 导入 Jakarta Persistence API
import lombok.Data;
import lombok.NoArgsConstructor; // 可以添加无参构造函数
import lombok.AllArgsConstructor; // 可以添加全参构造函数

@Entity // 标记这是一个 JPA 实体类，对应数据库中的一张表
@Table(name = "users", // 指定数据库表的名称为 "users"
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username") // 添加唯一约束，确保用户名不重复
       })
@Data // Lombok 注解，自动生成 getter, setter, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id // 标记这是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略：自增长
    private Long id;

    @Column(nullable = false, unique = true) // 映射到数据库列，不允许为空，且唯一
    private String username;

    @Column(nullable = false) // 映射到数据库列，不允许为空
    private String password;

    // 可以根据需要添加其他字段，例如：
    // private String email;
    // private String role; // 用户角色，例如 "USER", "ADMIN"
    // private java.time.LocalDateTime createdAt; // 创建时间
    // private java.time.LocalDateTime updatedAt; // 更新时间

    // @PrePersist // 在持久化（保存）之前自动设置创建时间
    // protected void onCreate() {
    //     createdAt = java.time.LocalDateTime.now();
    //     updatedAt = java.time.LocalDateTime.now();
    // }

    // @PreUpdate // 在更新之前自动设置更新时间
    // protected void onUpdate() {
    //     updatedAt = java.time.LocalDateTime.now();
    // }
}