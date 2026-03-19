import java.util.Scanner;

public class Inch {

    public static boolean checkInchEquality(double inchValue1,double inchValue2){

        InchCheck i1 = new InchCheck(inchValue1);
        InchCheck i2= new InchCheck(inchValue2);

        return i1.equals(i2);
    }

    public static boolean checkFeetEquality(double feetValue1,double feetValue2){
        FeetCheck1 f1 = new FeetCheck1(feetValue1);
        FeetCheck1 f2 = new FeetCheck1(feetValue2);

        return f1.equals(f2);
    }



    static void main(String[] args) {

        Scanner sc =new Scanner(System.in);

        System.out.println("enter first inch value:");
        double inchValue1 = sc.nextDouble();

        System.out.println("enter second inch value");
        double inchValue2 = sc.nextDouble();

        System.out.println("enter first feet value");
        double feetValue1 = sc.nextDouble();

        System.out.println("enter second feet value");
        double feetValue2 = sc.nextDouble();

        System.out.println("Inch Equal:" + checkInchEquality(inchValue1,inchValue2) );
        System.out.println("Feet Equal:"+ checkFeetEquality(feetValue1,feetValue2));

    }
}

class FeetCheck1{
    private final double value;

    public  FeetCheck1(double value){
        this.value = value;
    }

    public boolean equals(Object obj){

        if(this==obj){
            return true;
        }

        if(obj==null || getClass() != obj.getClass()){
            return false;
        }

        FeetCheck1 other = (FeetCheck1) obj;
        return Double.compare(this.value,other.value)==0;
    }
}

class InchCheck{
    private final double value;

    public  InchCheck(double value){
        this.value =value;
    }

    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }

        if(obj==null || getClass()!=obj.getClass()){
            return  false;
        }

        InchCheck other = (InchCheck) obj;

        return Double.compare(this.value,other.value)==0;
    }
}

