
public class MyEntity extends Entity {    	
    /**
     * The 'MyEntity' class represents a node and holds functions for the
     * application layer and the link layer performed at {@code this} nodes.
     * The constructor of MyEntity should initialize the MAC table for this
     * node, to hold the port value for the neighbors (according to the new ST
     * topology). In addition each neighbor's port should be initialized with
     * the value 'UNLEARNED'.  
     * @param myId
     * 			Represents the node ID number.
     * @param myNeighbors
     * 			1D array, were {@code myNeighbors[i]} represents the cost (or
     * 			weight) from {@code this} node to node {@code i}.
     */
	
    public MyEntity(int myId, int[] myNeighbors) {
    	this.myId = myId;
    	this.myNeighbors = new int[NetworkSimulator.NUMENTITIES ];
    	for(int i=0; i < NetworkSimulator.NUMENTITIES ; i++)
    	{
    		//updating MAC table
    		if((i == this.myId) || (myNeighbors[i] == Project.INF))
    			this.macTable[i] = NOT_EXIST;
    		else 
    			this.macTable[i] =  UNLEARNED;

    		this.myNeighbors[i] = myNeighbors[i]; //copy my neighbors array - avoiding alias 
    	}
    	
    }//MyEntity
    
    /**
     * This method should implement the application layer at {@code this}
     * entity, namely build the packet this node wants to send and call L2’s
     * this.layer2Receive method to forward it. Note, we assume here the end
     * system host runs over the switch. Invoke NetworkSimulator.toLayer2()
     * in order to transmit the packet.
     * @param d
     * 			Represents the ID of the destination node.
     */
    public void appSend(int d) {
    	if(this.myId != d)
    	{
    		Packet newPacket = new Packet(this.myId, d, -3, -3);
    		layer2Receive(newPacket);
    	}
    	
    }//appSend
    
    /**
     * This method should implement the application layer at {@code this}
     * entity
     */
    public void appReceive(Packet p) {	
    	System.out.println("application over entity : " + this.myId + " received a packet. the packet detailes are:" + p.toString());
    }//appReceive
    
    /**
     * This method may be called in two cases: 1) by the simulator when the
     * frame is captured by the switch/entity. 2) When the application layer of
     * this entity generates a frame and wants to send it. It should implement
     * a call to the learn algorithm by calling the learn() method.
     * @param p
     * 			Represent the received packet.
     */
    public void layer2Receive(Packet p) {  
    	learn(p);

    	if((p.getDest() == this.myId) && (p.getDest() != p.getSource()))
    	{
    		appReceive(p);
    		return;
    	} 	

		if( (macTable[p.getDest()] == NOT_EXIST) || (macTable[p.getDest()] == UNLEARNED) )
		{
	    	flood(p);
	    	return;
		}
		
		if((p.getFrom() != macTable[p.getDest()]) && (p.getSource() != macTable[p.getDest()]) )
		{
			p.setFrom(this.myId);
			p.setVia(macTable[p.getDest()]);
			NetworkSimulator.toLayer2(p);
		}
		
	}//layer2Receive
    
    /**
     * This function should implement the self-learning algorithm.
     * @param p
     * 			Represent the received packet.
     */
    protected void learn(Packet p) {    	
    	if(p.getSource() == this.myId )
    		return;
    	
    	macTable[p.getSource()] = p.getFrom();	
    }//learn
    
    private void flood(Packet p)
    {
    	for(int i=0; i < NetworkSimulator.NUMENTITIES ; i++)
    	{
    		if( i == this.myId || myNeighbors[i] == Project.INF || i == p.getFrom() ||  i == p.getSource() )
    			continue;

    		Packet newPacket = new Packet(p.getSource(),p.getDest(),i,this.myId);
			NetworkSimulator.toLayer2(newPacket);
    	}
    }
    
}//MyEntity
