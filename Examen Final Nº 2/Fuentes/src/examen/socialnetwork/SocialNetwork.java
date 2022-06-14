package examen.socialnetwork;

import material.graphs.*;

import java.util.*;

/**
 * Stores a social network and analyses the relevance of
 * its users.
 * @author J. Velez, A. Duarte, J. Sanchez-Oro,
 */
public class SocialNetwork {

    ELGraph<String, Integer> socialGraph;
    Map<String, Vertex<String>> vertexMap;
    Map<Integer, List<Vertex<String>>> friendMap;

    public SocialNetwork() {
      socialGraph = new ELGraph<>();
      vertexMap = new HashMap<>();
      friendMap = new HashMap<>();
    }

    /**
     * Adds a new user to the social network
     * @param user the name of the user to be added
     */
    public void addUser(String user) {
		Vertex<String> vertex = socialGraph.insertVertex(user);

        if (vertex != null){
            vertexMap.put(user, vertex);
            List<Vertex<String>> list = friendMap.computeIfAbsent(0, K -> new ArrayList<>());
            list.add(vertex);
        }
    }

    /**
     * Sets a friendship relation between two user
     * @param user1 name of the first user
     * @param user2 name of the second user
     */
    public void makeFriends(String user1, String user2) {
		if (vertexMap.containsKey(user1) && vertexMap.containsKey(user2)){
            Vertex<String> vertex1 = vertexMap.get(user1);
            Vertex<String> vertex2 = vertexMap.get(user2);
            socialGraph.insertEdge(vertex1, vertex2, 1);
        }
    }

    /**
     * Removes all users in the social network with a number of friends
     * lower than a given number k
     * @param k value for filtering
     * @return users removed from the social network
     */
    public Iterable<String> filter(int k) {
        boolean userDeleted = true;
        Set<String> toReturn = new HashSet<>();
        while (userDeleted){
            userDeleted = false;
            for (Vertex<String> vertex : socialGraph.vertices()){
                if ( socialGraph.incidentEdges(vertex).size() < k && !toReturn.contains(vertex.getElement())){
                    toReturn.add(vertex.getElement());
                    userDeleted = true;
                }
            }
            for (String vertex : toReturn){
                socialGraph.removeVertex(vertexMap.get(vertex));
            }
        }
        System.out.println(toReturn);
        return toReturn;
    }

    private void copyGraph(Graph<String, Integer> graph, Graph<String, Integer> graphCopy){

        for (Vertex<String> vertex :
                graph.vertices()) {
            graphCopy.insertVertex(vertex.getElement());
        }

        for (Edge<Integer> edge : graph.edges()){
            Vertex<String> vertex1 = graph.endVertices(edge).get(0);
            Vertex<String> vertex2 = graph.endVertices(edge).get(1);
            graphCopy.insertEdge(vertex1, vertex2, 0);
        }
    }

    /**
     * Evaluates how many groups there are in the social network
     * @return number of groups in the social network
     */
    public int groups() {
        BreadthSearch<String, Integer> utility = new BreadthSearch<>();
        int groups = 0;

        ELGraph<String, Integer> graph = new ELGraph<>();
        while (!graph.vertices().isEmpty()){
            groups++;

            Vertex<String> actualVertex = socialGraph.vertices().iterator().next();
            for (Vertex<String> vertex : utility.traverse(socialGraph, actualVertex)) {
                graph.removeVertex(vertex);
            }
        }

        return groups;
    }

   

   
   
}

