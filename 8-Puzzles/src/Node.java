import java.util.*;

public class Node {
    // State represented as sequence of digits
    int state;
    int depth;
    Node parent;
    double totalcost;
    int cost=0;
    Node(){
        state=0;
        cost=0;
        depth=0;
    }


    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setCost(int cost) {
        this.cost=cost;
    }
    public int getCost() {
        return cost;
    }

    public void setState(int state) {
        this.state=state;
    }
    public int getState() {
        return state;
    }

    public Node getParent(){
        return parent;
    }
    public void setParent(Node parent){
        this.parent=parent;
    }

    public double getTotalcost() {
        return totalcost;
    }
    public void setTotalcost(double total) {
        this.totalcost=total;
    }


/* this func store all childer of the given node
* the boolean will be true if the algom is DFS to reverse the sequence*/
    public List<Node> children(boolean dfs){
        int n = 3;
        int [] curr = new int[3*3];
        int zeroIndex = -1;
        LinkedList<Node>  neighbors = new LinkedList<Node>();
        String st = Integer.toString(state);
        if(st.length() != 9) {
            st = '0' + st;
        }
        for (int i = 0; i < st.length(); i++){
            curr[i] = st.charAt(i) - '0';
            if(curr[i] == 0)
                zeroIndex = i;
        }

        if(zeroIndex>2) {		//Can Go UP
            Node neighbor = new Node();
            neighbor.setState(state);
            neighbor.swapIndexes(zeroIndex, zeroIndex-n);
            neighbors.add(neighbor);
        }

        if(zeroIndex< 6) {		//Can Go DOWN
            Node neighbor = new Node();
            neighbor.setState(state);
            neighbor.swapIndexes(zeroIndex, zeroIndex+n);
            neighbors.add(neighbor);
        }


        if(zeroIndex%n != 2) {		//Can Go RIGHT
            Node neighbor = new Node();
            neighbor.setState(state);
            neighbor.swapIndexes(zeroIndex, zeroIndex+1);
            neighbors.add(neighbor);
        }

        if(zeroIndex%n != 0) {		//Can Go Left
            Node neighbor = new Node();
            neighbor.setState(state);
            neighbor.swapIndexes(zeroIndex, zeroIndex-1);
            neighbors.add(neighbor);
        }

        if(dfs){
            Collections.reverse(neighbors);
        }

        return neighbors;
    }


    @Override
    public int hashCode(){
        return Integer.hashCode(state);
    }
    @Override
    public boolean equals(Object object){
        if(!(object instanceof Node))
            return false;
        return this.state ==  ((Node) object).state;
    }
    @Override
    public String toString() {
        String st = Integer.toString(state);
        if(st.length() != 9) {
            st = '0' + st;
        }
        String s = "";
        for(int i=0;i<3;i++) {
            for(int j=3*i;j<3*i+3;j++) {
                s  += st.charAt(j);
            }
        }
        return s;
    }


    public void swapIndexes (int first , int second){
        int[] stateArray = new int[9] ;
        String stateString = Integer.toString(this.state) ;
        if (stateString.length() != 9){
            stateString = '0' + stateString ;
        }
        for (int i=0 ; i<stateString.length() ; i++) {
            stateArray[i] = stateString.charAt(i) - '0';
        }
        int temp = stateArray[first] ;
        stateArray[first] = stateArray[second] ;
        stateArray[second] = temp ;
        int result = 0 ,count = 0 ;
        for (int i=stateArray.length-1 ; i>=0 ; i--) {
            result += stateArray[i] * Math.pow(10,count);
            count++;
        }
        this.state = result ;
    }



}