package com.maraujo.userregister.service.chain;

public interface ExecutorChain<T> {

    T execute(T t);

}
