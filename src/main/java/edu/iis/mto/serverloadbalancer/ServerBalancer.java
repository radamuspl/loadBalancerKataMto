package edu.iis.mto.serverloadbalancer;

import java.util.Collection;

public class ServerBalancer {
    public void balance(Collection<Server> servers, Collection<VirtualMachine> virtualMachines) {
        for(VirtualMachine vm : virtualMachines) {
            for(Server server : servers) {
                if(server.getCapacity() <= vm.getSize()) {
                    server.addMachine(vm);
                    break;
                }
            }
        }
    }
}
