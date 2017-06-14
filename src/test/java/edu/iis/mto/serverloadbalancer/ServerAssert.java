package edu.iis.mto.serverloadbalancer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractDoubleAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;

public class ServerAssert extends AbstractAssert<ServerAssert, Server> {

    private static final double LOAD_PERCENTAGE_DELTA = 0.1;

    public static ServerAssert assertThat(Server server) {
        return new ServerAssert(server);
    }

    private ServerAssert(Server server) {
        super(server, ServerAssert.class);
    }

    public ServerAssert hasExactLoadPercentage(double expectedLoad) {
        return hasLoadPercentage(expectedLoad, 0.0);
    }

    public ServerAssert hasLoadPercentage(double expectedLoad) {
        return hasLoadPercentage(expectedLoad, LOAD_PERCENTAGE_DELTA);
    }

    public ServerAssert hasLoadPercentage(double expectedLoad, double delta) {
        load(expectedLoad).isCloseTo(expectedLoad, Offset.offset(delta));
        return this;
    }

    private AbstractDoubleAssert<?> load(double expected) {
        return Assertions.assertThat(actual.getLoad())
                .overridingErrorMessage("Expected load <%f> to be <%f> but was not", expected, actual.getLoad());
    }

    public ServerAssert contains(VirtualMachine vm) {
        Assertions.assertThat(actual.contains(vm))
                .overridingErrorMessage("Expected <%s> to contain <%s> but was not", actual, vm)
                .isTrue();
        return this;
    }
}
