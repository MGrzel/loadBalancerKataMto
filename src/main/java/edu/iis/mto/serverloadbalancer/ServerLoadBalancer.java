package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for (Vm vm : vms) {
			addToLessLoadedServer(servers, vm);
		}

	}

	private void addToLessLoadedServer(Server[] servers, Vm vm) {
		Server lessLoadedServer = findLessLoadedSever(servers);

		lessLoadedServer.addVm(vm);
	}

	private Server findLessLoadedSever(Server[] servers) {
		Server lessLoadedServer = null;
		for(Server server : servers) {
			if(lessLoadedServer == null || lessLoadedServer.currentLoadPecentage > server.currentLoadPecentage) {
				lessLoadedServer = server;
			}
		}
		return lessLoadedServer;
	}

}
