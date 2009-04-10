package player;
import mixmeta4.*;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;

/** 
 * Play using config file.
 * Originially by Greg Wogan, August 2002.
 * Minor additions by Cara MacNish.
 */

public class PlayCF {
  private final static String CONFIG = "config.txt";
  
  public static void main(String[] args) throws Exception {
    String cfg = CONFIG;
    if( args.length>0 )
      cfg = args[0];

    // TODO, read from StdIn when args[0] == "-"
    BufferedReader in = new BufferedReader( new FileReader(cfg) );

	ArrayList players = new ArrayList();
    Player red  = null, blue = null;
    String game = null, setup = null;
    long x = -1, y = -1;
    String visible = "gui";
    Game newGame;

    String s;
    while( (s=in.readLine()) != null ) {
      StringTokenizer token = new StringTokenizer( s, ":" );
      String name = token.nextToken();
	  if (name.trim().toUpperCase().equals("PLAYER")) {
		players.add(token.nextToken().trim());
	  }
	  else if( name.trim().toUpperCase().equals("GAME") ) {
		StringTokenizer tokenator = new StringTokenizer( token.nextToken(), "," );
		game = tokenator.nextToken().trim();
		if( tokenator.hasMoreTokens() ) {
		  x = Long.parseLong( tokenator.nextToken().trim() );
		  y = Long.parseLong( tokenator.nextToken().trim() );
		}
		if (tokenator.hasMoreTokens()) visible=tokenator.nextToken().trim();
		// determine what game we are playing
      } 
	  else if( name.trim().toUpperCase().equals("RED") ) {
		// Load the red player
		Class redClass = Class.forName( token.nextToken().trim() );
		Constructor cs = redClass.getConstructor( new Class[] {Boolean.TYPE} );
		red = (Player) cs.newInstance( new Object[] {new Boolean(true)} );
      } 
	  else if( name.trim().toUpperCase().equals("BLUE") ) {
		// Load the blue player
		Class blueClass = Class.forName( token.nextToken().trim() );
		Constructor cs = blueClass.getConstructor( new Class[] {Boolean.TYPE} );
		blue = (Player) cs.newInstance( new Object[] {new Boolean(false)} );
      } 
	  else if( name.trim().toUpperCase().equals("SETUP") ) {
		// Load the board setup
		setup = "";
		while( (s=in.readLine()) != null ) {
		  String str = (new StringTokenizer(s,":")).nextToken().trim();
		  if( str.toUpperCase().equals("SETUP") )
			break;
		  setup += str + "\n";
		}
      }
    }
    if( game == null ) {
      System.err.println( "Could not find valid game type" );
      return;
    }
    if( red == null ) {
      System.err.println( "Could not load Red player" );
      return;
    }
    if( blue == null ) {
      System.err.println( "Could not load Blue player" );
      return;
    }
    if( setup == null ) {
      System.err.println( "Could not find Setup information" );
      return;
    }
    System.out.println( game );
    System.out.println( red.getClass().getName() +" vs "+ blue.getClass().getName() );

    if( x>0 && y>0 ) {
      System.out.println( x +", "+ y );
      newGame = new Game( game, setup, red, blue, x, y, visible, players);
    } else
      newGame = new Game( game, setup, red, blue );
    if (!visible.equals("gui")) {
      newGame.play(); 
      if (newGame.redWon()) System.out.println("Red won.");
      else if (newGame.blackWon()) System.out.println("Black won");
    }
  }
}
