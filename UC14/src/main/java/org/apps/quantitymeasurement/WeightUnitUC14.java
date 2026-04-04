package org.apps.quantitymeasurement;

public enum WeightUnitUC14 implements IMeasurableUC14 {

    GRAM(1),
    KILOGRAM(1000),
    TONNE(1000000);

    private final double factor;

    WeightUnitUC14(double factor) {
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