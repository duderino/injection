package org.duderino.injection.jmockit._15;

public class Class {
    private Dependency dependency = new Dependency();

    public long generate() {
        return dependency.generate() * 2;
    }
}
