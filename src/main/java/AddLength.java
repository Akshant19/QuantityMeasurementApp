public class AddLength {
    static void main(String[] args) {

        QuantityLength1 q1 = new QuantityLength1(1.0, LengthUnit3.FEET);
        QuantityLength1 q2 = new QuantityLength1(12.0, LengthUnit3.INCHES);

        System.out.println(q1.add(q2));

        QuantityLength1 q3 = new QuantityLength1(12.0, LengthUnit3.INCHES);
        QuantityLength1 q4 = new QuantityLength1(1.0, LengthUnit3.FEET);

        System.out.println(q3.add(q4));
    }
}

enum LengthUnit3 {
    FEET(1.0),
    INCHES(1.0 / 12),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    double factor;

    LengthUnit3(double factor) {
        this.factor = factor;
    }
}

class QuantityLength1 {

    double value;
    LengthUnit3 unit;

    QuantityLength1(double value, LengthUnit3 unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException();
        }
        this.value = value;
        this.unit = unit;
    }


    public QuantityLength1 add(QuantityLength1 other) {

        if (other == null || other.unit == null) {
            throw new IllegalArgumentException();
        }


        double thisInFeet = this.value * this.unit.factor;
        double otherInFeet = other.value * other.unit.factor;


        double sumInFeet = thisInFeet + otherInFeet;

        
        double result = sumInFeet / this.unit.factor;


        return new QuantityLength1(result, this.unit);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}