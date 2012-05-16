import java.util.Random;

public class NetworkSimulator {    
    // This is the number of entities in the simulator
    public static final int NUMENTITIES = 4;

    // These constants are possible events
    public static final int FROMLAYER2 = 0;
    
    // Parameters of the simulation
    private static EventList eventList = null;
    private static Random rand = null;
    
    // Data used for the simulation
    private Entity[] entity = null;
    public static int[][] cost = null;
    private static double time = 0;

    /**
     * Initializes the simulator.
     * @param cost
     * 			2D array, were {@code myNeighbors[i]} represents the cost (or
     * 			weight) from {@code this} node to node {@code i}.
     */
    public NetworkSimulator(int[][] cost) {
    	System.out.println("Network Simulator v1.2"); //kNN modification
        eventList = new EventListImpl();
        rand = new Random((long)(Math.random() * Long.MAX_VALUE));
        time = 0.0;
        NetworkSimulator.cost = cost;
        entity = new Entity[NUMENTITIES];
        for (int i = 0; i < NUMENTITIES; ++i)
        	entity[i] = new MyEntity(i, cost[i]);
        for (int i = 0; i < NUMENTITIES; ++i)
        	for (int j = 0; j < NUMENTITIES; ++j)
        		entity[i].appSend(j);
        
    //    printTable();
    }//NetworkSimulator
    
    /**
     * Starts the simulation. It will end when no more packets are in the
     * medium.
     */
    public void runSimulator() {
        Event next = null;
        Packet p = null;
        while (true) {
            next = eventList.removeNext();
            if (next == null)
                break;
            time = next.getTime();
            if (next.getType() == FROMLAYER2) {
                p = next.getPacket();
                if ((next.getEntity() < 0) || (next.getEntity() >= NUMENTITIES))
                    System.out.println("main(): Panic. Unknown event entity.");
                else
                    entity[next.getEntity()].layer2Receive(p);
            } else
                System.out.println("main(): Panic.  Unknown event type.");
        }//while
        
      //  printTable();
        System.out.println("Simulator terminated at t=" + time +
                           ", no packets in medium.");        
    }//runSimulator
    
    // Sends a packet into the medium
    /** Warning!  This will allow an entity to send packets that they
          couldn't possibly send (e.g. Entity 1 could send a packet from
          0 to 3).  This should be fixed later... ****/
    /**
     * Sends a packet into the medium.
     * Warning!  This will allow an entity to send packets that they couldn't
     * possibly send (e.g. Entity 1 could send a packet from 0 to 3).
     * This should be fixed later.
     * @param p
     * 			The packet to send.
     */
    public static void toLayer2(Packet p) {
        Packet currentPacket = null;
        double arrivalTime = 0;
        if ((p.getSource() < 0) || (p.getSource() >= NUMENTITIES)) {
            System.out.println("toLayer2(): WARNING: Illegal source id in " +
                               "packet; ignoring.");
            return;
        }//if
        if ((p.getDest() < 0) || (p.getDest() >= NUMENTITIES)) {
            System.out.println("toLayer2(): WARNING: Illegal destination id " +
                               "in packet; ignoring.");
            return;
        }//if
        if (p.getSource() == p.getDest()) {
            System.out.println("toLayer2(): WARNING: Identical source and " +
                               "destination in packet; ignoring.");
            return;
        }//if
        arrivalTime = eventList.getLastPacketTime(p.getSource(), p.getDest());
        if (arrivalTime == 0.0)
            arrivalTime = time;
        arrivalTime = arrivalTime + 1.0 + (rand.nextDouble() * 9.0);
        
        currentPacket = new Packet(p);
        eventList.add(new Event(arrivalTime, FROMLAYER2, 
                currentPacket.getVia(), currentPacket));
     
    }//toLayer2
    /**
     * Prints the MAC tables of each entity.
     */
    public void printTable() {
    	for (Entity e : entity)
    		System.out.println(e);
    }//printRT
}//NetworkSimulator