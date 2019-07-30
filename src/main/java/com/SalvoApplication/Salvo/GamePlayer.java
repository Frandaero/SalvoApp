package com.SalvoApplication.Salvo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
public class GamePlayer
{
    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Date joinDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Ship> ships = new ArrayList<>();

    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Salvo> salvoes = new ArrayList<>();

    //METHODS
    public void addShip(Ship ship)
    {
        this.ships.add(ship);
        ship.setGamePlayer(this);
    }

    public void addSalvo(Salvo salvo)
    {
        this.salvoes.add(salvo);
        salvo.setGamePlayer(this);
    }

    //CONSTRUCTORS
    public GamePlayer(){}

    public GamePlayer(Game game, Player player)
    {
        this.joinDate = new Date();
        this.player = player;
        this.game = game;
        this.ships = getShips();
    }

    //GETTERS
    public long getId()
    {
        return id;
    }

    public Date getJoinDate()
    {
        return joinDate;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Game getGame()
    {
        return game;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Salvo> getSalvoes() {
        return salvoes;
    }

    //SETTERS
    public void setId(long id)
    {
        this.id = id;
    }

    public void setJoinDate(Date joinDate)
    {
        this.joinDate = joinDate;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public void setSalvoes(List<Salvo> salvoes) {
        this.salvoes = salvoes;
    }
}
