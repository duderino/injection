package org.duderino.injection.jmockit._7;

public class SubDependency extends SuperDependency {
    @Override
    public int generate() {
        return 333;
    }

    public int subGenerate() {
        return 333;
    }
}
