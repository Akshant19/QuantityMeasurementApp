interface IMeasurable12 {
    double toBase(double value);
    double fromBase(double baseValue);
}

enum LengthUnit12 implements IMeasurable12 {
    FEET {
        public double toBase(double value) { return value * 12; }
        public double fromBase(double baseValue) { return baseValue / 12; }
    },
    INCHES {
        public double toBase(double value) { return value; }
        public double fromBase(double baseValue) { return baseValue; }
    }
}

enum WeightUnit12 implements IMeasurable12 {
    KILOGRAM {
        public double toBase(double value) { return value * 1000; }
        public double fromBase(double baseValue) { return baseValue / 1000; }
    },
    GRAM {
        public double toBase(double value) { return value; }
        public double fromBase(double baseValue) { return baseValue; }
    }
}

enum VolumeUnit12 implements IMeasurable12 {
    LITRE {
        public double toBase(double value) { return value * 1000; }
        public double fromBase(double baseValue) { return baseValue / 1000; }
    },
    MILLILITRE {
        public double toBase(double value) { return value; }
        public double fromBase(double baseValue) { return baseValue; }
    }
}

class Quantity12<U extends IMeasurable12> {
    private final double value;
    private final U unit;

    public Quantity12(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.toBase(value);
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    public Quantity12<U> subtract(Quantity12<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity12<U> subtract(Quantity12<U> other, U targetUnit) {
        if (other == null || targetUnit == null) throw new IllegalArgumentException();

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException();
        }

        double resultBase = this.toBase() - other.toBase();
        double result = targetUnit.fromBase(resultBase);

        return new Quantity12<>(round(result), targetUnit);
    }

    public double divide(Quantity12<U> other) {
        if (other == null) throw new IllegalArgumentException();

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException();
        }

        double divisor = other.toBase();
        if (divisor == 0) throw new ArithmeticException();

        return this.toBase() / divisor;
    }

    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}

public class UC12 {
     static void main(String[] args) {

        Quantity12<LengthUnit12> l1 = new Quantity12<>(10, LengthUnit12.FEET);
        Quantity12<LengthUnit12> l2 = new Quantity12<>(6, LengthUnit12.INCHES);

        System.out.println(l1.subtract(l2));
        System.out.println(l1.subtract(l2, LengthUnit12.INCHES));
        System.out.println(l1.divide(l2));

        Quantity12<WeightUnit12> w1 = new Quantity12<>(10, WeightUnit12.KILOGRAM);
        Quantity12<WeightUnit12> w2 = new Quantity12<>(5000, WeightUnit12.GRAM);

        System.out.println(w1.subtract(w2));
        System.out.println(w1.divide(w2));

        Quantity12<VolumeUnit12> v1 = new Quantity12<>(5, VolumeUnit12.LITRE);
        Quantity12<VolumeUnit12> v2 = new Quantity12<>(500, VolumeUnit12.MILLILITRE);

        System.out.println(v1.subtract(v2));
        System.out.println(v1.divide(v2));

        System.out.println(new Quantity12<>(5, LengthUnit12.FEET)
                .subtract(new Quantity12<>(10, LengthUnit12.FEET)));

        System.out.println(new Quantity12<>(10, LengthUnit12.FEET)
                .subtract(new Quantity12<>(120, LengthUnit12.INCHES)));
    }
}