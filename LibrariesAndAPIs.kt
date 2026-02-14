import kotlin.math.pow
import kotlin.time.measureTime

/**
 * Conclusión: Las bibliotecas estándar y APIs de Kotlin nos ahorran mucho tiempo 
 * al proporcionar herramientas robustas ya construidas. Aprendimos a usar 
 * 'kotlin.math' para cálculos financieros o científicos complejos, 'kotlin.time' para medir 
 * el rendimiento de ejecución de nuestro código, y cómo habilitar o probar 
 * características nuevas del lenguaje usando las anotaciones de 'Opt-In' 
 * (como @ExperimentalStdlibApi o usando @OptIn).
 */

// --- Exercise 1: Math API ---
fun calculateCompoundInterest(P: Double, r: Double, n: Int, t: Int): Double {
    return P * (1 + r / n).pow(n * t)
}

// --- Exercise 3: Experimental APIs ---
// Forma 1: Usando @OptIn
@OptIn(ExperimentalStdlibApi::class)
fun useExperimentalFeature() {
    println("Using an experimental feature with @OptIn.")
}

// Forma 2: Usando la anotación directamente (Probablemente la respuesta que pedía tu test)
@ExperimentalStdlibApi
fun anotherExperimentalFunction() {
    println("Using an experimental feature with direct annotation.")
}

// --- Unified Main Function ---
@OptIn(ExperimentalStdlibApi::class) // Se requiere para llamar a funciones experimentales aquí
fun main() {
    println("--- Prueba Exercise 1 ---")
    val principal = 1000.0
    val rate = 0.05
    val timesCompounded = 4
    val years = 5
    val amount = calculateCompoundInterest(principal, rate, timesCompounded, years)
    println("The accumulated amount is: $amount")

    println("\n--- Prueba Exercise 2 ---")
    // Exercise 2: Time API
    val timeTaken = measureTime {
        // Simular procesamiento de datos
        val data = List(1000) { it * 2 }
        val filteredData = data.filter { it % 3 == 0 }
        val processedData = filteredData.map { it / 2 }
        println("Processed data successfully.")
    }
    println("Time taken to process data: $timeTaken")

    println("\n--- Prueba Exercise 3 ---")
    useExperimentalFeature()
    anotherExperimentalFunction()
}
