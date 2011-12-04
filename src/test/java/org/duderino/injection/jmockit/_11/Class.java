package org.duderino.injection.jmockit._11;

/**
 * A class with a private dependency
 */
public class Class {
    private Dependency dependency = new Dependency();

    public int generate() {
        return dependency.generate() + (2 * dependency.generate());
    }
}
