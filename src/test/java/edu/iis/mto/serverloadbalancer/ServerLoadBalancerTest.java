package edu.iis.mto.serverloadbalancer;


import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static edu.iis.mto.serverloadbalancer.Builder.A;
import static edu.iis.mto.serverloadbalancer.ServerAssert.assertThat;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VirtualMachineBuilder.VM;
import static org.hamcrest.Matchers.equalTo;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		MatcherAssert.assertThat(true, equalTo(true));
	}

	@Test
	public void testEmptyServerStaysEmpty() {
		Server server = A(server().withCapacity(1));

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

	@Test
	public void testOneMachineTakes10PercentageOfSpace() throws Exception {
		Server server = A(server().withCapacity(10));
		VirtualMachine vm = A(VM().withSize(1));

		balance(Collections.singleton(server), Collections.singletonList(vm));

		assertThat(server).hasExactLoadPercentage(10);
	}

	@Test
	public void testServerContainsAllAddedVms() throws Exception {
		Server server = A(server().withCapacity(10));
		VirtualMachine vm1 = A(VM().withSize(1));
		VirtualMachine vm2 = A(VM().withSize(1));

		balance(Collections.singleton(server), Arrays.asList(vm1, vm2));

		assertThat(server).hasExactLoadPercentage(20)
				.contains(vm1)
				.contains(vm2);

	}

	@Test
	public void testBalancesMachinesToLessLoadedServer() throws Exception {
		Server lessLoaded = A(server().withCapacity(10).withInitialLoad(20));
		Server moreLoaded = A(server().withCapacity(15).withInitialLoad(50));
		VirtualMachine vm = A(VM().withSize(1));

		balance(Arrays.asList(moreLoaded, lessLoaded), Collections.singletonList(vm));

		assertThat(lessLoaded).contains(vm).hasLoadPercentage(30);
		assertThat(moreLoaded).hasExactLoadPercentage(50);
	}
}
