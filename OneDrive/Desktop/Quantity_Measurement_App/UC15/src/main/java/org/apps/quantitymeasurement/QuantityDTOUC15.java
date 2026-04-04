package org.apps.quantitymeasurement;

public final class QuantityDTOUC15 {

    private final double value;
    private final String unitName;
    private final String measurementType;

    public QuantityDTOUC15(double value, String unitName, String measurementType) {
        this.value = value;
        this.unitName = unitName;
        this.measurementType = measurementType;
    }

    public double getValue() { return value; }
    public String getUnitName() { return unitName; }
    public String getMeasurementType() { return measurementType; }
}