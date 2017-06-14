package edu.iis.mto.serverloadbalancer;

public class Server {
    private int capacity;

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getLoad() {
        return 0;
    }
}
