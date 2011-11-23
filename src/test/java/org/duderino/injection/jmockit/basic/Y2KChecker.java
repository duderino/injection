package org.duderino.injection.jmockit.basic;

import java.util.Calendar;
import java.util.Date;

public class Y2KChecker {
    public void check() throws Y2KException {
        Calendar calendar = Calendar.getInstance();

        calendar.set(2000, 1, 1);

        Date date = calendar.getTime();

        if (date.getTime() == System.currentTimeMillis()) {
            throw new Y2KException("Y2K Bug!");
        }
    }

    public static class Y2KException extends Exception {
        public Y2KException(String reason) {
            super(reason);
        }
    }
}
