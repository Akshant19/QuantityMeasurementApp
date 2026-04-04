package org.apps.quantitymeasurement;

import java.util.function.DoubleBinaryOperator;

public class QuantityUC13<U extends IMeasurableUC13> {

    private double value;
    private U unit;

    public QuantityUC13(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException();

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof QuantityUC13<?> q))
            return false;

        if (unit.getClass() != q.unit.getClass())
            return false;

        double b1 =
                unit.convertToBaseUnit(value);

        double b2 =
                ((IMeasurableUC13) q.unit)
                        .convertToBaseUnit(q.value);

        return Double.compare(b1, b2) == 0;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }

    public double convertTo(U targetUnit) {

        double base =
                unit.convertToBaseUnit(value);

        return targetUnit.convertFromBaseUnit(base);
    }


    // ================= ENUM =================

    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {

            if (b == 0.0)
                throw new ArithmeticException();

            return a / b;
        });

        private final DoubleBinaryOperator op;

        ArithmeticOperation(
                DoubleBinaryOperator op) {
            this.op = op;
        }

        public double compute(double a, double b) {
            return op.applyAsDouble(a, b);
        }
    }


    // ============= VALIDATE =============

    private void validateArithmeticOperands(
            QuantityUC13<U> other,
            U targetUnit,
            boolean targetRequired) {

        if (other == null)
            throw new IllegalArgumentException();

        if (unit.getClass() !=
                other.unit.getClass())
            throw new IllegalArgumentException();

        if (!Double.isFinite(value) ||
                !Double.isFinite(other.value))
            throw new IllegalArgumentException();

        if (targetRequired &&
                targetUnit == null)
            throw new IllegalArgumentException();
    }


    // ============= HELPER =============

    private double performArithmetic(
            QuantityUC13<U> other,
            U targetUnit,
            ArithmeticOperation op) {

        double b1 =
                unit.convertToBaseUnit(value);

        double b2 =
                other.unit.convertToBaseUnit(
                        other.value);

        return op.compute(b1, b2);
    }


    // ============= ADD =============

    public QuantityUC13<U> add(
            QuantityUC13<U> other) {

        validateArithmeticOperands(
                other, unit, false);

        double result =
                performArithmetic(
                        other,
                        unit,
                        ArithmeticOperation.ADD);

        double r =
                unit.convertFromBaseUnit(result);

        return new QuantityUC13<>(r, unit);
    }


    public QuantityUC13<U> add(
            QuantityUC13<U> other,
            U targetUnit) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true);

        double result =
                performArithmetic(
                        other,
                        targetUnit,
                        ArithmeticOperation.ADD);

        double r =
                targetUnit.convertFromBaseUnit(result);

        return new QuantityUC13<>(r, targetUnit);
    }


    // ============= SUBTRACT =============

    public QuantityUC13<U> subtract(
            QuantityUC13<U> other) {

        validateArithmeticOperands(
                other, unit, false);

        double result =
                performArithmetic(
                        other,
                        unit,
                        ArithmeticOperation.SUBTRACT);

        double r =
                unit.convertFromBaseUnit(result);

        return new QuantityUC13<>(r, unit);
    }


    public QuantityUC13<U> subtract(
            QuantityUC13<U> other,
            U targetUnit) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true);

        double result =
                performArithmetic(
                        other,
                        targetUnit,
                        ArithmeticOperation.SUBTRACT);

        double r =
                targetUnit.convertFromBaseUnit(result);

        return new QuantityUC13<>(r, targetUnit);
    }


    // ============= DIVIDE =============

    public double divide(
            QuantityUC13<U> other) {

        validateArithmeticOperands(
                other,
                null,
                false);

        return performArithmetic(
                other,
                null,
                ArithmeticOperation.DIVIDE);
    }
}