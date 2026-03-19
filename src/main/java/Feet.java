import java.util.Scanner;

public class Feet {
    static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first feet value:");
        double value1 = sc.nextDouble();

        System.out.println("Enter second feet value:");
        double value2 = sc.nextDouble();

        FeetCheck f1 = new FeetCheck(value1);
        FeetCheck f2 = new FeetCheck(value2);

        boolean result = f1.equals(f2);

        System.out.println("Measurement equal: " + result);
    }
}


class FeetCheck{

    private final double value;

    FeetCheck(double value){
        this.value = value;
    }

    public boolean equals(Object obj){

        if(this==obj){
            return true;
        }

        if(obj==null || getClass() != obj.getClass() ){
            return false;
        }

        FeetCheck other =(FeetCheck) obj;

        return Double.compare(this.value, other.value)==0;
    }
}