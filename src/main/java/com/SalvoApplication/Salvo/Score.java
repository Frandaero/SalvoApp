package com.SalvoApplication.Salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Score
{
    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private double score;

    private Date finishDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    //METHODS

    //CONSTRUCTORS
    public Score(){}

    public Score(double score, Player player, Game game){
        this.score = score;
        this.finishDate = new Date();
        this.player = player;
        this.game = game;
    }

    //GETTERS
    public long getId() {
        return id;
    }

    public double getScore() {
        return score;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    //SETTERS
    public void setId(long id) {
        this.id = id;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
