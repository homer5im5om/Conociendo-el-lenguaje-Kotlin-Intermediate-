/**
 * Conclusión: Las clases abstractas y las interfaces nos ayudan a definir estructuras 
 * y contratos para nuestra aplicación. 
 * - Las clases abstractas (abstract class) permiten compartir estado y código común.
 * - Las interfaces definen comportamientos que las clases deben implementar obligatoriamente.
 * - Además, exploramos el patrón de delegación con la palabra clave 'by', que permite 
 * que una clase pase la ejecución de ciertos métodos a otro objeto, promoviendo la reutilización 
 * de código de forma muy limpia.
 */

// --- Exercise 1: Abstract Classes ---
abstract class SmartDevice(val name: String) {
    abstract fun turnOn()
    abstract fun turnOff()
}

class SmartLight(name: String) : SmartDevice(name) {
    override fun turnOn() { println("$name is now ON.") }
    override fun turnOff() { println("$name is now OFF.") }
    fun adjustBrightness(level: Int) { println("Adjusting $name brightness to $level%.") }
}

class SmartThermostat(name: String) : SmartDevice(name) {
    override fun turnOn() { println("$name thermostat is now heating.") }
    override fun turnOff() { println("$name thermostat is now off.") }
    fun adjustTemperature(temperature: Int) { println("$name thermostat set to $temperature°C.") }
}

// --- Exercise 2: Interfaces ---
interface Media {
    val title: String
    fun play()
}

class Audio(override val title: String, val composer: String) : Media {
    override fun play() { println("Playing audio: $title, composed by $composer") }
}

// --- Exercise 3: Combining Abstract Classes and Interfaces ---
interface Refundable {
    fun refund(amount: Double)
}

abstract class PaymentMethod(val name: String) {
    fun authorize(amount: Double) { println("Authorizing payment of $$amount.") }
    abstract fun processPayment(amount: Double)
}

class CreditCard(name: String) : PaymentMethod(name), Refundable {
    override fun processPayment(amount: Double) { println("Processing credit card payment of $$amount.") }
    override fun refund(amount: Double) { println("Refunding $$amount to the credit card.") }
}

// --- Exercise 4: Delegation with 'by' ---
interface Messenger {
    fun sendMessage(message: String)
    fun receiveMessage(): String
}

class BasicMessenger : Messenger {
    override fun sendMessage(message: String) { println("Sending message: $message") }
    override fun receiveMessage(): String { return "You've got a new message!" }
}

class SmartMessenger(val basicMessenger: BasicMessenger) : Messenger by basicMessenger {
    override fun sendMessage(message: String) {
        println("Sending a smart message: $message")
        basicMessenger.sendMessage("[smart] $message")
    }
}

// --- Unified Main Function ---
fun main() {
    println("--- Prueba Ejercicio 1 ---")
    val livingRoomLight = SmartLight("Living Room Light")
    val bedroomThermostat = SmartThermostat("Bedroom Thermostat")

    livingRoomLight.turnOn()
    livingRoomLight.adjustBrightness(10)
    livingRoomLight.turnOff()

    bedroomThermostat.turnOn()
    bedroomThermostat.adjustTemperature(5)
    bedroomThermostat.turnOff()

    println("\n--- Prueba Ejercicio 2 ---")
    val audio = Audio("Symphony No. 5", "Beethoven")
    audio.play()

    println("\n--- Prueba Ejercicio 3 ---")
    val visa = CreditCard("Visa")
    visa.authorize(100.0)
    visa.processPayment(100.0)
    visa.refund(50.0)

    println("\n--- Prueba Ejercicio 4 ---")
    val basicMessenger = BasicMessenger()
    val smartMessenger = SmartMessenger(basicMessenger)

    basicMessenger.sendMessage("Hello!")
    println(smartMessenger.receiveMessage())
    smartMessenger.sendMessage("Hello from SmartMessenger!")
}
