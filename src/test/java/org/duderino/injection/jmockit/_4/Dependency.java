package org.duderino.injection.jmockit._4;

/**
 * An example dependency
 */
public class Dependency {
    private int value;

    public Dependency(int value) {
        this.value = value;
    }

    public int generate() {
        return value;
    }
}
