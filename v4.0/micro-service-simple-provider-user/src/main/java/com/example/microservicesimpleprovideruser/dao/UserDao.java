package com.example.microservicesimpleprovideruser.dao;


import com.example.microservicesimpleprovideruser.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User getById(Integer id);
}
