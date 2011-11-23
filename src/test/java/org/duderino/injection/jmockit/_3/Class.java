package org.duderino.injection.jmockit._3;

/**
 * A class with a private static dependency
 */
public class Class {
    public int generate() {
        return Dependency.generate() * 2;
    }
}
