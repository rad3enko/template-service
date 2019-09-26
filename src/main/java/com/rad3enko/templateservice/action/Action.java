package com.rad3enko.templateservice.action;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
public interface Action<T, A> {
    T execute(A arg);
}
