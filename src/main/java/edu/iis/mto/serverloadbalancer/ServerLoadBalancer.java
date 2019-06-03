package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) {
        for (Vm vm : vms) {
            List<Server> capableServers = findCapableServers(servers, vm);

            Server lessLoaded = extractLessLoadedServer(capableServers);
            if (lessLoaded != null) {
                lessLoaded.addVm(vm);
            }
        }
    }

    private List<Server> findCapableServers(Server[] servers, Vm vm) {
        List<Server> capableServers = new ArrayList<>();
        for (Server server : servers) {
            if (server.canFit(vm)) {
                capableServers.add(server);
            }
        }
        return capableServers;
    }

    private Server extractLessLoadedServer(List<Server> servers) {
        Server lessLoaded = null;
        for (Server server : servers) {
            if (lessLoaded == null || lessLoaded.currentLoadPecentage > server.currentLoadPecentage) {
                lessLoaded = server;
            }
        }
        return lessLoaded;
    }

}
