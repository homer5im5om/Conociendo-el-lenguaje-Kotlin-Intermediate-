/**
 * Conclusión: Las funciones de alcance (Scope functions) como 'let', 'apply' y 'also' 
 * nos permiten ejecutar un bloque de código en el contexto de un objeto, haciendo el código más conciso. 
 * - 'let' es excelente para transformaciones y para trabajar con objetos que podrían ser nulos (junto con '?').
 * - 'apply' es ideal para configurar las propiedades de un objeto usando 'this'.
 * - 'also' es muy útil para realizar acciones secundarias o adicionales (como imprimir logs) usando 'it'.
 */

// --- Ejercicio 1: Uso de 'let' ---
data class ProductInfo(val priceInDollars: Double?)

class Product {
    fun getProductInfo(): ProductInfo? {
        return ProductInfo(100.0)
    }
}

// Usamos 'let' de forma segura (?.) para convertir los dólares a euros solo si el valor no es nulo.
fun Product.getPriceInEuros() = getProductInfo()?.priceInDollars?.let { convertToEuros(it) }

fun convertToEuros(dollars: Double): Double {
    return dollars * 0.85
}

// --- Ejercicio 2: Uso de 'apply' y 'also' ---
data class User(val id: Int, var email: String)

// 'apply' modifica el objeto internamente (actualiza el email).
// 'also' ejecuta una acción extra (imprime un mensaje) devolviendo el mismo objeto modificado.
fun updateEmail(user: User, newEmail: String): User = user.apply {
    this.email = newEmail
}.also { 
    println("Updating email for user with ID: ${it.id}") 
}

fun main() {
    println("--- Prueba Ejercicio 1 ---")
    val product = Product()
    val priceInEuros = product.getPriceInEuros()

    if (priceInEuros != null) {
        println("Price in Euros: €$priceInEuros")
    } else {
        println("Price information is not available.")
    }

    println("\n--- Prueba Ejercicio 2 ---")
    val user = User(1, "old_email@example.com")
    // Al llamar a la función, se ejecutará el 'also' y se imprimirá el log de actualización
    val updatedUser = updateEmail(user, "new_email@example.com")
    println("Updated User: $updatedUser")
}
