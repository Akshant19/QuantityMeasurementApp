 class UC11 {


    interface IMeasurable {
        double toBase(double value);
        double fromBase(double baseValue);
    }


    static class Quantity<U extends IMeasurable> {

        double value;
        U unit;
        static final double EPSILON = 0.0001;

        Quantity(double value, U unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            this.value = value;
            this.unit = unit;
        }


        Quantity<U> convertTo(U targetUnit) {
            double base = unit.toBase(value);
            double converted = targetUnit.fromBase(base);
            return new Quantity<>(converted, targetUnit);
        }

        Quantity<U> add(Quantity<U> other) {
            return add(other, this.unit);
        }

        Quantity<U> add(Quantity<U> other, U targetUnit) {
            double base1 = this.unit.toBase(this.value);
            double base2 = other.unit.toBase(other.value);

            double sum = base1 + base2;
            double result = targetUnit.fromBase(sum);

            return new Quantity<>(result, targetUnit);
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Quantity)) return false;

            Quantity<?> other = (Quantity<?>) obj;


            if (!this.unit.getClass().equals(other.unit.getClass())) return false;

            double base1 = this.unit.toBase(this.value);
            double base2 = ((IMeasurable) other.unit).toBase(other.value);

            return Math.abs(base1 - base2) < EPSILON;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // 🔹 Volume Unit Enum
    enum VolumeUnit implements IMeasurable {
        LITRE(1.0),
        MILLILITRE(0.001),
        GALLON(3.78541);

        private final double factor;

        VolumeUnit(double factor) {
            this.factor = factor;
        }

        public double toBase(double value) {
            return value * factor; // convert to litres
        }

        public double fromBase(double baseValue) {
            return baseValue / factor;
        }
    }


    static void main(String[] args) {

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);


        System.out.println("1L == 1000mL: " + v1.equals(v2)); // true
        System.out.println("1 Gallon == 3.78541L: " + v3.equals(v1)); // true


        System.out.println("1L to mL: " + v1.convertTo(VolumeUnit.MILLILITRE));
        System.out.println("1 Gallon to L: " + v3.convertTo(VolumeUnit.LITRE));


        System.out.println("1L + 1000mL: " + v1.add(v2)); // 2L
        System.out.println("1L + 1 Gallon in mL: " + v1.add(v3, VolumeUnit.MILLILITRE));


        Quantity<VolumeUnit> v4 = new Quantity<>(500, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v5 = new Quantity<>(0.5, VolumeUnit.LITRE);

        System.out.println("500mL == 0.5L: " + v4.equals(v5)); // true
        System.out.println("Add 500mL + 0.5L: " + v4.add(v5));
    }
}