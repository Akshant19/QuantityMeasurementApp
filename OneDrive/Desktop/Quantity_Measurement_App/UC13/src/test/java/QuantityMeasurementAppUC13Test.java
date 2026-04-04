import org.apps.quantitymeasurement.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppUC13Test {

    @Test
    public void testAdd() {

        QuantityUC13<LengthUnitUC13> a =
                new QuantityUC13<>(1,
                        LengthUnitUC13.FEET);

        QuantityUC13<LengthUnitUC13> b =
                new QuantityUC13<>(12,
                        LengthUnitUC13.INCHES);

        assertEquals(
                2,
                a.add(b).getValue());
    }


    @Test
    public void testSubtract() {

        QuantityUC13<WeightUnitUC13> a =
                new QuantityUC13<>(10,
                        WeightUnitUC13.KILOGRAM);

        QuantityUC13<WeightUnitUC13> b =
                new QuantityUC13<>(5,
                        WeightUnitUC13.KILOGRAM);

        assertEquals(
                5,
                a.subtract(b).getValue());
    }


    @Test
    public void testDivide() {

        QuantityUC13<VolumeUnitUC13> a =
                new QuantityUC13<>(10,
                        VolumeUnitUC13.LITRE);

        QuantityUC13<VolumeUnitUC13> b =
                new QuantityUC13<>(5,
                        VolumeUnitUC13.LITRE);

        assertEquals(
                2,
                a.divide(b));
    }
}