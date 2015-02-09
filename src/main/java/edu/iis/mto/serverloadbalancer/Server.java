package edu.iis.mto.serverloadbalancer;

public class Server {

	private static final double MAXIMUM_LOAD = 100.0d;
	public double currentLoadPecentage;
	private int capacity;

	public Server(int capacity) {
		this.capacity = capacity;
	}

	public boolean contains(Vm vm) {
		return true;
	}

	public void addVm(Vm vm) {
		this.currentLoadPecentage = (double) vm.size / (double) this.capacity
				* MAXIMUM_LOAD;
	}

}
