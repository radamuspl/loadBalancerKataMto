package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private double capacity;
    private double usedSpace = 0;
    private final List<VirtualMachine> virtualMachines = new ArrayList<>();

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getLoad() {
        return (usedSpace / capacity) * 100;
    }

    public void addMachine(VirtualMachine vm) {
        virtualMachines.add(vm);
        usedSpace += vm.getSize();
    }
}
