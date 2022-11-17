import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleDriver {
    public static void main(String[] args) throws Exception {
        // game code should be modular
        // bulk of logic elsewhere
        // this should only have I/O logic
        // meaning talk to user, get user input, show changes to user

        // e.g.
        // create an object of type Game
        // Game g = new Game();
        // maybe while(!g.hasWon())
        // ask user for input
        // call g.displayBoard()

        // can add a gui with minimal if any changes to game logic

        Scanner in = new Scanner(System.in);
        int numPlayers;
        ArrayList<String> playerNames = new ArrayList<>();

        //ask for names of players
        try {
            System.out.println("Enter number of players: ");
            numPlayers = in.nextInt();
            if ((numPlayers > 6) || (numPlayers < 2)) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("invalid Input: Must be 2-6 players");
        }
        in.nextLine();
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter next player: ");
            playerNames.add(in.nextLine());
        }
        System.out.println(playerNames);
        Monopoly monopoly = new Monopoly(playerNames);
        System.out.println(monopoly.getWinner().name + " won!");
    }
}
