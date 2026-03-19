public class UC9 {
    static void main(String[] args) {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight w3 = new QuantityWeight(2.20462, WeightUnit.POUND);

        System.out.println("=== Equality ===");
        System.out.println(w1.equals(w2)); // true
        System.out.println(w1.equals(w3)); // true


        System.out.println("\n=== Conversion ===");
        System.out.println(w1.convertTo(WeightUnit.GRAM));     // 1000 g
        System.out.println(w2.convertTo(WeightUnit.POUND));    // ~1.10231 lb


        System.out.println("\n=== Addition (Default Unit) ===");
        System.out.println(w1.add(w2)); // 2 kg


        System.out.println("\n=== Addition (Target Unit) ===");
        System.out.println(w1.add(w2, WeightUnit.GRAM)); // 2000 g


        System.out.println("\n=== Mixed Addition ===");
        System.out.println(w1.add(w3, WeightUnit.KILOGRAM));
    }
}

enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }


    public double toBase(double value) {
        return value * factor;
    }

  
    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}

class QuantityWeight {

    private final double value;
    private final WeightUnit unit;
    private static final double EPSILON = 1e-6;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }


    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = unit.toBase(this.value);
        double convertedValue = targetUnit.fromBase(baseValue);

        return new QuantityWeight(convertedValue, targetUnit);
    }


    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }


    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double base1 = this.unit.toBase(this.value);
        double base2 = other.unit.toBase(other.value);

        double sumBase = base1 + base2;

        double result = targetUnit.fromBase(sumBase);

        return new QuantityWeight(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;

        double base1 = this.unit.toBase(this.value);
        double base2 = other.unit.toBase(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        double base = unit.toBase(value);
        return Double.hashCode(base);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
