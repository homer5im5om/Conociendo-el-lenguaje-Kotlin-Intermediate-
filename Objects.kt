/**
 * Conclusión: En Kotlin, la palabra clave 'object' es una herramienta muy poderosa.
 * - 'object' (como FlyingSkateboard) implementa el patrón Singleton, asegurando que solo exista una instancia de esa clase.
 * - 'data object' (como OrderOne) es útil cuando necesitamos un Singleton que además tenga una representación de texto limpia (para imprimir o serializar).
 * - 'companion object' nos permite definir métodos y propiedades que pertenecen a la clase en sí, no a una instancia específica (similar a los métodos estáticos en Java), lo cual es ideal para crear funciones de fábrica (factory methods) como 'fromFahrenheit'.
 */

// --- Exercise 1: Data Objects ---
interface Order {
    val orderId: String
    val customerName: String
    val orderTotal: Double
}

data object OrderOne: Order {
    override val orderId = "001"
    override val customerName = "Alice"
    override val orderTotal = 15.50
}

data object OrderTwo: Order {
    override val orderId = "002"
    override val customerName = "Bob"
    override val orderTotal = 12.75
}

// --- Exercise 2: Object Declarations (Singletons) ---
interface Vehicle {
    val name: String
    fun move(): String
}

object FlyingSkateboard : Vehicle {
    override val name = "Flying Skateboard"
    override fun move() = "Glides through the air with a hover engine"
    fun fly(): String = "Woooooooo"
}

// --- Exercise 3: Companion Objects ---
data class Temperature(val celsius: Double) {
    val fahrenheit: Double = celsius * 9 / 5 + 32

    companion object {
        // Esto actúa como un Factory Method
        fun fromFahrenheit(fahrenheit: Double): Temperature = Temperature((fahrenheit - 32) * 5 / 9)
    }
}

// --- Unified Main Function ---
fun main() {
    println("--- Prueba Ejercicio 1 ---")
    println("Order name: $OrderOne")
    println("Order name: $OrderTwo")
    println("Are the two orders identical? ${OrderOne == OrderTwo}")

    if (OrderOne == OrderTwo) {
        println("The orders are identical.")
    } else {
        println("The orders are unique.")
    }
    println("Do the orders have the same customer name? ${OrderOne.customerName == OrderTwo.customerName}")

    println("\n--- Prueba Ejercicio 2 ---")
    println("${FlyingSkateboard.name}: ${FlyingSkateboard.move()}")
    println("${FlyingSkateboard.name}: ${FlyingSkateboard.fly()}")

    println("\n--- Prueba Ejercicio 3 ---")
    val fahrenheit = 90.0
    val temp = Temperature.fromFahrenheit(fahrenheit)
    println("${temp.celsius}°C is $fahrenheit °F")
}
