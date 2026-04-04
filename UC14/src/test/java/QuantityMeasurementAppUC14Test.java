import org.apps.quantitymeasurement.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppUC14Test {


    @Test
    public void testLengthEquality() {

        QuantityUC14<LengthUnitUC14> a =
                new QuantityUC14<>(1,
                        LengthUnitUC14.FEET);

        QuantityUC14<LengthUnitUC14> b =
                new QuantityUC14<>(12,
                        LengthUnitUC14.INCHES);

        assertTrue(a.equals(b));
    }


    @Test
    public void testWeightAddition() {

        QuantityUC14<WeightUnitUC14> a =
                new QuantityUC14<>(1,
                        WeightUnitUC14.KILOGRAM);

        QuantityUC14<WeightUnitUC14> b =
                new QuantityUC14<>(1000,
                        WeightUnitUC14.GRAM);

        QuantityUC14<WeightUnitUC14> result =
                a.add(b);

        assertEquals(
                2,
                result.getValue()
        );
    }


    @Test
    public void testVolumeSubtraction() {

        QuantityUC14<VolumeUnitUC14> a =
                new QuantityUC14<>(5,
                        VolumeUnitUC14.LITRE);

        QuantityUC14<VolumeUnitUC14> b =
                new QuantityUC14<>(2,
                        VolumeUnitUC14.LITRE);

        QuantityUC14<VolumeUnitUC14> result =
                a.subtract(b);

        assertEquals(
                3,
                result.getValue()
        );
    }


    @Test
    public void testDivision() {

        QuantityUC14<LengthUnitUC14> a =
                new QuantityUC14<>(10,
                        LengthUnitUC14.FEET);

        QuantityUC14<LengthUnitUC14> b =
                new QuantityUC14<>(5,
                        LengthUnitUC14.FEET);

        double r = a.divide(b);

        assertEquals(
                2,
                r
        );
    }


    @Test
    public void testTemperatureEquality() {

        QuantityUC14<TemperatureUnitUC14> t1 =
                new QuantityUC14<>(0,
                        TemperatureUnitUC14.CELSIUS);

        QuantityUC14<TemperatureUnitUC14> t2 =
                new QuantityUC14<>(32,
                        TemperatureUnitUC14.FAHRENHEIT);

        assertTrue(
                t1.equals(t2)
        );
    }


    @Test
    public void testTemperatureConversion() {

        QuantityUC14<TemperatureUnitUC14> t =
                new QuantityUC14<>(100,
                        TemperatureUnitUC14.CELSIUS);

        double f =
                t.convertTo(
                        TemperatureUnitUC14.FAHRENHEIT);

        assertEquals(
                212,
                f
        );
    }


    @Test
    public void testTemperatureAddUnsupported() {

        QuantityUC14<TemperatureUnitUC14> t1 =
                new QuantityUC14<>(100,
                        TemperatureUnitUC14.CELSIUS);

        QuantityUC14<TemperatureUnitUC14> t2 =
                new QuantityUC14<>(50,
                        TemperatureUnitUC14.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.add(t2)
        );
    }


    @Test
    public void testTemperatureSubtractUnsupported() {

        QuantityUC14<TemperatureUnitUC14> t1 =
                new QuantityUC14<>(100,
                        TemperatureUnitUC14.CELSIUS);

        QuantityUC14<TemperatureUnitUC14> t2 =
                new QuantityUC14<>(50,
                        TemperatureUnitUC14.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.subtract(t2)
        );
    }


    @Test
    public void testTemperatureDivideUnsupported() {

        QuantityUC14<TemperatureUnitUC14> t1 =
                new QuantityUC14<>(100,
                        TemperatureUnitUC14.CELSIUS);

        QuantityUC14<TemperatureUnitUC14> t2 =
                new QuantityUC14<>(50,
                        TemperatureUnitUC14.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.divide(t2)
        );
    }


    @Test
    public void testDifferentCategoryEquality() {

        QuantityUC14<LengthUnitUC14> a =
                new QuantityUC14<>(1,
                        LengthUnitUC14.FEET);

        QuantityUC14<WeightUnitUC14> b =
                new QuantityUC14<>(1,
                        WeightUnitUC14.KILOGRAM);

        assertFalse(
                a.equals(b)
        );
    }

}