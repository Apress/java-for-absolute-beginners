package com.apress.bgn.ch4.ex;

import com.apress.bgn.ch4.hierarchy.Gender;
import com.apress.bgn.ch4.hierarchy.Performer;

public class PerformerGenerator {

    public static Performer get(String name) throws EmptyPerformerException {
        return new Performer(name,40, 1.91f, Gender.MALE);
    }
}
