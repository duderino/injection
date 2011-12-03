package org.duderino.injection.jmockit._5;

/**
 * A class with a private and anonymous dependency
 */
public class Class {
    public int generate() {
       Dependency dependency = new Dependency() {
           @Override
           public int generate() {
               return 999;
           }
       };

        return dependency.generate() * 2;
    }
}
