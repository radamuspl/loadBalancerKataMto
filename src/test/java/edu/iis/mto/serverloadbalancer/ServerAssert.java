package edu.iis.mto.serverloadbalancer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ServerAssert extends AbstractAssert<ServerAssert, Server> {

    public static ServerAssert assertThat(Server server) {
        return new ServerAssert(server);
    }

    private ServerAssert(Server server) {
        super(server, ServerAssert.class);
    }

    public ServerAssert hasExactLoadPercentage(double expectedLoad) {
        Assertions.assertThat(expectedLoad)
                .overridingErrorMessage("Expected load <%f> to be <%f> but was not", expectedLoad, actual.getLoad())
                .isEqualTo(expectedLoad);
        return this;
    }
}
