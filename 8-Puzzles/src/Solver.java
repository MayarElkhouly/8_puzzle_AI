import java.util.*;

public abstract class Solver {
    HashSet<Integer> explored;
    Node goalNode;
    Solver(){
        int goalState= 12345678;
        goalNode=new Node();
        goalNode.setState(goalState);
        explored=new HashSet<>();
    }

    public Result solve(Node initialState){

        Result result = new Result();
        // Calculate the running time
        long startTime = System.nanoTime();
        search(initialState, result);
        long endTime = System.nanoTime();
        long timeElapsed = (endTime - startTime);
        result.setTime(timeElapsed / 1000000);
        // Save the solution path
        result.setPath(getPath());

        return result;
    }

    abstract boolean search(Node initialState,Result result);

    List<Node> getPath(){
        LinkedList<Node> path=new LinkedList<>();
        Node curr=goalNode;
        while(curr!=null){
            path.addFirst(curr);
            curr=curr.getParent();
        }
        return path;
    }

    public boolean goalTest(Node state){
        return state.equals(goalNode);
    }
    Node getGoalNode(){
        return goalNode;
    }
}