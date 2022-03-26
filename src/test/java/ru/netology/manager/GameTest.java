package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Player;
import ru.netology.exceptions.NotRegisteredException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Player player1 = new Player(1, "Юрий", 18);
    private Player player2 = new Player(2, "Петр", 15);
    private Player player3 = new Player(3, "Андрей", 15);
    private Player player4 = new Player(4, "Nik", 19);

    private Game game = new Game();

    @Test
    public void shouldFindByName() {
        game.register(player1);
        game.register(player2);

        Player expected = player1;
        Player actual = game.findByName("Юрий");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindByName() {
        game.register(player1);
        game.register(player2);

        Player expected = null;
        Player actual = game.findByName("Андрей");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotRegisterSamePlayerTwice() {
        game.register(player1);
        game.register(player1);

        int expected = 1;
        int actual = game.getAll().toArray().length;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldShowWinner() {
        game.register(player1);
        game.register(player2);

        int expected = 2;
        int actual = game.round("Петр", "Юрий");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldShowWinner1() {
        game.register(player1);
        game.register(player2);

        int expected = 1;
        int actual = game.round("Юрий", "Петр");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldShowLooser() {
        game.register(player1);
        game.register(player2);

        int expected = 2;
        int actual = game.round("Петр", "Юрий");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldShowDraw() {
        game.register(player3);
        game.register(player2);

        int expected = 0;
        int actual = game.round("Андрей", "Петр");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotRegisteredExeption() {
        game.register(player1);
        game.register(player2);

        try {
            game.round("Юрий", "Non-existed");
        } catch (RuntimeException ex) {
            Assertions.assertThrows(NotRegisteredException.class, () -> game.round("Юрий", "Nik"));
        }
    }

    @Test
    public void shouldThrowNotRegisteredExeptionForOnePlayer1() {
        game.register(player1);
        game.register(player2);

        try {
            game.round("Non-existed", "Nick");
        } catch (RuntimeException ex) {
            Assertions.assertThrows(NotRegisteredException.class, () -> game.round("Юрий", "Nik"));
        }
    }

    @Test
    public void shouldThrowNotRegisteredExeptionForOnePlayer2() {
        game.register(player1);
        game.register(player2);

        try {
            game.round("Non-existed", "Non-existed");
        } catch (RuntimeException ex) {
            Assertions.assertThrows(NotRegisteredException.class, () -> game.round("Юрий", "Nik"));
        }
    }
}