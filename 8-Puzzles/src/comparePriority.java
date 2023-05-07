import java.util.Comparator;

public class comparePriority implements Comparator<Node> {

    // This function override the compare function in Comparator library
    // It's task is to store nodes in ascending order according to their
    // total cost so while traversing the fringe we remove the node with
    // the least total cost
    @Override
    public int compare(Node o , Node s) {
        if (o.getTotalcost() < s.getTotalcost()) {
            return -1 ;
        }
        if (o.getTotalcost() > s.getTotalcost()) {
            return 1 ;
        }
        return 0 ;
    }
}