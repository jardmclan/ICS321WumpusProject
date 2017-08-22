/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jared
 */
@ManagedBean(name = "AttemptBean")
@SessionScoped
public class AttemptBean implements Serializable{
    
    public String stat = "moves";
    public String username;
    public boolean personal = false;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getPersonal() {
        return personal;
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }
    
    public List<Attempt> getAttemptList() throws SQLException{

        Connection con;

        if(CreateConnection.con == null) {
            CreateConnection.getConnection();
        }
        PreparedStatement ps;
        con = CreateConnection.con;
        
        if(personal && username != null) {
            ps = con.prepareStatement("select * from gamestats where username = '" + username + "' order by " + stat, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
        else {
            ps = con.prepareStatement("select * from gamestats order by " + stat, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
        ResultSet result =  ps.executeQuery();

        List<Attempt> list = new ArrayList<Attempt>();

        int i = 0;
        while(result.next() && i < 10){
                Attempt a = new Attempt();

                a.setUsername(result.getString("username"));
                a.setMoves(result.getInt("moves"));
                a.setTime(result.getString("time"));

                //store all data into a List
                list.add(a);
                i++;
        }

        return list;
    }
}
