package org.apps.quantitymeasurement;

import java.util.function.DoubleBinaryOperator;

public class QuantityUC14<U extends IMeasurableUC14> {

    private double value;
    private U unit;

    public QuantityUC14(double value, U unit) {

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

        if (!(obj instanceof QuantityUC14<?> q))
            return false;

        if (unit.getClass() != q.unit.getClass())
            return false;

        double b1 =
                unit.convertToBaseUnit(value);

        double b2 =
                ((IMeasurableUC14) q.unit)
                        .convertToBaseUnit(q.value);

        return Double.compare(b1, b2) == 0;
    }

    public double convertTo(U targetUnit) {

        if (unit instanceof TemperatureUnitUC14 t1 &&
                targetUnit instanceof TemperatureUnitUC14 t2) {

            return t1.convertTo(value, t2);
        }

        double base =
                unit.convertToBaseUnit(value);

        return targetUnit.convertFromBaseUnit(base);
    }

    private enum ArithmeticOperation {

        ADD((a,b)->a+b),
        SUBTRACT((a,b)->a-b),
        DIVIDE((a,b)->{
            if(b==0) throw new ArithmeticException();
            return a/b;
        });

        DoubleBinaryOperator op;

        ArithmeticOperation(DoubleBinaryOperator op){
            this.op=op;
        }

        double compute(double a,double b){
            return op.applyAsDouble(a,b);
        }
    }

    private double performArithmetic(
            QuantityUC14<U> other,
            ArithmeticOperation op) {

        unit.validateOperationSupport(op.name());
        other.unit.validateOperationSupport(op.name());

        double b1 =
                unit.convertToBaseUnit(value);

        double b2 =
                other.unit.convertToBaseUnit(
                        other.value);

        return op.compute(b1,b2);
    }

    public QuantityUC14<U> add(
            QuantityUC14<U> other) {

        double r =
                performArithmetic(
                        other,
                        ArithmeticOperation.ADD);

        return new QuantityUC14<>(
                unit.convertFromBaseUnit(r),
                unit);
    }

    public QuantityUC14<U> subtract(
            QuantityUC14<U> other) {

        double r =
                performArithmetic(
                        other,
                        ArithmeticOperation.SUBTRACT);

        return new QuantityUC14<>(
                unit.convertFromBaseUnit(r),
                unit);
    }

    public double divide(
            QuantityUC14<U> other) {

        return performArithmetic(
                other,
                ArithmeticOperation.DIVIDE);
    }

}