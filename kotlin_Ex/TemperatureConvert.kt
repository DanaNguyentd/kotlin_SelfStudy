
/**
There are three main temperature scales used in the world: Celsius, Fahrenheit, and Kelvin.
In the initial code provided in the following code snippet, write a program that converts a temperature from one scale to another with these formulas:
​
Celsius to Fahrenheit: ° F = 9/5 (° C) + 32
Kelvin to Celsius: ° C = K - 273.15
Fahrenheit to Kelvin: K = 5/9 (° F - 32) + 273.15
Note that the String.format("%.2f", /* measurement method is used to convert a number into a String type with 2 decimal places.
 */
​
fun main() {
    val CelValue = 27.0
    printFinalTemperatureWithFunction(CelValue, "Celsius", "Fahrenheit", celsiusToFahrenheit(CelValue))
    printFinalTemperature(350.0, "Kelvin", "Celsius") { it - 273.15 }
    printFinalTemperature(10.0, "Fahrenheit", "Kelvin") { 5.0 / 9.0 * (it - 32) + 273.15 }
}
​
fun celsiusToFahrenheit(celsius : Double): Double {
    return (9/5 * (celsius) + 32.0)
}
        
fun kelvinToCelsius(kelvin  : Double): Double {
    return kelvin - 273.15
} 
        
fun fahrenheitToKelvin(fahrenheit: Double): Double {
    return  (5.0 / 9.0 )* (fahrenheit - 32) + 273.15 
}
​
fun printFinalTemperature(
    initialMeasurement: Double, 
    initialUnit: String, 
    finalUnit: String, 
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}
​
fun printFinalTemperatureWithFunction(
    initialMeasurement: Double, 
    initialUnit: String, 
    finalUnit: String, 
    conversionFormula: Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}
27.0 degrees Celsius is 59.00 degrees Fahrenheit.
350.0 degrees Kelvin is 76.85 degrees Celsius.
10.0 degrees Fahrenheit is 260.93 degrees Kelvin.