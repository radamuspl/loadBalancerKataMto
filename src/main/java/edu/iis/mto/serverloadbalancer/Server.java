package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private double capacity;
    private double usedSpace = 0;
    private final List<VirtualMachine> virtualMachines = new ArrayList<>();

    public Server(double capacity) {
        this.capacity = capacity;
    }

    public double getLoad() {
        return (usedSpace / capacity) * 100;
    }

    public void addMachine(VirtualMachine vm) {
        virtualMachines.add(vm);
        usedSpace = calculateUsedSpaceWith(vm);
    }

    public boolean canAddVm(VirtualMachine virtualMachine) {
        return (!contains(virtualMachine)) && (calculateUsedSpaceWith(virtualMachine) <= capacity);
    }

    public boolean contains(VirtualMachine vm) {
        return virtualMachines.contains(vm);
    }

    private double calculateUsedSpaceWith(VirtualMachine virtualMachine) {
        return usedSpace + virtualMachine.getSize();
    }
}
