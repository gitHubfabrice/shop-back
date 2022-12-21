package com.fatechnologies.security.adapter.mapper.factory;

public interface Factory<S, T> {

  T factory(S source);
}
