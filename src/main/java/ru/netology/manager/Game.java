package ru.netology.manager;

import ru.netology.domain.Player;
import ru.netology.exceptions.NotRegisteredException;

import java.util.ArrayList;
import java.util.Collection;

public class Game {
    private Collection<Player> players = new ArrayList<>();

    public Player findByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public Collection<Player> getAll() {
        return players;
    }


    public void register(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = this.findByName(playerName1);
        Player player2 = this.findByName(playerName2);
        if (player1 == null || player2 == null) {
            throw new NotRegisteredException("Player not found");
        }

        int result = player1.compareTo(player2);
        if (result == 1) {
            return 1;
        }
        if (result == -1) {
            return 2;
        }
        return 0;
    }
}
