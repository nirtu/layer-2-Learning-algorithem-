/**
 * this class implements the Bellman ford algorithm
 * the class get a 2D graph with the costs between every nods in the graph
 * and return a spanned graph of the origin one including only the shortest paths (from the root of the graph).
 *   
 * @author Nir Turjeman  ID 039622543 &  Yaniv Gal ID 066165184
 *
 */
public class BellmanFord {
	public int root;
	public Vert vert[];
	public int W [][];
//*************************************************************************************************
	/**
	 * this is the constructor for the Bellman Ford class.
	 * it initiate the fields of the object 
	 * 
	 * @param cost - the cost graph sends buy the user  
	 * @param root - the wanted root for this specific spanning event.
	 */
	public BellmanFord(int[][] cost, int root)
	{
		this.W = cost;
		this.vert = new Vert[cost[0].length];
		this.root = root;
		
		for(int i=0 ; i < cost[0].length ; i++)
			vert[i] = new Vert();
	}
//*************************************************************************************************
	/**
	 * this is the main function of the class, this function controls the algorithm steps
	 * step 1 - init the Vert array.
	 * step 2 - start the RelaxationStage (where the magic happens)
	 * step 3 - check if there is a negative cycles in the given graph
	 * step 4 - create a new graph (spanned of the origin) 
	 * 
	 * @return new spanned graph or null in case there is a negative cycles  
	 */
	public int[][] startSpan()
	{ 
		boolean hasNegativeCycles = false;
		int spannedTree[][] = new int [vert.length][vert.length];
		init();
		BF_RelaxationStage();
		hasNegativeCycles = checkForNegCycle();
		
		if(hasNegativeCycles)
		{
			//System.out.println("this tree contain neg cycles");
			return null;
		}
		
	//	System.out.println("this tree doesnt contain neg cycles");
		preperSpannedTree(spannedTree);
		return spannedTree;
	}
//*************************************************************************************************
	/**
	 * this fucnctio check for the presents of a negative cycles in the graph
	 * @return true in case there is a negative cycle false otherwise 
	 */
	private boolean checkForNegCycle()
	{
		for(int i = 0 ; i <  W[0].length ; i ++)
			for (int j = 0 ; j <  W[0].length ; j ++)
				if(W[i][j] < Project.INF && vert[j].dist > vert[i].dist + W[i][j])
					return true;
		return false;
	}
//*************************************************************************************************
	/**
	 * initiate the Vert array for the action of the algorithm
	 */
	private void init()
	{
		for (int i = 0; i <  W[0].length; i++)
		{
			if(i != root)
			{
				if( W[root][i] < Project.INF)
				{
					vert[i].dist = W[root][i];
					vert[i].pred = root;
				}
				else
				{
					vert[i].dist = Project.INF;
					vert[i].pred = -1;
				}
			}
		}
		vert[root].dist = 0;
		vert[root].pred = -1;
		
		//System.out.println(toString());
	}
//*************************************************************************************************	
	/**
	 * this function start the relaxation   
	 */
	private void BF_RelaxationStage()
	{
		int i , j , k ;
		for(k=1; k < vert.length ; k++)
			for (i=0; i < vert.length ; i++)
				for (j=0 ; j< vert.length ; j++)
					if(W[i][j] < Project.INF)
						relax(i,j);
	}
//*************************************************************************************************
	/**
	 * this function check if the changes until this point allows to find new short paths in the graph 
	 * 
	 * @param i - represent a node 
	 * @param j - represent a node
	 */
	private void relax(int i , int j)
	{
		if(vert[j].dist > vert[i].dist + W[i][j])
		{
			vert[j].dist = vert[i].dist + W[i][j];
			vert[j].pred = i;			
		}
	}
//*************************************************************************************************
	/**
	 * this function builds a new spanned tree (using a 2D array as a graph).
	 * its use the vert array to accomplish that mission.
	 * 
	 * @param spannedTree 2D array including only the new shortest paths.
	 */
	private void preperSpannedTree(int spannedTree[][])
	{
		for (int i =0 ; i < vert.length ; i++)
			for (int j=0 ; j < vert.length ; j++)
				if (i==j)
					spannedTree[i][j] = 0 ;
				else 
					spannedTree[i][j] = Project.INF;
		
		for(int i=0 ; i < vert.length ; i++)
			if(-1 != vert[i].pred )
			{
				spannedTree[i][vert[i].pred] = W[i][vert[i].pred];
				spannedTree[vert[i].pred][i] = W[i][vert[i].pred];
			}
	}
}