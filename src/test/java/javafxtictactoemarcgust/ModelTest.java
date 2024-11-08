package javafxtictactoemarcgust;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new Model();
    }

    @Test
    public void testInitialBoardIsEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(' ', model.getBoardSquare(i, j), "Spelbrädet ska vara tomt");
            }
        }
    }

    @Test
    public void testRandomStartingPlayer() {
        char firstPlayer = model.getCurrentPlayer();
        assertTrue(firstPlayer == 'X' || firstPlayer == 'O', "Första spelaren kan vara 'X' eller 'O'");
    }

    @Test
    public void testMakeValidMove() {
        assertTrue(model.makeAMove(0, 0), "Kan bara välja tomma rutor");
        assertEquals('X', model.getBoardSquare(0, 0), "Spelbrädet ska visa 'X' eller 'O' beroende på nuvarande spelare");
    }

    @Test
    public void testWinningCondition() {
        model.makeAMove(0, 0);
        model.makeAMove(1, 0);
        model.makeAMove(0, 1);
        model.makeAMove(1, 1);
        model.makeAMove(0, 2);

        assertTrue(model.isGameOver(), "Spelet är slut när en spelare vinner");
    }

    @Test
    public void testDrawCondition() {
        model.makeAMove(0, 0);
        model.makeAMove(0, 1);
        model.makeAMove(0, 2);
        model.makeAMove(1, 1);
        model.makeAMove(1, 0);
        model.makeAMove(1, 2);
        model.makeAMove(2, 1);
        model.makeAMove(2, 0);
        model.makeAMove(2, 2);

        assertTrue(model.isDraw(), "Spelet blir oavgjort när brädet är fullt och det inte finns en vinnare");
        assertTrue(model.isGameOver(), "Spelet är slut när det blir oavgjort");
    }

    @Test
    public void testPlayerXWinsScore() {
        Model model = new Model();

        if (model.getCurrentPlayer() == 'O') {
            model.makeAMove(0, 0);
        }

        model.makeAMove(1, 0);
        model.makeAMove(0, 1);
        model.makeAMove(1, 1);
        model.makeAMove(2, 2);
        model.makeAMove(1, 2);

        assertTrue(model.isGameOver(), "Spelet är slut när en spelare X vinner");
        assertEquals(1, model.getPlayerXWins(), "Spelare X borde ha 1 vinst");
        assertEquals(0, model.getPlayerOWins(), "Spelare O borde ha 0 vinster");
        assertEquals(0, model.getDraws(), "Oavgjort borde ha 0 vinster");
    }

    @Test
    public void testPlayerOWinsScore() {
        Model model = new Model();

        if (model.getCurrentPlayer() == 'X') {
            model.makeAMove(0, 0);
        }

        model.makeAMove(1, 0);
        model.makeAMove(0, 1);
        model.makeAMove(1, 1);
        model.makeAMove(2, 2);
        model.makeAMove(1, 2);

        assertTrue(model.isGameOver(), "Spelet är slut när en spelare O vinner");
        assertEquals(0, model.getPlayerXWins(), "Spelare X borde ha 0 vinster");
        assertEquals(1, model.getPlayerOWins(), "Spelare O borde ha 1 vinst");
        assertEquals(0, model.getDraws(), "Oavgjort borde ha 0 vinster");
    }

    @Test
    public void testScoreAfterMultipleGames() {
        Model model = new Model();

        if (model.getCurrentPlayer() == 'X') {
            model.makeAMove(0, 0);
            model.makeAMove(1, 0);
            model.makeAMove(0, 1);
            model.makeAMove(1, 1);
            model.makeAMove(0, 2);
        } else {
            model.makeAMove(1, 0);
            model.makeAMove(0, 0);
            model.makeAMove(1, 1);
            model.makeAMove(0, 1);
            model.makeAMove(2, 2);
            model.makeAMove(0, 2);
        }

        assertEquals(1, model.getPlayerXWins(), "Spelare X borde ha 1 vinst efter första matchen");
        assertEquals(0, model.getPlayerOWins(), "Spelare O borde ha 0 vinster efter första matchen");
        assertEquals(0, model.getDraws(), "Oavgjort borde ha 0 vinster efter första matchen");

        model.resetBoard();

        if (model.getCurrentPlayer() == 'X') {
            model.makeAMove(0, 0);
            model.makeAMove(1, 0);
            model.makeAMove(0, 1);
            model.makeAMove(1, 1);
            model.makeAMove(1, 2);
            model.makeAMove(2, 2);
        } else {
            model.makeAMove(1, 0);
            model.makeAMove(0, 0);
            model.makeAMove(1, 1);
            model.makeAMove(0, 1);
            model.makeAMove(2, 2);
        }

        assertEquals(1, model.getPlayerXWins(), "Spelare X borde ha 1 vinst efter andra matchen");
        assertEquals(1, model.getPlayerOWins(), "Spelare O borde ha 1 vinst efter andra matchen");
        assertEquals(0, model.getDraws(), "Oavgjort borde ha 0 vinster efter andra matchen");

        model.resetBoard();

        if (model.getCurrentPlayer() == 'X') {
            model.makeAMove(0, 0);
            model.makeAMove(0, 1);
            model.makeAMove(0, 2);
            model.makeAMove(1, 1);
            model.makeAMove(1, 0);
            model.makeAMove(1, 2);
            model.makeAMove(2, 1);
            model.makeAMove(2, 0);
            model.makeAMove(2, 2);
        } else {
            model.makeAMove(0, 1);
            model.makeAMove(0, 0);
            model.makeAMove(0, 2);
            model.makeAMove(1, 1);
            model.makeAMove(1, 0);
            model.makeAMove(1, 2);
            model.makeAMove(2, 0);
            model.makeAMove(2, 1);
            model.makeAMove(2, 2);
        }

        assertEquals(1, model.getPlayerXWins(), "Spelare X borde ha 1 vinst efter tredje matchen");
        assertEquals(1, model.getPlayerOWins(), "Spelare O borde ha 1 vinst efter tredje matchen");
        assertEquals(1, model.getDraws(), "Det borde vara 1 oavgjord match efter tredje matchen");
    }
}
