import java.util.*;

public class DFS extends Solver {
    Stack<Node> frontier;
    HashSet<Integer> frontierSet;
    public DFS(){
        super();
        frontier=new Stack<>();
        frontierSet = new HashSet<>();
    }


    /*this func search for the goal by taking the intial state we take from user and
    result abject from class Result to store number of node expanded , the path and
    depth .
    then return true if we find goal state
    */
    @Override
    boolean search(Node initialState, Result result) {
        frontier.push(initialState);
        frontierSet.add(initialState.getState());

        while(!frontier.isEmpty()){
            Node state=frontier.pop();
            frontierSet.remove(state.getState());
            explored.add(state.getState());
            if(goalTest(state)){
                getGoalNode().setParent(state.getParent());
                result.setNofNodes(explored.size());
                return true;
            }
            for(Node neighbor : state.children(true)){
                if(!explored.contains(neighbor.getState()) && !frontierSet.contains(neighbor.getState())) {
                    neighbor.setParent(state);
                    neighbor.setDepth(state.getDepth()+1);
                    frontier.add(neighbor);
                    frontierSet.add(neighbor.getState());
                    if(neighbor.getDepth() > result.getMaxDepth()){
                        result.setMaxDepth(neighbor.getDepth());
                    }
                }
            }
        }
        result.setNofNodes(explored.size());
        return false;
    }

}