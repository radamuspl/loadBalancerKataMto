package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {

	private int capacity;
	private double initialLoad;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		Server server = new Server(capacity);
		addInitialLoad(server);
		return server;
	}

	private void addInitialLoad(Server server) {
		if (initialLoad > 0) {
			int expectedLoad = (int) (initialLoad / Server.MAXIMUM_LOAD * (double) server
					.getCapacity());
			server.addVm(VmBuilder.vm().ofSize(expectedLoad).build());
		}
	}

	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public ServerBuilder withCurrentLoadOf(double initialLoad) {
		this.initialLoad = initialLoad;
		return this;
	}
}
