package com.SalvoApplication.Salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Map;

@RepositoryRestResource
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long>{}
