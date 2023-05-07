import java.util.HashMap ;
import java.util.List ;
import java.util.PriorityQueue ;

public class AStarManhattan extends Solver {

    Heuristics getCost = new Heuristics() ;
    PriorityQueue<Node> frontier = new PriorityQueue<>(10,new comparePriority()) ;
    HashMap<Integer ,Double> frontierCosts= new HashMap<>() ;
    int maxDepth = 0 ;

    @Override
    public boolean search (Node initialNode, Result result){

        // add initial state in the frontier
        frontier.add(initialNode)  ;
        frontierCosts.put(initialNode.getState(),0.0) ;
        Node currentNode ;

        // while the fringe isn't empty remove the node with the least cost
        // and add it to the explored set
        while(!frontier.isEmpty()) {
            currentNode = frontier.poll() ;
            frontierCosts.remove(currentNode.getState()) ;

            // if the node is had been already traversed before then
            // continue and remove another node
            if (explored.contains(currentNode.getState())){
                continue ;
            }
            explored.add(currentNode.getState()) ;

            // if there is a node that has a depth greater than the max depth
            // then store it in the max depth variable to get the max depth
            // has been traversed
            if (currentNode.getDepth() > maxDepth){
                maxDepth = currentNode.getDepth() ;
            }

            // if we reach the goal state then store its information in result class
            // and return true
            if (goalTest(currentNode)){
                getGoalNode().setParent(currentNode.getParent()) ;
                result.setNofNodes(explored.size()) ;
                result.setMaxDepth(maxDepth) ;
                return true ;
            }

            // else get the children of the removed node to get all possible moves
            List<Node> children = currentNode.children(false) ;

            // for each child set its cost & depth equal the cost & depth of its
            // parent + 1 then calculate its total cost f(x) = g(x) + h(x)
            // and set current node as its parent
            for (Node child : children) {
                if(!explored.contains(child.getState())) {
                    child.setCost(currentNode.getCost() + 1) ;
                    child.setDepth(currentNode.getDepth() + 1) ;
                    child.setTotalcost(getCost.heuristicCost(child.getState(),true) + child.getCost()) ;
                    child.setParent(currentNode) ;

                    // if we get the same child but from another moves with less cost
                    // than the already stored then replace it
                    if(frontierCosts.containsKey(child.getState())) {
                        if(child.getTotalcost() < frontierCosts.get(child.getState())) {
                            frontierCosts.put(child.getState(), child.getTotalcost()) ;
                            frontier.remove(child);
                        }else {
                            continue ;
                        }
                    }

                    // add the child to the fringe
                    frontier.add(child) ;
                    frontierCosts.put(child.getState(),child.getTotalcost()) ;
                }
            }
        }

        // if the fringe is empty then it's unsolvable case
        // return false and number of expanded nodes
        System.out.println("Unsolvable Case !") ;
        System.out.println("# of nodes expanded = " + (explored.size())) ;
        return false ;
    }
}