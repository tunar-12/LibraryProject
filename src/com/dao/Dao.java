package com.dao;

import com.model.Books;
import com.model.Users;

import java.util.List;

public interface Dao {

    public boolean createAccount(Users users);

    public boolean checkUserbyUsername(String username);

    public Users checkUser(String username, String password);

    public List<Books> getAllBooks();

    public boolean addBook(Books newBook);

    public boolean updateBook(Books book);

    public List<Books> searchBook(String keywords);

    public List<Books> filterBookByAmount(double min, double max);

    public List<Books> filterBookByPageCount(double min, double max);

    public boolean updateBookByStatus(int selectedId);

    public boolean deleteBook(int selectedId);

    public List<Books> filterBookByStatus(String status);
}
