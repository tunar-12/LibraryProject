package com.dao;

import com.model.Books;
import com.model.Users;
import com.util.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl {
    // Connection    Baza ile elaqe
    // PreparedStatement    Bazadan gelen sql i icraya hazirlayir
    // ResultSet     bazadan melumat getirir

    public boolean createAccount(Users users) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
// Result setden istifade etmirem cunki mene lazim deyil cunki men hesab aciramm burada
        String sql = "insert into library_project.users(name,surname,nick,password,mail,address)\n"
                + "values ('" + users.getName() + "','" + users.getSurname() + "','" + users.getUsername() + "','" + users.getPassword() + "','" + users.getMail() + "','" + users.getAddress() + "')";
        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("DaoImpl exception in creating account");
            } finally {
                Utility.closeAll(c, ps, null);
            }

        } else {
            System.out.println("Not connected");
        }
        return result;
    }

    public boolean checkUserbyUsername(String username) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from library_project.users\n"
                + "where nick='" + username + "'";

        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Utility.closeAll(c, ps, rs);
            }
        } else {
            System.out.println("Check userby Not connection");
        }
        return result;
    }

    public Users checkUser(String username, String password) {
        Users u = new Users();
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,surname,nick,password,mail,address from library_project.users\n"
                + "where username='" + username + "' and password='" + password + "'";
        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    u.setId(rs.getInt("id"));
                    u.setName(rs.getString("name"));
                    u.setSurname(rs.getString("surname"));
                    u.setUsername(rs.getString("nick"));
                    u.setPassword(rs.getString("password"));
                    u.setMail(rs.getString("mail"));
                    u.setAddress(rs.getString("address"));

                } else {
                    u = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Utility.closeAll(c, ps, rs);
            }
        }
        return u;
    }

    public List<Books> getAllBooks() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Books> allBooks = new ArrayList<Books>();
        String sql = "select id,name,theme,author,page_count,amount,language,status from library_project.books";
        c = DBHelper.getConnection();
        if (c != null) {
            try {
                Books book = new Books();
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {

                    book.setId(rs.getInt("id"));
                    book.setName(rs.getString("name"));
                    book.setAmount(rs.getDouble("amount"));
                    book.setAuthor(rs.getString("author"));
                    book.setTheme(rs.getString("theme"));
                    book.setPageCount(rs.getString("page_count"));
                    book.setLanguage(rs.getString("language"));
                    book.setStatus(rs.getString("status"));
                    allBooks.add(book);
                }

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                Utility.closeAll(c, ps, rs);
            }
        } else {
            System.out.println("There isn't connection");

        }
        return allBooks;
    }

    public boolean addBook(Books newBook) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into library_project.books(name,theme,author,page_count,amount,language)\n"
                + "values ('" + newBook.getName() + "','" + newBook.getTheme() + "','" + newBook.getAuthor() + "','" + newBook.getPageCount() + "','" + newBook.getAmount() + "','" + newBook.getLanguage() + "')";

        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                Utility.closeAll(c, ps, null);
            }
        } else {
            System.out.println("There isn't any connection");
        }

        return result;
    }

    public boolean updateBook(Books book) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update library_project.books\n"
                + "set name='" + book.getName() + "',author='" + book.getAuthor() + "',page_count=" + book.getPageCount() + ",theme='" + book.getTheme() + "',amount=" + book.getAmount() + " ,language='" + book.getLanguage() + "'\n"
                + "where id=" + book.getId() + "";

        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Utility.closeAll(c, ps, null);
            }
        } else {
            System.out.println("Three isn't any connection!");
        }
        return result;
    }

    public List<Books> searchBook(String keywords) {
        List<Books> resultBook = new ArrayList<Books>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,theme,author,page_count,amount,language,status from library_project.books\n"
                + "where name like('%" + keywords + "%') or "
                + "theme like('%" + keywords + "%') or "
                + "author like('%" + keywords + "%') or "
                + "page_count like('%" + keywords + "%') or "
                + "amount like('%" + keywords + "%') or "
                + "language like('%" + keywords + "%') or "
                + "status like('%" + keywords + "%')";

        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Books book = new Books();
                    book.setId(rs.getInt("id"));
                    book.setName(rs.getString("name"));
                    book.setTheme(rs.getString("theme"));
                    book.setAuthor(rs.getString("author"));
                    book.setAmount(rs.getDouble("amount"));
                    book.setPageCount(rs.getString("page_count"));
                    book.setLanguage(rs.getString("language"));
                    book.setStatus(rs.getString("status"));
                    resultBook.add(book);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Utility.closeAll(c, ps, rs);
            }
        } else {
            System.out.println("There isn't any connection!");
        }
        return resultBook;
    }

    public List<Books> filterBookByAmount(double min, double max) {
        List<Books> resultBook = new ArrayList<Books>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,theme,author,page_count,amount,language,status from library_project.books\n"
                + "where amount between '" + min + "' and '" + max + "'";

        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Books book = new Books();
                    book.setId(rs.getInt("id"));
                    book.setName(rs.getString("name"));
                    book.setTheme(rs.getString("theme"));
                    book.setAuthor(rs.getString("author"));
                    book.setAmount(rs.getDouble("amount"));
                    book.setPageCount(rs.getString("page_count"));
                    book.setLanguage(rs.getString("language"));
                    book.setStatus(rs.getString("status"));
                    resultBook.add(book);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Utility.closeAll(c, ps, rs);
            }
        } else {
            System.out.println("No connection!");
        }
        return resultBook;
    }

    public List<Books> filterBookByPageCount(double min, double max) {
        List<Books> resultBook = new ArrayList<Books>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,theme,author,page_count,amount,language,status from library_project.books\n"
                + "where page_count between '" + min + "' and '" + max + "'";

        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Books book = new Books();
                    book.setId(rs.getInt("id"));
                    book.setName(rs.getString("name"));
                    book.setTheme(rs.getString("theme"));
                    book.setAuthor(rs.getString("author"));
                    book.setAmount(rs.getDouble("amount"));
                    book.setPageCount(rs.getString("page_count"));
                    book.setLanguage(rs.getString("language"));
                    book.setStatus(rs.getString("status"));
                    resultBook.add(book);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Utility.closeAll(c, ps, rs);
            }
        } else {
            System.out.println("There isn't any conection!");
        }
        return resultBook;
    }

    public boolean updateBookByStatus(int selectedId) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update library_project.books\n"
                + "set status='Sold' \n"
                + "where id=" + selectedId + "";

        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Utility.closeAll(c, ps, null);
            }
        } else {
            System.out.println("There isn't connection!");
        }
        return result;
    }

    public boolean deleteBook(int selectedId) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "delete from library_project.books\n"
                + "where id=" + selectedId + "";
        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Utility.closeAll(c, ps, null);
            }
        } else {
            System.out.println("There isn't connection!");
        }
        return result;
    }

    public List<Books> filterBookByStatus(String status) {
        List<Books> resultBook = new ArrayList<Books>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,theme,author,page_count,amount,language,status from library_project.books\n"
                + "where status='" + status + "'";

        c = DBHelper.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Books book = new Books();
                    book.setId(rs.getInt("id"));
                    book.setName(rs.getString("name"));
                    book.setTheme(rs.getString("theme"));
                    book.setAuthor(rs.getString("author"));
                    book.setAmount(rs.getDouble("amount"));
                    book.setPageCount(rs.getString("page_count"));
                    book.setLanguage(rs.getString("language"));
                    book.setStatus(rs.getString("status"));
                    resultBook.add(book);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Utility.closeAll(c, ps, rs);
            }
        } else {
            System.out.println("There issn't connection!");
        }
        return resultBook;
    }

}
