

interface IMeasurable {
    double getConversionFactor();

    default double convertToBaseUnit(double value) {
        return value * getConversionFactor();
    }

    default double convertFromBaseUnit(double baseValue) {
        return baseValue / getConversionFactor();
    }

    String getUnitName();
}


enum LengthUnitUC10 implements IMeasurable {
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(1.0 / 2.54);

    private final double factor;

    LengthUnitUC10(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public String getUnitName() {
        return this.name();
    }
}


enum WeightUnitUC10 implements IMeasurable {
    KILOGRAM(1000.0),
    GRAM(1.0),
    POUND(453.592);

    private final double factor;

    WeightUnitUC10(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public String getUnitName() {
        return this.name();
    }
}


class QuantityUC10<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public QuantityUC10(double value, U unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value or unit");
        }
        this.value = value;
        this.unit = unit;
    }

    public double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityUC10<U> convertTo(U targetUnit) {
        double base = this.toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new QuantityUC10<>(round(converted), targetUnit);
    }

    public QuantityUC10<U> add(QuantityUC10<U> other) {
        return add(other, this.unit);
    }

    public QuantityUC10<U> add(QuantityUC10<U> other, U targetUnit) {
        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityUC10<>(round(result), targetUnit);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityUC10<?> other)) return false;

        // Prevent cross-category comparison
        if (this.unit.getClass() != other.unit.getClass()) return false;

        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}


public class UC10 {

    // Generic Methods
    public static <U extends IMeasurable> void demonstrateEquality(QuantityUC10<U> q1, QuantityUC10<U> q2) {
        System.out.println("Equality: " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(QuantityUC10<U> q, U target) {
        System.out.println("Convert: " + q.convertTo(target));
    }

    public static <U extends IMeasurable> void demonstrateAddition(QuantityUC10<U> q1, QuantityUC10<U> q2, U target) {
        System.out.println("Add: " + q1.add(q2, target));
    }

     static void main(String[] args) {


        QuantityUC10<LengthUnitUC10> l1 =
                new QuantityUC10<>(1.0, LengthUnitUC10.FEET);

        QuantityUC10<LengthUnitUC10> l2 =
                new QuantityUC10<>(12.0, LengthUnitUC10.INCHES);

        demonstrateEquality(l1, l2);
        demonstrateConversion(l1, LengthUnitUC10.INCHES);
        demonstrateAddition(l1, l2, LengthUnitUC10.FEET);

        QuantityUC10<WeightUnitUC10> w1 =
                new QuantityUC10<>(1.0, WeightUnitUC10.KILOGRAM);

        QuantityUC10<WeightUnitUC10> w2 =
                new QuantityUC10<>(1000.0, WeightUnitUC10.GRAM);

        demonstrateEquality(w1, w2);
        demonstrateConversion(w1, WeightUnitUC10.GRAM);
        demonstrateAddition(w1, w2, WeightUnitUC10.KILOGRAM);

        
        QuantityUC10<LengthUnitUC10> l =
                new QuantityUC10<>(1.0, LengthUnitUC10.FEET);

        QuantityUC10<WeightUnitUC10> w =
                new QuantityUC10<>(1.0, WeightUnitUC10.KILOGRAM);

        System.out.println("Cross Category Equality: " + l.equals(w)); // false
    }
}