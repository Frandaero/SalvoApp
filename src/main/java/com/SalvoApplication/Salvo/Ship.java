package com.SalvoApplication.Salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship
{
    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name="location")
    private List<String> locations = new ArrayList<>();

    //METHODS

    //CONSTRUCTORS
    public Ship(){}

    public Ship(String type, GamePlayer gamePlayer, List<String> locations) {
        this.type = type;
        this.gamePlayer = gamePlayer;
        this.locations = locations;
    }

    //GETTERS
    public long getId()
    {
        return id;
    }

    public String getType()
    {
        return type;
    }

    @JsonIgnore
    public GamePlayer getGamePlayer()
    {
        return gamePlayer;
    }

    public List<String> getLocations()
    {
        return locations;
    }

    //SETTERS
    public void setId(long id)
    {
        this.id = id;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
