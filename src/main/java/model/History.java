package model;


import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="history")
/**
 * A class for historyTable in Database
 */
public class History {
    /**
     * Enum for gameResult (Who won the round)
     */
    public enum gameResults{
        WON,
        LOST,
        DRAW
    }

    @Id
    @GenericGenerator(name = "gameid", strategy = "increment")
    @GeneratedValue(generator = "gameid")
    /**
     * GameNumber column in historyTable
     */
    private int gameNumber;

    @Column
    /**
     * UserID column in historyTable
     */
    private int userID;

    @Column
    /**
     * GameResult column in historyTable
     */
    private gameResults result;

    @Column
    /**
     * Method column in historyTable
     */
    private String method;

    @Column
    /**
     * Bet column in historyTable
     */
    private int bet;

    @Column
    /**
     * Balance column in historyTable
     */
    private int balance;

    @Column
    /**
     * Date column in historyTable
     */
    private LocalDateTime date;


    //GETTERS

    /**
     * Gets the gameNumber
     * @return - The gameNumber
     */
    public int getGameNumber() {
        return gameNumber;
    }

    /**
     * Gets the result
     * @return - The result
     */
    public gameResults getResult() {
        return result;
    }

    /**
     * Gets the method
     * @return - The method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Gets the bet
     * @return - The bet
     */
    public int getBet() {
        return bet;
    }

    /**
     * Gets the balance
     * @return - The balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Gets the current Date
     * @return - the Date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Gets the userID
     * @return - The userID
     */
    public int getUserID() {
        return userID;
    }

    //SETTERS

    /**
     * Sets the gameNumber
     * @param gameNumber - The gameNumber
     */
    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    /**
     * Sets the result
     * @param result - The result
     */
    public void setResult(gameResults result) {
        this.result = result;
    }

    /**
     * Sets the method
     * @param method - The method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Sets the bet
     * @param bet - The bet
     */
    public void setBet(int bet) {
        this.bet = bet;
    }

    /**
     * Sets the balance
     * @param balance - the balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Sets the date
     * @param date - The date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Sets the userID
     * @param userID - The userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
}
