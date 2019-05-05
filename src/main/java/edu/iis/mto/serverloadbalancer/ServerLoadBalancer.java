package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for (Vm vm : vms) {
			List<Server> serversWithEnoughCapacity = new ArrayList<Server>(servers.length);
			for(Server server : servers){
				if(server.canFit(vm))
					serversWithEnoughCapacity.add(server);
			}			
			Server lessLoaded = extractLessLoadedServer(serversWithEnoughCapacity);
			if(lessLoaded != null)
				lessLoaded.addVm(vm);
		}

	}

	private Server extractLessLoadedServer(List<Server> servers) {
		Server lessLoaded = null;
		for(Server server : servers){
			if(lessLoaded == null || lessLoaded.currentLoadPecentage > server.currentLoadPecentage){
				lessLoaded = server;
			}
		}
		return lessLoaded;
	}

}
