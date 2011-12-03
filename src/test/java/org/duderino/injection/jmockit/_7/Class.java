package org.duderino.injection.jmockit._7;

public class Class {
    private SubDependency dependency = new SubDependency();

    public int generate() {
        return 2 * dependency.generate();
    }

    public int superGenerate() {
        return 2 * dependency.superGenerate();
    }

    public int subGenerate() {
        return 2 * dependency.subGenerate();
    }
}
