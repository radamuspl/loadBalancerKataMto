package edu.iis.mto.serverloadbalancer;

import java.util.Collection;
import java.util.Comparator;

public class ServerBalancer {

    private static final Comparator<Server> LOAD_COMPARATOR = (s1, s2) -> {
        double s1Load = s1.getLoad();
        double s2Load = s2.getLoad();

        double diff = s1Load - s2Load;
        if(Math.abs(diff) - 0.1 < 0) {
            return 0;
        } else if(s1Load < s2Load) {
            return -1;
        } else {
            return 2;
        }
    };

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
                .sorted(LOAD_COMPARATOR)
                .findFirst()
                .orElseThrow(NoServerWithEnoughSpace::new);
    }
}
