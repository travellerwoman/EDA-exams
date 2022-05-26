package examen.socialnetwork;

import material.graphs.ELGraph;
import material.graphs.Edge;
import material.graphs.Graph;
import material.graphs.Vertex;

import java.util.*;

/**
 * Stores a social network and analyses the relevance of
 * its users.
 * @author J. Velez, A. Duarte, J. Sanchez-Oro,
 */
public class SocialNetwork {

    

    public SocialNetwork() {
      
    }

    /**
     * Adds a new user to the social network
     * @param user the name of the user to be added
     */
    public void addUser(String user) {
		throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Sets a friendship relation between two user
     * @param user1 name of the first user
     * @param user2 name of the second user
     */
    public void makeFriends(String user1, String user2) {
		throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Removes all users in the social network with a number of friends
     * lower than a given number k
     * @param k value for filtering
     * @return users removed from the social network
     */
    public Iterable<String> filter(int k) {
		throw new UnsupportedOperationException("Not yet implemented");

    }

    /**
     * Evaluates how many groups there are in the social network
     * @return number of groups in the social network
     */
    public int groups() {
		throw new UnsupportedOperationException("Not yet implemented");

    }

   

   
   
}

