public class Packet {
    private int source = 0;
    private int dest = 0;
    private int via = 0;
    private int from = 0;
    
    public static final int ENTITY = 0;
    public static final int COST = 1;
    
    /**
     * Returns a copy of {@code p}.
     * @param p
     * 			A packet to be copied.
     */
    public Packet(Packet p) {
        source = p.getSource();
        dest = p.getDest();
        via = p.via;
        from = p.from;
    }//Packet
    
    /**
     * 
     * @param s
     * 			source node (sender).
     * @param d
     * 			destination node (receiver).
     * @param v
     * 			via node (connector).
     * @param f
     * 			from node (forwarder).
     */
    public Packet(int s, int d, int v, int f) {
        source = s;
        dest = d;
        via = v;
        from = f;
    }//Packet
    
    /** Returns the source of {@code this} */
    public int getSource() { return source; }
    
    /** Returns the destination of {@code this} */
    public int getDest() { return dest; }
    
    /** Returns the via of {@code this} */
    public int getVia() { return via; }
    
    /** Sets the via of {@code this} */
    public void setVia(int ent) { via = ent; }
    
    /** Returns the from of {@code this} */
    public int getFrom() { return from; }
    
    /** Sets the source of {@code this} */
    public void setFrom(int ent) { from = ent; }
    
    /**
     * Return a String representation of the packet.
     */
    public String toString() {
        return "source: " + source + "  dest: " + dest + "  via: " + via +
        		"  from: " + from;
    }//toString    
}//Packet