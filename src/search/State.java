//
//  State.java
//  javaAgents
//
//  Created by Cara MacNish on 28/07/05.
//  Copyright 2005 CSSE, UWA. All rights reserved.
//

package search;
import agent.*;

public interface State {
    
  public void update (Action action) throws RuntimeException;
  
  public Actions getActions ();
  
  public Object clone ();
  
}
