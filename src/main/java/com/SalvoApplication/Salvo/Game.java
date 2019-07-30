package com.SalvoApplication.Salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Game
{
    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Date creationDate;

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<GamePlayer> gamePlayers = new ArrayList<>();

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Score> scores = new ArrayList<>();

    //METHODS
    public void addGamePlayer(GamePlayer gamePlayer)
    {
        gamePlayer.setGame(this);
        gamePlayers.add(gamePlayer);
    }

    public void addScore(Score score)
    {
        score.setGame(this);
        scores.add(score);
    }

    //CONSTRUCTORS
    public Game()
    {
        this.creationDate = new Date();
    }

    //GETTERS
    public long getId()
    {
        return id;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    @JsonIgnore
    public List<GamePlayer> getGamePlayers()
    {
        return gamePlayers;
    }

    @JsonIgnore
    public List<Score> getScores() {
        return scores;
    }

    //SETTERS
    public void setId(long id)
    {
        this.id = id;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public void setGamePlayers(List<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
