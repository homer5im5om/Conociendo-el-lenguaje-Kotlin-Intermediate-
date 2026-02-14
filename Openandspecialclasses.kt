/**
 * Conclusión: Las clases selladas (sealed classes) nos permiten restringir la herencia,
 * creando jerarquías cerradas y seguras. Son perfectas para representar estados finitos 
 * (como el estado de una entrega o la carga de una pantalla). Al usarlas junto con 'when', 
 * el compilador nos obliga a manejar todas las opciones posibles sin necesidad de usar un 'else', 
 * reduciendo errores. Además, vimos cómo combinarlas con 'enum classes' para agrupar constantes relacionadas.
 */

// --- Exercise 1: Sealed Classes for State ---
sealed class DeliveryStatus {
    data class Pending(val sender: String) : DeliveryStatus()
    data class InTransit(val estimatedDeliveryDate: String) : DeliveryStatus()
    data class Delivered(val deliveryDate: String, val recipient: String) : DeliveryStatus()
    data class Canceled(val reason: String) : DeliveryStatus()
}

fun printDeliveryStatus(status: DeliveryStatus) {
    when (status) {
        is DeliveryStatus.Pending -> {
            println("The package is pending pickup from ${status.sender}.")
        }
        is DeliveryStatus.InTransit -> {
            println("The package is in transit and expected to arrive by ${status.estimatedDeliveryDate}.")
        }
        is DeliveryStatus.Delivered -> {
            println("The package was delivered to ${status.recipient} on ${status.deliveryDate}.")
        }
        is DeliveryStatus.Canceled -> {
            println("The delivery was canceled due to: ${status.reason}.")
        }
    }
}

// --- Exercise 2: Sealed Classes with Enums ---
sealed class Status {
    data object Loading : Status()
    
    data class Error(val problem: Problem) : Status() {
        enum class Problem {
            NETWORK,
            TIMEOUT,
            UNKNOWN
        }
    }

    data class OK(val data: List<String>) : Status()
}

fun handleStatus(status: Status) {
    when (status) {
        is Status.Loading -> println("Loading...")
        is Status.OK -> println("Data received: ${status.data}")
        is Status.Error -> when (status.problem) {
            Status.Error.Problem.NETWORK -> println("Network issue")
            Status.Error.Problem.TIMEOUT -> println("Request timed out")
            Status.Error.Problem.UNKNOWN -> println("Unknown error occurred")
        }
    }
}

// --- Unified Main Function ---
fun main() {
    println("--- Prueba Ejercicio 1 ---")
    val delStatus1: DeliveryStatus = DeliveryStatus.Pending("Alice")
    val delStatus2: DeliveryStatus = DeliveryStatus.InTransit("2024-11-20")
    val delStatus3: DeliveryStatus = DeliveryStatus.Delivered("2024-11-18", "Bob")
    val delStatus4: DeliveryStatus = DeliveryStatus.Canceled("Address not found")

    printDeliveryStatus(delStatus1)
    printDeliveryStatus(delStatus2)
    printDeliveryStatus(delStatus3)
    printDeliveryStatus(delStatus4)

    println("\n--- Prueba Ejercicio 2 ---")
    val appStatus1: Status = Status.Error(Status.Error.Problem.NETWORK)
    val appStatus2: Status = Status.OK(listOf("Data1", "Data2"))

    handleStatus(appStatus1)
    handleStatus(appStatus2)
}
