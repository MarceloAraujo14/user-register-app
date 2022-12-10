package com.maraujo.userregister.form.chain;

public interface ExecutorChain<T> {

    T execute(T t);

}
