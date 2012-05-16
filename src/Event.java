public class Event
{
    private double time = 0;
    private int type = 0;
    private int entity = 0;
    private Packet packet = null;
    
    public Event(double t, int ty, int ent) {
        time = t;
        type = ty;
        entity = ent;
        packet = null;
    }//Event
    
    public Event(double t, int ty, int ent, Packet p) {
        time = t;
        type = ty;
        entity = ent;
        packet = new Packet(p);
    }//Event
            
    public boolean setTime(double t) {
        time = t;
        return true;
    }//setTime
    
    public boolean setType(int n) {
        if ((n != NetworkSimulator.FROMLAYER2)) {
            type = -1;
            return false;
        }//if
        type = n;
        return true;
    }//setType
    
    public boolean setEntity(int n) {
        if ((n < 0) || (n >= NetworkSimulator.NUMENTITIES)) {
            entity = -1;
            return false;
        }//if
        entity = n;
        return true;
    }//setEntity
    
    public boolean setPacket(Packet p) {
        if (p == null)
            packet = null;
        else
            packet = new Packet(p);
        return true;
    }//setPacket
    
    public double getTime() { return time; }
    
    public int getType() { return type; }
    
    public int getEntity() { return entity; }
    
    public Packet getPacket() { return packet; }
    
    public String toString() {
        return("time: " + time + "  type: " + type + "  entity: " + entity +
               "packet: " + packet);
    }//toString
}//Event