package com.codeactuator.samriddhi.dto;

public interface Marshallable<E, D> {
    E marshall();

    void unmarshall(E var1);
}