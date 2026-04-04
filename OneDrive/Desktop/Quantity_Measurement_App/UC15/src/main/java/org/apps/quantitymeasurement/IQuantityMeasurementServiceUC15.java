package org.apps.quantitymeasurement;

public interface IQuantityMeasurementServiceUC15 {
    QuantityDTOUC15 add(QuantityDTOUC15 first, QuantityDTOUC15 second);
    QuantityDTOUC15 convert(QuantityDTOUC15 dto, String targetUnit);
}