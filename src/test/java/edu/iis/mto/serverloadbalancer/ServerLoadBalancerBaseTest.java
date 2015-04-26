package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancerBaseTest {
	Vm[] aListOfVmsWith(Vm... vms) {
		return vms;
	}

	Server[] aListOfServersWith(Server... servers) {
		return servers;
	}

	<T> T a(Builder<T> builder) {
		return builder.build();
	}


	protected void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}


	protected Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}
}
