package com.codeactuator.samriddhi.dto;

public interface Marshallable<E, D> {
    E marshall();

    void unmarshal(E var1);
}