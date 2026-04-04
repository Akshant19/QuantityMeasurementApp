import org.apps.quantitymeasurement.QuantityDTOUC15;
import org.apps.quantitymeasurement.QuantityMeasurementExceptionUC15;
import org.apps.quantitymeasurement.QuantityMeasurementServiceImplUC15;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuantityMeasurementServiceImplUC15Test {

    private final QuantityMeasurementServiceImplUC15 service =
            new QuantityMeasurementServiceImplUC15();

    @Test
    void testAdditionSuccess() {

        QuantityDTOUC15 q1 = new QuantityDTOUC15(2.0, "FEET", "LengthUnit");
        QuantityDTOUC15 q2 = new QuantityDTOUC15(3.0, "FEET", "LengthUnit");

        QuantityDTOUC15 result = service.add(q1, q2);

        assertEquals(5.0, result.getValue());
    }

    @Test
    void testAdditionNull() {

        assertThrows(QuantityMeasurementExceptionUC15.class,
                () -> service.add(null, null));
    }

    @Test
    void testMeasurementMismatch() {

        QuantityDTOUC15 q1 = new QuantityDTOUC15(2.0, "FEET", "LengthUnit");
        QuantityDTOUC15 q2 = new QuantityDTOUC15(3.0, "KG", "WeightUnit");

        assertThrows(QuantityMeasurementExceptionUC15.class,
                () -> service.add(q1, q2));
    }
}
