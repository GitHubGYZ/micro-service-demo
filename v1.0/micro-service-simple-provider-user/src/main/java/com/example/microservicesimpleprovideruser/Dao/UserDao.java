package com.example.microservicesimpleprovideruser.Dao;


import com.example.microservicesimpleprovideruser.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    User getById(Integer id);
}
