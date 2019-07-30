package com.SalvoApplication.Salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salvo
{
    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private int turn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name="location")
    private List<String> locations = new ArrayList<>();

    //METHODS

    //CONSTRUCTORS
    public Salvo(){}

    public Salvo(int turn, GamePlayer gamePlayer, List<String> locations)
    {
        this.turn = turn;
        this.gamePlayer = gamePlayer;
        this.locations = locations;
    }

    //GETTERS
    public long getId()
    {
        return id;
    }

    public int getTurn()
    {
        return turn;
    }

    @JsonIgnore
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public List<String> getLocations() {
        return locations;
    }

    //SETTERS
    public void setId(long id) {
        this.id = id;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
