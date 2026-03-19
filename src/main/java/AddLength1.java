public class AddLength1 {
    static void main(String[] args) {
        QuantityLength3 q1 = new QuantityLength3(1.0, LengthUnit4.FEET);
        QuantityLength3 q2 = new QuantityLength3(12.0, LengthUnit4.INCHES);

        //Uc6
        System.out.println(q1.add(q2));

       //UC7
        System.out.println(q1.add(q2, LengthUnit4.INCHES));
        System.out.println(q1.add(q2, LengthUnit4.YARDS));

        QuantityLength3 q3 = new QuantityLength3(12.0, LengthUnit4.INCHES);
        QuantityLength3 q4 = new QuantityLength3(1.0, LengthUnit4.FEET);

        System.out.println(q3.add(q4, LengthUnit4.FEET));
    }
    }

enum LengthUnit4 {
    FEET(1.0),
    INCHES(1.0 / 12),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    double factor;

    LengthUnit4(double factor) {
        this.factor = factor;
    }
}

class QuantityLength3{

    double value;
    LengthUnit4 unit;

    QuantityLength3(double value, LengthUnit4 unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException();
        }
        this.value = value;
        this.unit = unit;
    }


    public QuantityLength3 add(QuantityLength3 other) {
        return add(other, this.unit);
    }

    // 🔥 UC7 (NEW METHOD)
    public QuantityLength3 add(QuantityLength3 other, LengthUnit4 targetUnit) {

        if (other == null || other.unit == null || targetUnit == null) {
            throw new IllegalArgumentException();
        }


        double thisInFeet = this.value * this.unit.factor;
        double otherInFeet = other.value * other.unit.factor;

        double sumInFeet = thisInFeet + otherInFeet;


        double result = sumInFeet / targetUnit.factor;

        return new QuantityLength3(result, targetUnit);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
