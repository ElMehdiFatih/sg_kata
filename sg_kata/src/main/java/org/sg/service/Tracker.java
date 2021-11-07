package org.sg.service;


public interface Tracker<T> {
     T historize(long accountNumber, T trackedObject);
}
