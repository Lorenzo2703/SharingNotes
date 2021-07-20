package com.sharingnotes.Dao;

import com.sharingnotes.Model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserData implements UserDao{

    private static List<User> Db = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User user) {
        Db.add(new User(id,user.getName()));
        return 1;
    }
}
