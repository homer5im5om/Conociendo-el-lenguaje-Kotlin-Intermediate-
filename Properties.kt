import kotlin.properties.Delegates.observable

/**
 * Conclusión: En Kotlin, las propiedades (Properties) son mucho más avanzadas que en otros lenguajes. 
 * No necesitamos escribir "getters" y "setters" repetitivos, ya que Kotlin los genera bajo el capó.
 * Aprendimos a crear propiedades de extensión con getters personalizados (como asMiles), a usar 
 * inicialización perezosa con 'by lazy' (para ahorrar memoria y ejecutar código solo cuando se necesita), 
 * y a usar delegados observables con 'by observable' para reaccionar automáticamente cada vez que el valor 
 * de una variable cambia.
 */

// --- Exercise 1: Property Access in Collections ---
fun findOutOfStockBooks(inventory: List<Int>): List<Int> {
    val outOfStockIndices = mutableListOf<Int>()
    // 'indices' is a built-in property of the List
    for (index in inventory.indices) {
        if (inventory[index] == 0) {
            outOfStockIndices.add(index)
        }
    }
    return outOfStockIndices
}

// --- Exercise 2: Extension Properties ---
// Custom getter to convert kilometers to miles
val Double.asMiles: Double
    get() = this * 0.621371

// --- Exercise 3: Lazy Delegation ---
fun checkAppServer(): Boolean {
    println("Performing application server health check...")
    return true
}

fun checkDatabase(): Boolean {
    println("Performing database health check...")
    return false
}

// --- Exercise 4: Observable Properties ---
class Budget(val totalBudget: Int) {
    // This property watches for any changes to its value
    var remainingBudget: Int by observable(totalBudget) { _, oldValue, newValue ->
        if (newValue < totalBudget * 0.2) {
            println("Warning: Your remaining budget ($newValue) is below 20% of your total budget.")
        } else if (newValue > oldValue) {
            println("Good news: Your remaining budget increased to $newValue.")
        }
    }
}

// --- Unified Main Function ---
fun main() {
    println("--- Prueba Exercise 1 ---")
    val inventory = listOf(3, 0, 7, 0, 5)
    println(findOutOfStockBooks(inventory))

    println("\n--- Prueba Exercise 2 ---")
    val distanceKm = 5.0
    println("$distanceKm km is ${distanceKm.asMiles} miles")

    val marathonDistance = 42.195
    println("$marathonDistance km is ${marathonDistance.asMiles} miles")

    println("\n--- Prueba Exercise 3 ---")
    // These functions are only called when the variables are accessed for the first time
    val isAppServerHealthy by lazy { checkAppServer() }
    val isDatabaseHealthy by lazy { checkDatabase() }

    when {
        isAppServerHealthy -> println("Application server is online and healthy")
        isDatabaseHealthy -> println("Database is healthy")
        else -> println("System is offline")
    }

    println("\n--- Prueba Exercise 4 ---")
    val myBudget = Budget(totalBudget = 1000)
    myBudget.remainingBudget = 800
    myBudget.remainingBudget = 150
    myBudget.remainingBudget = 50
    myBudget.remainingBudget = 300
}
