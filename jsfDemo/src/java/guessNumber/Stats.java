/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jared
 */
@ManagedBean(name = "Stats")
@RequestScoped
public class Stats implements Serializable{

    public String time;
    public String username;
    public Integer moves;

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getMoves() {
        return moves;
    }

    public void setMoves(Integer moves) {
        this.moves = moves;
    }
    
    public void addStats() throws SQLException {
        if(moves != null) {
            if(CreateConnection.con == null) {
                CreateConnection.getConnection();
            }
        
            PreparedStatement ps = CreateConnection.con.prepareStatement("insert into gamestats(username, moves, time) values(?, ?, ?)");
            ps.setString(1, username);
            ps.setInt(2, moves);
            ps.setString(3, time);

            ps.executeUpdate();
        }
    }
    
    /**
     * Creates a new instance of Stats
     */
    public Stats() {
        
    }
    
}
