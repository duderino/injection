package org.duderino.injection.jmockit._4;

/**
 * A class with a private dependency
 */
public class Class {
    private Dependency dependency = new Dependency(999);

    public int generate() {
        return dependency.generate() * 2;
    }
}
