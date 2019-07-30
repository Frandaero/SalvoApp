package com.SalvoApplication.Salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication
{
	public static void main(String[] args){SpringApplication.run(SalvoApplication.class, args);}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository, SalvoRepository salvoRepository, ScoreRepository scoreRepository)
	{
		return(args) ->
		{
			Player player1 = new Player("roxanitan@hotmail.com", "12345");
			Player player2 = new Player("mazoku@hotmail.com", "6789");
			Player player3 = new Player("qendril@hotmail.com", "25888");

			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);

			Date creationDate1 = new Date();
			Game game1 = new Game();
			game1.setCreationDate(creationDate1);

			creationDate1 = Date.from(creationDate1.toInstant().plusSeconds(3600));
			Game game2 = new Game();
			game2.setCreationDate(creationDate1);

			Game game3 = new Game();
			creationDate1 = Date.from(creationDate1.toInstant().plusSeconds(3600));
			game3.setCreationDate(creationDate1);

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);

			GamePlayer gPlayer1 = new GamePlayer(game1, player1);
			GamePlayer gPlayer2 = new GamePlayer(game1, player3);
			GamePlayer gPlayer3 = new GamePlayer(game3, player2);

			gamePlayerRepository.save(gPlayer1);
			gamePlayerRepository.save(gPlayer2);
			gamePlayerRepository.save(gPlayer3);

			List<String> ubicacion1 = new ArrayList<>();
			List<Ship> ships1 = new ArrayList<>();
			ubicacion1.add("F4");
			ubicacion1.add("F5");

			Ship ship1 = new Ship("Cruiser", gPlayer1, ubicacion1);
			gPlayer1.setShips(ships1);
			shipRepository.save(ship1);
			gamePlayerRepository.save(gPlayer1);

			List<String> ubicacion2 = new ArrayList<>();
			List<Ship> ships2 = new ArrayList<>();
			ubicacion2.add("H3");
			ubicacion2.add("H4");

			Ship ship2 = new Ship("Destroyer", gPlayer2, ubicacion2);
			gPlayer2.setShips(ships2);
			shipRepository.save(ship2);
			gamePlayerRepository.save(gPlayer2);

			List<String> ubicacionsalvo1 = new ArrayList<>();
			List<Salvo> salvoes1 = new ArrayList<>();
			ubicacionsalvo1.add("F4");
			ubicacionsalvo1.add("A2");

			Salvo salvo1 = new Salvo(1, gPlayer1, ubicacionsalvo1);
			gPlayer1.setSalvoes(salvoes1);
			salvoRepository.save(salvo1);
			gamePlayerRepository.save(gPlayer1);

			List<String> ubicacionsalvo2 = new ArrayList<>();
			List<Salvo> salvoes2 = new ArrayList<>();
			ubicacionsalvo2.add("H4");
			ubicacionsalvo2.add("B3");

			Salvo salvo2 = new Salvo(2, gPlayer2, ubicacionsalvo2);
			gPlayer2.setSalvoes(salvoes2);
			salvoRepository.save(salvo2);
			gamePlayerRepository.save(gPlayer2);

			List<String> ubicacionsalvo3 = new ArrayList<>();
			List<Salvo> salvoes3 = new ArrayList<>();
			ubicacionsalvo3.add("C5");
			ubicacionsalvo3.add("C6");

			Salvo salvo3 = new Salvo(3, gPlayer1, ubicacionsalvo3);
			gPlayer1.setSalvoes(salvoes3);
			salvoRepository.save(salvo3);
			gamePlayerRepository.save(gPlayer1);

			Score score1 = new Score(1.0, player1, game1);
			Score score2 = new Score(2.5, player3, game1);
			Score score3 = new Score(1.5, player2, game3);
			scoreRepository.save(score1);
			scoreRepository.save(score2);
			scoreRepository.save(score3);
			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			gameRepository.save(game1);
			gameRepository.save(game3);
		};
	}
}
