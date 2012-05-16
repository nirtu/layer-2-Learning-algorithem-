/**
 * this class represent a vertex with 2 fields. where dist represent a distance between to nods 
 * and pred stands for the node that point to this.node
 * 
 * @author Nir Turjeman  ID 039622543 &  Yaniv Gal ID 066165184
 *
 */

public class Vert {

	public int dist;
	public int pred;
	
	/**
	 * this is the empty constructor for the class Vert.
	 */
	public Vert ()
	{
		this.dist = 0;
		this.pred = 0;
	}
	
	/**
	 * this is a custom constructor for the class Vert that initiate the fields of the object  
	 * 
	 * @param dist 
	 * @param pred
	 */
	
	public Vert(int dist, int pred)
	{
		this.dist = dist;
		this.pred = pred;
	}	
}
