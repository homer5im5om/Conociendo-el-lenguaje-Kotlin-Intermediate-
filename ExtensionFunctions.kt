/**
 * Conclusión: Las funciones de extensión (Extension Functions) nos permiten agregar 
 * nuevas funcionalidades a clases que ya existen (como Int o String) sin tener que heredar 
 * de ellas ni modificar su código fuente original. Utilizamos la palabra clave 'this' 
 * para referirnos al objeto sobre el cual estamos llamando la función.
 */

// Ejercicio 1: Extensión para la clase Int
// Agrega una función para verificar si un número entero es mayor a cero.
fun Int.isPositive(): Boolean = this > 0

// Ejercicio 2: Extensión para la clase String
// Agrega una función para convertir una cadena de texto a minúsculas.
fun String.toLowercaseString(): String = this.lowercase()

fun main() {
    // Prueba del Ejercicio 1
    val number = 1
    println("¿El número $number es positivo?: ${number.isPositive()}") // Imprime: true
    
    // Prueba del Ejercicio 2
    val text = "Hello World!"
    println("Texto original: $text")
    println("Texto modificado: ${text.toLowercaseString()}") // Imprime: hello world!
}
