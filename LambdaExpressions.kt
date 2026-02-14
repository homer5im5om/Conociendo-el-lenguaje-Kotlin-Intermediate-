/**
 * Conclusión: Las expresiones lambda con receptor (Lambda expressions with receiver) 
 * nos permiten llamar a los métodos y propiedades del objeto receptor directamente 
 * dentro de la lambda, sin necesidad de usar la palabra 'it' o el nombre del objeto. 
 * Esto es extremadamente útil para crear constructores fluidos (builders) y hacer 
 * que el código sea mucho más natural y fácil de leer, como vimos al usar 'StringBuilder' 
 * y 'buildList'.
 */

// --- Exercise 1: StringBuilder Receiver ---
fun fetchData(callback: StringBuilder.() -> Unit) {
    val builder = StringBuilder("Data received")
    // Llamamos a la lambda sobre el objeto builder
    builder.callback()
}

// --- Exercise 2: Custom Event Receiver ---
class Button {
    fun onEvent(action: ButtonEvent.() -> Unit) {
        val event = ButtonEvent(isRightClick = false, amount = 2, position = Position(100, 200))
        event.action() 
    }
}

data class ButtonEvent(val isRightClick: Boolean, val amount: Int, val position: Position)
data class Position(val x: Int, val y: Int)

// --- Exercise 3: buildList Receiver ---
fun List<Int>.incremented(): List<Int> {
    val originalList = this
    // buildList proporciona una MutableList como receptor (this)
    return buildList {
        for (n in originalList) {
            add(n + 1) // Se llama a add() directamente sobre el receptor
        }
    }
}

fun main() {
    println("--- Prueba Ejercicio 1 ---")
    fetchData {
        // 'append' se llama directamente sobre el StringBuilder
        append(" - Processed")
        println(this.toString())
    }

    println("\n--- Prueba Ejercicio 2 ---")
    val button = Button()
    button.onEvent {
        // Accedemos a 'isRightClick' y 'amount' directamente sin usar 'it.'
        if (!isRightClick && amount == 2) {
            println("Double click!")
        }
    }

    println("\n--- Prueba Ejercicio 3 ---")
    val originalList = listOf(1, 2, 3)
    val newList = originalList.incremented()
    println(newList)
}
