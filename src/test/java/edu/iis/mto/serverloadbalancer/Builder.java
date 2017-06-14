package edu.iis.mto.serverloadbalancer;

public interface Builder<T> {

    static <T> T A(Builder<T> builder) {
        return builder.build();
    }

    T build();
}
