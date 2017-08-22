/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jared
 */
@ManagedBean(name = "VerifyLogin")
@SessionScoped
public class VerifyLogin implements Serializable {
    
    String username;
    String password;
    boolean response;
    PreparedStatement ps;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public boolean getResponse() throws SQLException {
        
        if(CreateConnection.con == null) {
            CreateConnection.getConnection();
        }
        
        ps = CreateConnection.con.prepareStatement("select * from accounts where username=?");
        ps.setString(1, username);
        ResultSet r = ps.executeQuery();
        
        if(!r.next() || password.compareTo(r.getString("password")) != 0) {
            return false;
        }
        
        else {
            return true;
        }
    }

    // Creates a new instance of UserNumberBean

    public VerifyLogin() {

    }
    
}
