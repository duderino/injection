package org.duderino.injection.jmockit._8;

/**
 * A class with a private dependency
 */
public class Class {
    private Dependency dependency = new Dependency();

    public int generate() {
        return dependency.generate() * 2;
    }
}
