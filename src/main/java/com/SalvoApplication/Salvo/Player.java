package com.SalvoApplication.Salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player
{
    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String email;

    private String password;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<GamePlayer> gamePlayers = new ArrayList<>();

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Score> scores = new ArrayList<>();

    //METHODS
    public void addGamePlayer(GamePlayer gamePlayer)
    {
        gamePlayer.setPlayer(this);
        gamePlayers.add(gamePlayer);
    }

    public void addScore(Score score)
    {
        score.setPlayer(this);
        scores.add(score);
    }

    //CONSTRUCTORS
    public Player(){}

    public Player(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    //GETTERS
    public long getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
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

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setGamePlayers(List<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
