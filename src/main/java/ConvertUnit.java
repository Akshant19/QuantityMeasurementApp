public class ConvertUnit {

    static void convertDemo(double value, LengthUnit2 from, LengthUnit2 to) {
        double result = QuantityLength.convert(value, from, to);
        System.out.println(value + " " + from + " = " + result + " " + to);
    }

    static void convertDemo(QuantityLength q, LengthUnit2 to) {
        double result = q.convertTo(to);
        System.out.println(q + " = " + result + " " + to);
    }

    static void main(String[] args) {

        convertDemo(1, LengthUnit2.FEET, LengthUnit2.INCHES);
        convertDemo(3, LengthUnit2.YARDS, LengthUnit2.FEET);

        QuantityLength q = new QuantityLength(36, LengthUnit2.INCHES);
        convertDemo(q, LengthUnit2.YARDS);

        // equals demo
        QuantityLength a = new QuantityLength(1, LengthUnit2.FEET);
        QuantityLength b = new QuantityLength(12, LengthUnit2.INCHES);

        System.out.println("Equal? " + a.equals(b));

    }
}

enum LengthUnit2{

    FEET(1.0),
    INCHES(1.0/12.0),
    YARDS(3.0),
    CENTIMETER(1.0/30.48);

     double factor;
    LengthUnit2(double factor){
        this.factor = factor;
    }


}

class QuantityLength {
    double value;
    LengthUnit2 unit;

    QuantityLength(double value, LengthUnit2 unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }


    double convertTo(LengthUnit2 target) {
        double base = value * unit.factor;
        return base / target.factor;
    }


    static double convert(double value, LengthUnit2 from, LengthUnit2 to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Invalid unit");
        }
        double base = value * from.factor;
        return base / to.factor;
    }


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        double v1 = value * unit.factor;
        double v2 = other.value * other.unit.factor;

        return Math.abs(v1 - v2) < 0.0001;
    }


    public String toString() {
        return value + " " + unit;
    }
}