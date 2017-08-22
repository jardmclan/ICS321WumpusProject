/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jared
 */
@ManagedBean(name = "CreateAccount")
@SessionScoped
public class CreateAccount implements Serializable {
    
    String username;
    String password;
    String confirmPass;
    String response;
    PreparedStatement ps;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getResponse() throws SQLException {
        
        if(CreateConnection.con == null) {
            CreateConnection.getConnection();
        }
        
        ps = CreateConnection.con.prepareStatement("select * from accounts where username=?");
        ps.setString(1, username);
        if(ps.executeQuery().next()) {
            return "Sorry, username already in use";
        }
        
        if (password.compareTo(confirmPass) == 0) {

            //invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();
            
            ps = CreateConnection.con.prepareStatement("insert into accounts(username, password) values(?, ?)");
        
            ps.setString(1, username);
            ps.setString(2, password);
        
            ps.executeUpdate();
            
            return "Account created";
        } else {

            return "<p>Passwords must match</p>";
        }
    }

    // Creates a new instance of UserNumberBean

    public CreateAccount() {
        username = null;
        password = null;
        confirmPass = null;
    }
    
}
