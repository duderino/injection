package org.duderino.injection.jmockit._15;

/**
 * An example dependency
 */
public class Dependency {
    public long generate() {
        return Thread.currentThread().getId() + 999;
    }
}
