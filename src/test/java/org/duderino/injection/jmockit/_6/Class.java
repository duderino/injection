package org.duderino.injection.jmockit._6;

/**
 * A class with a private and inaccessible class dependency
 */
public class Class {
    private class Dependency {
        public int generate() {
            return (int) Math.random() * 1000;
        }
    }

    private Dependency dependency = new Dependency();

    public int generate() {
        return dependency.generate() * 2;
    }
}
