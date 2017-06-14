package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {

    public static ServerBuilder server() {
        return new ServerBuilder();
    }


    private Server server = new Server();

    @Override
    public Server build() {
        return server;
    }

    public ServerBuilder withCapacity(double capacity) {
        server.setCapacity(capacity);
        return this;
    }
}
