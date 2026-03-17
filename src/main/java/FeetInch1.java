import java.util.Scanner;

public class FeetInch1 {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter value1:");
        double v1 = sc.nextDouble();

        System.out.println("Enter unit1 (FEET, INCH, YARD, CM):");
        LengthUnit1 u1 = LengthUnit1.valueOf(sc.next().toUpperCase());

        System.out.println("Enter value2:");
        double v2 = sc.nextDouble();

        System.out.println("Enter unit2 (FEET, INCH, YARD, CM):");
        LengthUnit1 u2 = LengthUnit1.valueOf(sc.next().toUpperCase());

        Measurement m1 = new Measurement(v1, u1);
        Measurement m2 = new Measurement(v2, u2);

        System.out.println("Are Equal? " + m1.equals(m2));

        sc.close();
    }
}

enum LengthUnit1 {
    FEET(12.0),
    INCH(1.0),
    YARD(36.0),
    CM(0.393701);

    private final double toInches;

    LengthUnit1(double toInches) {
        this.toInches = toInches;
    }

    public double convertToInches(double value) {
        return value * toInches;
    }
}

class Measurement {
    private double value;
    private LengthUnit1 unit;

    public Measurement(double value, LengthUnit1 unit) {
        this.value = value;
        this.unit = unit;
    }

    private double toInches() {
        return unit.convertToInches(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Measurement)) return false;

        Measurement other = (Measurement) obj;
        return Math.abs(this.toInches() - other.toInches()) < 0.0001;
    }
}