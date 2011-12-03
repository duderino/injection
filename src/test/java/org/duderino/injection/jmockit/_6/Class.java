package org.duderino.injection.jmockit._6;

/**
 * A class with a private and inaccessible class dependency
 */
public class Class {
    private class Dependency {
        public int generate() {
            return 999;
        }
    }

    private Dependency dependency = new Dependency();

    public int generate() {
        return dependency.generate() * 2;
    }
}
