package com.SalvoApplication.Salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoRestController
{
    //ATTRIBUTES
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private SalvoRepository salvoRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    //MAPPINGS
    @RequestMapping("/games")
    public List<Object> getGames()
    {
        return gameRepository.findAll()
                .stream()
                .map(game -> getGameDTO(game))
                .collect(Collectors.toList());
    }

    @RequestMapping("/game_view/{gamePlayerId}")
    public Map<String, Object> getGameView(@PathVariable Long gamePlayerId) {
        GamePlayer gamePlayer = gamePlayerRepository.findOne(gamePlayerId);

        return getGameViewToDTO(gamePlayer.getGame(), gamePlayer);
    }

    @RequestMapping("/ships_view")
    public List<Map<String, Object>> getShips(){
        return shipRepository.findAll()
                .stream()
                .map(ship -> getShipToDTO(ship))
                .collect(Collectors.toList());
    }

    //METHODS
    private Map<String, Object> getGameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getId());
        dto.put("creationDate", game.getCreationDate());
        dto.put("players", getGamePlayersLista(game.getGamePlayers()));
        return dto;
    }

    private Map<String, Object> gameDTO(Game game)
    {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getId());
        dto.put("creationDate", game.getCreationDate());
        dto.put("players", getGamePlayersLista(game.getGamePlayers()));
        dto.put("scores", game.getScores());
        return dto;
    }

    private List<Object> getGamePlayersLista(List<GamePlayer> gamePlayers)
    {
        return gamePlayers
                .stream()
                .map(gamePlayer -> gamePlayersDTO(gamePlayer))
                .collect(Collectors.toList());
    }

    private Map<String,Object> gamePlayersDTO(GamePlayer gamePlayer )
    {
        Map<String,Object> dto = new LinkedHashMap<String,Object>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", gamePlayer.getPlayer());
        return dto;
    }

    private Map<String, Object> getGameViewToDTO(Game game, GamePlayer gamePlayer){

        Map<String, Object> dtoGameView = new LinkedHashMap<String, Object>();

        dtoGameView.put("idGame", game.getId());
        dtoGameView.put("creationDate", game.getCreationDate());
        dtoGameView.put("gamePlayers", getGamePlayersToListDTO(game.getGamePlayers()));
        dtoGameView.put("ships", getLocationShips(gamePlayer.getShips()));
        dtoGameView.put("salvoes", getSalvosFromAllGamePlayers(game));
        return dtoGameView;
    }

    private List<Map<String, Object>> getSalvosFromAllGamePlayers(Game game){
        List<Map<String, Object>> finalList = new ArrayList<>();
        game.getGamePlayers().forEach(gamePlayer -> finalList.addAll(SalvoLocationsList(gamePlayer.getSalvoes())));
        return finalList;
    }

    private List<Map<String, Object>> SalvoLocationsList(List<Salvo> salvoList){

        return salvoList.stream()
                .map(salvo -> SalvoToDTO(salvo))
                .collect(Collectors.toList());
    }

    private Map<String, Object> SalvoToDTO(Salvo salvo) {

        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("turn", salvo.getTurn());
        dto.put("player", salvo.getGamePlayer().getPlayer().getId());
        dto.put("locations", salvo.getLocations());
        return dto;
    }

    private List<Map<String, Object>> getGamePlayersToListDTO(List<GamePlayer> gamePlayers){

        return gamePlayers.stream()
                .map(gamePlayer -> getGamePlayerToDTO(gamePlayer))
                .collect(Collectors.toList());
    }

    private Map<String, Object> getGamePlayerToDTO(GamePlayer gamePlayer){

        Map<String, Object> dtoGamePlayer = new LinkedHashMap<String, Object>();

        dtoGamePlayer.put("idGamePlayer", gamePlayer.getId());
        dtoGamePlayer.put("player", playerToDTO(gamePlayer.getPlayer()));
        dtoGamePlayer.put("joinDate", gamePlayer.getJoinDate());
        return dtoGamePlayer;
    }

    private List<Map<String, Object>> getGamePlayers(List<GamePlayer> gamePlayers){

        return gamePlayers.stream()
                .map(gamePlayer -> gamePlayerToDTO(gamePlayer))
                .collect(Collectors.toList());
    }

    private Map<String, Object> gamePlayerToDTO(GamePlayer gamePlayer){

        Map<String, Object> dtoGamePlayer = new LinkedHashMap<String, Object>();

        dtoGamePlayer.put("idGamePlayer", gamePlayer.getId());
        dtoGamePlayer.put("player", playerToDTO(gamePlayer.getPlayer()));
        dtoGamePlayer.put("joinDate", gamePlayer.getJoinDate());
        return dtoGamePlayer;
    }

    private Map<String, Object> playerToDTO(Player player){

        Map<String, Object> dtoPlayer = new LinkedHashMap<String, Object>();
        dtoPlayer.put("idPlayer", player.getId());
        dtoPlayer.put("email", player.getEmail());
        return dtoPlayer;
    }

    private List<Map<String, Object>> getLocationShips(List<Ship> ships){
        return ships.stream()
                .map(ship -> getShipToDTO(ship))
                .collect(Collectors.toList());
    }

    private Map<String, Object> getShipToDTO(Ship ship){

        Map<String, Object> dtoShip = new LinkedHashMap<String, Object>();
        dtoShip.put("shipType", ship.getType());
        dtoShip.put("locations", ship.getLocations());
        return dtoShip;
    }
}
