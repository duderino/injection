package org.duderino.injection.jmockit._14;

public class Class {
    private Dependency dependency = new Dependency();
    private int result = 0;

    public void callback(int result) {
        this.result = result;
    }

    public int generate() {
        dependency.generate(this);

        return 2 * result;
    }
}
