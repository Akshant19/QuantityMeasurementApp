package org.apps.quantitymeasurement;

public class QuantityMeasurementAppUC13 {

    public static <U extends IMeasurableUC13>
    boolean demonstrateEquality(
            QuantityUC13<U> q1,
            QuantityUC13<U> q2) {

        return q1.equals(q2);
    }

    public static <U extends IMeasurableUC13>
    QuantityUC13<U> demonstrateAddition(
            QuantityUC13<U> q1,
            QuantityUC13<U> q2) {

        return q1.add(q2);
    }

    public static <U extends IMeasurableUC13>
    QuantityUC13<U> demonstrateSubtraction(
            QuantityUC13<U> q1,
            QuantityUC13<U> q2) {

        return q1.subtract(q2);
    }

    public static <U extends IMeasurableUC13>
    double demonstrateDivision(
            QuantityUC13<U> q1,
            QuantityUC13<U> q2) {

        return q1.divide(q2);
    }
}