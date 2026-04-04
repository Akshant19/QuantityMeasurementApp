package org.apps.quantitymeasurement;

public enum VolumeUnitUC14 implements IMeasurableUC14 {

    LITRE(1),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double factor;

    VolumeUnitUC14(double factor) {
        this.factor = factor;
    }

    public String getUnitName() {
        return name();
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

}