package org.duderino.injection.jmockit._12;

/**
 * A class with a dependency that implements an interface
 */
public class Class {
    public class DependencyImpl implements Dependency {
        public int generate() {
               return 999;
           }
    }

    private Dependency dependency = new DependencyImpl();

    public int generate() {
        return dependency.generate() * 2;
    }
}
