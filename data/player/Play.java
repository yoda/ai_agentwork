package player;
import mixmeta4.*;
import java.awt.*;
import java.util.*;
import samplePlayers.*;

/**
* This is the main class for initiating games between two players. You can specify the players, 
 * board configuration, type of game, starting clock time, time per move, and output type.
 * (For multiple players, use PlayCF.)
 */
public class Play {
  
  public static void main(String[] args) {

    Player red = new Arnie(true);
//    Player red = new Hal(true);
    Player black = new Marvin(false);

    String setup =""+
      "..wnqknw..\n"+
      "._epepep_.\n"+
      "__________\n"+
      "__..__..__\n"+
      "__________\n"+
      "._epepep_.\n"+
      "..wnqknw..\n";


    //    Game game = new Game("Finish First",setup,red,black);
    //    Game game = new Game("Finish First",setup,red,black,60,10);
    //    Game game = new Game("TakeAll",setup,red,black);
	Game game = new Game("CheX",setup,red,black, 60, 6, "gui");

	//	Game game = new Game("CheX",setup,red,black, 100, 12, "terminal");

	// the following runs game automatically without using gui
//	while (!game.gameIsOver()) game.step();

 }
}
