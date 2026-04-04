package org.apps.quantitymeasurement;

public class QuantityMeasurementAppUC14 {

    public static void main(String[] args) {

        QuantityUC14<TemperatureUnitUC14> t1 =
                new QuantityUC14<>(0,
                        TemperatureUnitUC14.CELSIUS);

        QuantityUC14<TemperatureUnitUC14> t2 =
                new QuantityUC14<>(32,
                        TemperatureUnitUC14.FAHRENHEIT);

        System.out.println(
                t1.equals(t2));

    }

}