package com.dao;

import com.model.Users;
import java.awt.print.Book;
import java.util.List;

public interface Dao {

    public boolean createAccount(Users users);

    public boolean checkUserbyUsername(String username);

    public Users checkUser(String username, String password);
    public List<Book> getAllBooks();
}
