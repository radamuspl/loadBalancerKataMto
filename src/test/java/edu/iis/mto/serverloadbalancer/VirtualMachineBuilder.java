package edu.iis.mto.serverloadbalancer;

public class VirtualMachineBuilder implements Builder<VirtualMachine> {

    public static VirtualMachineBuilder VM() {
        return new VirtualMachineBuilder();
    }

    private double size = 0.0;

    public VirtualMachineBuilder withSize(double size) {
        this.size = size;
        return this;
    }

    @Override
    public VirtualMachine build() {
        return new VirtualMachine(size);
    }
}
