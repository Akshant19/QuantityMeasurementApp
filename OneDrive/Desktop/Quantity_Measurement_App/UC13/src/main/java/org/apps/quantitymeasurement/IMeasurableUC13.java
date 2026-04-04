package org.apps.quantitymeasurement;

public interface IMeasurableUC13 {

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);
}