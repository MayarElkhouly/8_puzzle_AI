public class Heuristics {


    // this function is responsible for calculating the heuristic
    // it takes the state and a boolean flag which indicates the required
    // heuristic false for euclidean and true for manhattan
    public double heuristicCost (int state , boolean flag){
        int[] stateArray = new int[9] ;
        int cost = 0 ;
        String stateString = Integer.toString(state) ;
        if (stateString.length() != 9){
            stateString = '0' + stateString ;
        }
        // converting the state into an array of size 9 to calculate
        // the heuristic for each element in the state ang add them
        // to get the total heuristic cost
        for (int i=0 ; i<stateString.length() ; i++) {
            stateArray[i] = stateString.charAt(i) - '0' ;
            if(stateArray[i] != i){
                if (flag){
                    cost += manhattanCost(i,stateArray[i]) ;
                }
                else {
                    cost += euclideanCost(i,stateArray[i]) ;
                }
            }
        }
        return cost ;
    }

    // get the x & y coordinates of a given element and return its manhattan cost
    public double manhattanCost (int curPos , int goalPos){
        int x1 = curPos % 3 ;
        int x2 = goalPos % 3 ;
        int y1 = curPos / 3 ;
        int y2 = goalPos / 3 ;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2) ;
    }

    // get the x & y coordinates of a given element and return its euclidean cost
    public double euclideanCost (int curPos , int goalPos){
        int x1 = curPos % 3 ;
        int x2 = goalPos % 3 ;
        int y1 = curPos / 3 ;
        int y2 = goalPos / 3 ;
        return  Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) ;
    }
}