public class UC8 {

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println("Convert: " + q1.convertTo(LengthUnit.INCHES));

        System.out.println("Add: " + q1.add(q2, LengthUnit.FEET));

        System.out.println("Equal: " + q1.equals(q2));

        QuantityLength q3 = new QuantityLength(36.0, LengthUnit.INCHES);
        QuantityLength q4 = new QuantityLength(1.0, LengthUnit.YARDS);

        System.out.println("36 inches == 1 yard: " + q3.equals(q4));

        QuantityLength q5 = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
        System.out.println("2.54 cm to inches: " + q5.convertTo(LengthUnit.INCHES));
    }

    // ---------------- ENUM ----------------
    enum LengthUnit {

        FEET(1.0),
        INCHES(1.0 / 12),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }
    }

    // ---------------- CLASS ----------------
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (Double.isNaN(value) || Double.isInfinite(value))
                throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {

            double base = unit.convertToBaseUnit(value);
            double converted = targetUnit.convertFromBaseUnit(base);

            return new QuantityLength(converted, targetUnit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);

            double sum = base1 + base2;

            double result = targetUnit.convertFromBaseUnit(sum);

            return new QuantityLength(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (!(obj instanceof QuantityLength)) return false;

            QuantityLength other = (QuantityLength) obj;

            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);

            return Math.abs(base1 - base2) < 0.0001;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }
}