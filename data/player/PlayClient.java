package player;
import mixmeta4.*;
import java.awt.*;
import java.util.*;
import samplePlayers.*;

public class PlayClient {
  
  public static void main(String[] args) {

    Player red = new ClientPlayer(true,"zhangy02.Pascal","localhost");
    Player black = new Marvin(false);    // insert your player here

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
	Game game = new Game("CheX",setup,red,black, 60, 10, "gui");

	//	Game game = new Game("CheX",setup,red,black, 100, 12, "terminal");

	// the following runs game automatically without using gui
  //	while (!game.gameIsOver()) game.step();

 }
}
