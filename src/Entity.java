public abstract class Entity {
	/** Entity ID */
	protected int myId = -1;
	
	/** Entity neighbors */
	protected int[] myNeighbors = null; 
	
    /** Entity self-learning table -- 1D array, were {@code macTable[i]}
     * represents the port which {@code this} node needs to use in order to 
     * reach node {@code i}.
     */
    protected int[] macTable = new int[NetworkSimulator.NUMENTITIES];
    
    protected static final int NOT_EXIST = -1;
    protected static final int UNLEARNED = -2;
    
    /** Send a packet to destination {@code d} in the application level */
    public abstract void appSend(int d);
    
    /** Receive a packet to application*/
    public abstract void appReceive(Packet p); 
    
    /** Called when a packet is received */
    public abstract void layer2Receive(Packet p);
    
    /** Performs learning */
    protected abstract void learn(Packet p);
    
    /**
     * Return a String representation of the MAC table.
     */
    public String toString() {
    	String nl = System.getProperty("line.separator");
    	StringBuilder sb = new StringBuilder();
    	sb.append("   " + myId + "   " + nl);
    	sb.append("-------" + nl);
    	for (int i = 0; i < macTable.length; ++i)
    		if (macTable[i] == NOT_EXIST)
    			continue;
    		else
    			sb.append(i + " --> " + macTable[i] + nl);
    	return sb.toString();
    }//toString
}//Entity