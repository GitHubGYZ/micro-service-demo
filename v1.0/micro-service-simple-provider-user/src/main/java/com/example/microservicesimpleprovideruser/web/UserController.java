package com.example.microservicesimpleprovideruser.web;


import com.example.microservicesimpleprovideruser.Dao.UserDao;
import com.example.microservicesimpleprovideruser.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/user/{id}")
    public User getUserByid(@PathVariable Integer id) {
        User u = userDao.getById(id);
        userDao.getById(id);
        return u;
    }


}
