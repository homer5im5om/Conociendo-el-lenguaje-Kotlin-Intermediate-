/**
 * Conclusión: Kotlin ofrece herramientas muy avanzadas para manejar nulos de forma segura y elegante.
 * En esta sección aprendimos a usar el "cast seguro" ('as?') para evitar excepciones al convertir tipos, 
 * retornos tempranos (early returns) combinados con el operador Elvis ('?:'), 
 * y funciones de colecciones muy útiles como 'listOfNotNull', 'singleOrNull' y 'mapNotNull', 
 * las cuales filtran automáticamente los valores nulos, manteniendo nuestro código limpio y robusto.
 */

// --- Exercise 1: Safe Casts and listOfNotNull ---
// Renamed to NotificationUser to avoid conflict with Exercise 3
data class NotificationUser(val name: String?)

fun getNotificationPreferences(user: Any, emailEnabled: Boolean, smsEnabled: Boolean): List<String> {
    val validUser = user as? NotificationUser ?: return emptyList()
    val userName = validUser.name ?: "Guest"

    return listOfNotNull(
        "Email Notifications enabled for $userName".takeIf { emailEnabled },
        "SMS Notifications enabled for $userName".takeIf { smsEnabled }
    )
}

// --- Exercise 2: singleOrNull ---
data class Subscription(val name: String, val isActive: Boolean)

fun getActiveSubscription(subscriptions: List<Subscription>): Subscription? {
    return subscriptions.singleOrNull { subscription -> subscription.isActive }
}

// --- Exercise 3: mapNotNull ---
// Renamed to SystemUser to avoid conflict with Exercise 1
data class SystemUser(val username: String, val isActive: Boolean)

fun getActiveUsernames(users: List<SystemUser>): List<String> {
    return users.mapNotNull { user ->
        if (user.isActive) user.username else null
    }
}

// --- Exercise 4: Early returns with Elvis ---
fun validateStock(requested: Int?, available: Int?): Int {
    val validRequested = requested ?: return -1
    val validAvailable = available ?: return -1

    if (validRequested < 0) return -1
    if (validRequested > validAvailable) return -1

    return validRequested
}

// --- Unified Main Function ---
fun main() {
    println("--- Prueba Exercise 1 ---")
    val user1 = NotificationUser("Alice")
    val user2 = NotificationUser(null)
    val invalidUser = "NotAUser"

    println(getNotificationPreferences(user1, emailEnabled = true, smsEnabled = false))
    println(getNotificationPreferences(user2, emailEnabled = false, smsEnabled = true))
    println(getNotificationPreferences(invalidUser, emailEnabled = true, smsEnabled = true))

    println("\n--- Prueba Exercise 2 ---")
    val userWithPremiumPlan = listOf(
        Subscription("Basic Plan", false),
        Subscription("Premium Plan", true)
    )
    val userWithConflictingPlans = listOf(
        Subscription("Basic Plan", true),
        Subscription("Premium Plan", true)
    )

    println(getActiveSubscription(userWithPremiumPlan))
    println(getActiveSubscription(userWithConflictingPlans)) // Returns null because there are multiple true values

    println("\n--- Prueba Exercise 3 ---")
    val allUsers = listOf(
        SystemUser("alice123", true),
        SystemUser("bob_the_builder", false),
        SystemUser("charlie99", true)
    )

    println(getActiveUsernames(allUsers))

    println("\n--- Prueba Exercise 4 ---")
    println(validateStock(5, 10))
    println(validateStock(null, 10))
    println(validateStock(-2, 10))
}
