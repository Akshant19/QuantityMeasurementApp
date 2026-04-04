package org.apps.quantitymeasurement;

import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementServiceImplUC15 implements IQuantityMeasurementServiceUC15 {

    @Override
    public QuantityDTOUC15 add(QuantityDTOUC15 first, QuantityDTOUC15 second) {

        if (first == null || second == null) {
            throw new QuantityMeasurementExceptionUC15("Quantities cannot be null");
        }

        if (!first.getMeasurementType().equals(second.getMeasurementType())) {
            throw new QuantityMeasurementExceptionUC15("Measurement type mismatch");
        }

        double result = first.getValue() + second.getValue();

        return new QuantityDTOUC15(result, first.getUnitName(), first.getMeasurementType());
    }

    @Override
    public QuantityDTOUC15 convert(QuantityDTOUC15 dto, String targetUnit) {

        if (dto == null) {
            throw new QuantityMeasurementExceptionUC15("Input cannot be null");
        }

        return new QuantityDTOUC15(dto.getValue(), targetUnit, dto.getMeasurementType());
    }
}

