package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingAServer_noVms_serverStaysEmpty() {
		Server theServer = a(server().withCapacity(1));

		balance(aListOfServersWith(theServer), anEmptyListOfVms());

		assertThat(theServer, hasLoadPercentageOf(0.0d));
	}

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillServerWithVm() {
		Server theServer = a(server().withCapacity(1));
		Vm vm = a(VmBuilder.vm().ofSize(1));
		balance(aListOfServersWith(theServer), aListOfVmsWith(vm));

		assertThat(theServer, hasLoadPercentageOf(100.0d));
		assertThat("server should contain the vm", theServer.contains(vm));
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Vm[] aListOfVmsWith(Vm... vms) {
		return vms;
	}

	private Server[] aListOfServersWith(Server server) {
		return new Server[] { server };
	}

	private <T> T a(Builder<T> builder) {
		return builder.build();
	}
}
