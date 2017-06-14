package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.VirtualMachineBuilder.VM;

public class ServerBuilder implements Builder<Server> {

    public static ServerBuilder server() {
        return new ServerBuilder();
    }


    private double capacity = 0.0;
    private double initialLoad = 0.0;

    @Override
    public Server build() {
        Server server = new Server(capacity);
        if(initialLoad > 0.0) {
            VirtualMachine vm = Builder.A(VM().withSize(capacity * (initialLoad / 100)));
            server.addMachine(vm);
        }
        return server;
    }

    public ServerBuilder withCapacity(double capacity) {
        this.capacity = capacity;
        return this;
    }

    public ServerBuilder withInitialLoad(double initialLoad) {
        this.initialLoad = initialLoad;
        return this;
    }
}
