package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		if (vms.length > 0) {
			servers[0].currentLoadPecentage = (double) vms[0].size
					/ (double) servers[0].capacity * 100.0d;
		}
	}

}
