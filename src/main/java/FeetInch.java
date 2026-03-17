import java.util.Scanner;

public class FeetInch {
    static void main(String[] args) {

        Scanner sc =new Scanner(System.in);

        System.out.println("enter value1:");
        double value1 = sc.nextDouble();

        System.out.println("enter value2");
        double value2 = sc.nextDouble();

        Measurement m1 = new Measurement(value1,LengthUnit.FEET);
        Measurement m2 = new Measurement(value2,LengthUnit.INCH);

        System.out.println("m1 Equal m2:" + m1.equals(m2));
    }
}

enum LengthUnit{

    FEET(1.0),
    INCH(1.0 / 12.0);

    private final double conversionFactortoFeet;

    LengthUnit(double conversionFactortoFeet){
        this.conversionFactortoFeet = conversionFactortoFeet;
    }

    public double toFeet(double value){
        return value * conversionFactortoFeet;
    }
}

class Measurement{

    private final double value;
    private final LengthUnit unit;

  public  Measurement(double value,LengthUnit unit) {

      if (unit == null) {
          throw new IllegalArgumentException("Unit can not null");
      }

      this.value = value;
      this.unit = unit;

  }
        public double toFeet(){
            return unit.toFeet(value);
        }

        public boolean equals(Object obj){

            if(this==obj){
                return true;
            }
            if(obj==null) return false;
            if(getClass()!=obj.getClass()) return false;

            Measurement other = (Measurement) obj;
            return Double.compare(this.toFeet(),other.toFeet())==0;
      }


}