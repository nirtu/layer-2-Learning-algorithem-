public class Project {
	// Infinity weight
	public static final int INF = 999;
	
    public final static void main(String[] argv) {
    	int[][] cost = {
    		{0,1,3,7},
			{1,0,1,INF},
			{3,1,0,2},
			{7,INF,2,0},
    			
    	};//cost
    	
    	int[][] costST = computeST(cost);
    	if(costST != null)
    	{
    		NetworkSimulator simulator = new NetworkSimulator(costST);
    		simulator.runSimulator();
    		simulator.printTable();
    	}
    }//main
        
    /**
     * This method should calculate the spanning tree using the Bellman-Ford
     * algorithm as it was presented in class.
     * @param cost
     * 		A graph representation using its costs.
     * @return
     * 		A spanning tree.
     */
    private static int[][] computeST(int[][] cost) {
    	int[][] spannedTree;
    	BellmanFord spanTree = new BellmanFord(cost,1);
    	spannedTree = spanTree.startSpan();
    	return spannedTree;
	}//computeST
}//Project