package org.duderino.injection.jmockit.basic;

import mockit.Mock;
import mockit.Mockit;
import org.testng.annotations.Test;

public class Y2KCheckerTest {
    private static class MockSystem {
        private static final long Y2K_MILLIS = 949433850262L;

        @Mock
        public long currentTimeMillis() {
            return Y2K_MILLIS;
        }
    }

    @Test(expectedExceptions = Y2KChecker.Y2KException.class)
    public void testCheck() throws Exception {
        Y2KChecker checker = new Y2KChecker();

        Mockit.setUpMock(System.class, MockSystem.class);

        checker.check();
    }
}
