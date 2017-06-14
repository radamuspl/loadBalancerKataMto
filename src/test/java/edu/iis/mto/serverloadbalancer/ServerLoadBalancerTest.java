package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.Builder.A;
import static edu.iis.mto.serverloadbalancer.ServerAssert.assertThat;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VirtualMachineBuilder.VM;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		MatcherAssert.assertThat(true, equalTo(true));
	}

	@Test
	public void testEmptyServerStaysEmpty() {
		Server server = A(server().withCapacity(0));

		//noinspection unchecked
		balance(Collections.singletonList(server), Collections.EMPTY_LIST);

		assertThat(server).hasExactLoadPercentage(0);
	}

	private void balance(Collection<Server> servers, Collection<VirtualMachine> virtualMachines) {
		new ServerBalancer().balance(servers, virtualMachines);
	}

	@Test
	public void testServerWithOneMachineTakingFullCapacity() throws Exception {
		Server server = A(server().withCapacity(1));
		VirtualMachine vm = A(VM().withSize(1));
		balance(Collections.singleton(server), Collections.singletonList(vm));

		assertThat(server).hasExactLoadPercentage(100);
	}
}
