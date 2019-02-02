package frc.robot.tools;

public class UnitConversion {
    public static final double PI = 3.141592;

    public static final double METERS_PER_FEET = 0.3048;
    public static final double FEET_PER_METER = 3.28084;
    
    public static final double KILOGRAMS_PER_POUND = 0.453592;
    public static final double POUNDS_PER_KILOGRAM = 2.20462;

    public static double convertMetersToFeet(double meters) {
        return meters * FEET_PER_METER;
    }

    public static double convertFeetToMeters(double feet) {
        return feet * METERS_PER_FEET;
    }

    public static double convertPoundsToKilograms(double pounds) {
        return pounds * KILOGRAMS_PER_POUND;
    }

    public static double convertKilogramsToPounds(double kilograms) {
        return kilograms * POUNDS_PER_KILOGRAM;
    }

    public static double convertRevolutionsPerMinuteToRadiansPerSecond(double rpm) {
        return rpm * 2.0 * PI / 60;
    }

    public static double convertRadiansToDegrees(double radians) {
        return 180.0 * radians / Math.PI;
    }

    public static double convertDegreesToRadians(double degrees) {
        return Math.PI * degrees / 180.0;
    }

    public static double convertInchesToFeet(double inches) {
        return inches / 12.0;
    }

    public static double convertFeetToInches(double feet) {
        return feet * 12.0;
    }

}