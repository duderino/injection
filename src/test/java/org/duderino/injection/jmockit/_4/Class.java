package org.duderino.injection.jmockit._4;

/**
 * A class with a private dependency
 */
public class Class {
    private final Dependency dependency;

    public Class(int max) {
        dependency = new Dependency(max);
    }

    public int generate() {
        return dependency.generate() * 2;
    }
}
