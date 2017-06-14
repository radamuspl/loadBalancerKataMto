package edu.iis.mto.serverloadbalancer;

import java.util.Collection;

public class ServerBalancer {
    public void balance(Collection<Server> servers, Collection<VirtualMachine> virtualMachines) {
        virtualMachines.forEach(virtualMachine -> pickServerAndAddVm(virtualMachine, servers));
    }

    private void pickServerAndAddVm(VirtualMachine vm, Collection<Server> servers) {
        //noinspection ConstantConditions
        pickServerForVm(vm, servers).addMachine(vm);
    }

    private Server pickServerForVm(VirtualMachine vm, Collection<Server> servers) {
        return servers.stream()
                .filter(server -> server.canAddVm(vm))
                .findFirst()
                .orElse(null);
    }
}
