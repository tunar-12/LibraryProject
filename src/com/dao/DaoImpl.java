package com.dao;

import com.model.Users;
import com.util.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoImpl {
    // Connection    Baza ile elaqe
    // PreparedStatement    Bazadan gelen sql i icraya hazirlayir
    // ResultSet     bazadan melumat getirir

    public boolean createAccount(Users users) {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;


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
        
        c=DBHelper.getConnection();
        if(c!=null){
            try{
                ps=c.prepareStatement(sql);
                rs=ps.executeQuery();
                if(rs.next()){
                    result=true;
                }
                
                
            }catch (Exception e){
                e.printStackTrace();
            }finally{
                Utility.closeAll(c,ps,rs);
            }
        }else{
            System.out.println("Check userby Not connection");
        }
        return result;
    }
    
    public Users checkUser(String username, String password){
        Users u=new Users();
        boolean result=false;
        Connection c=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select id,name,surname,nick,password,mail,address from library_project.users\n"
                + "where username='"+username+"' and password='"+password+"'";
        
        c=DBHelper.getConnection();
        if(c!=null){
            try{
                ps=c.prepareStatement(sql);
                rs=ps.executeQuery();
                if(rs.next()){
                    u.setId(rs.getInt("id"));
                    u.setName(rs.getString("name"));
                    u.setSurname(rs.getString("surname"));
                    u.setUsername(rs.getString("nick"));
                    u.setPassword(rs.getString("password"));
                    u.setMail(rs.getString("mail"));
                    u.setAddress(rs.getString("address"));
                    
                    
                }else{
                    u=null;
                }
                
                
            }catch (Exception e){
                e.printStackTrace();
            }finally{
                Utility.closeAll(c, ps, rs);
            }
        }
       return u; 
    }

}
