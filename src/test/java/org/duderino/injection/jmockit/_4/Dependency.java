package org.duderino.injection.jmockit._4;

/**
 * An example dependency
 */
public class Dependency {
    private final int max;

    public Dependency(int max) {
        this.max = max;
    }

    public int generate() {
        return (int) Math.random() * max;
    }
}
