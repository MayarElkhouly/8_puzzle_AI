import java.util.*;

public class BFS extends Solver {
    HashSet<Integer> frontierSet;
    Queue<Node> frontier;
    public BFS(){
        super();
        frontier = new LinkedList<>();
        frontierSet = new HashSet<>();
    }
   /*this func search for the goal by taking the intial state we take from user and
    result object from class Result to store number of node expanded , the path and
    depth .
    then return true if we find goal state
    */
    @Override
    boolean search(Node initialState, Result result) {
        frontier.add(initialState);
        frontierSet.add(initialState.getState());
        while(!frontier.isEmpty()){
            Node state=frontier.poll();
            frontierSet.remove(state.getState());
            explored.add(state.getState());
            if(goalTest(state)){
                getGoalNode().setParent(state.getParent());
                result.setNofNodes(explored.size());
                return true;
            }

            for(Node neighbor : state.children(false)){
                if(!explored.contains(neighbor.getState()) && !frontierSet.contains(neighbor.getState())) {
                    neighbor.setParent(state);
                    frontier.add(neighbor);
                    frontierSet.add(neighbor.getState());
                }
            }
        }
        result.setNofNodes(explored.size());
        return false;
    }


}