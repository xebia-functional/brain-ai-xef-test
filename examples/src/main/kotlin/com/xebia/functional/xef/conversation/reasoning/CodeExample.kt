package com.xebia.functional.xef.conversation.reasoning

import com.xebia.functional.xef.conversation.llm.openai.OpenAI
import com.xebia.functional.xef.reasoning.code.Code

suspend fun main() {
  OpenAI.conversation {
    val code =
      Code(
        model = OpenAI.fromEnvironment().DEFAULT_CHAT,
        serialization = OpenAI.fromEnvironment().DEFAULT_SERIALIZATION,
        scope = this
      )

    val sourceCode =
      """
       import java.util.*

       class ShoppingCart {
           private val items: MutableList<String> = mutableListOf()

           fun addItem(item: String) {
               items.add(item)
           }

           fun removeItem(item: String) {
               items.remove(item)
           }

           fun getTotalItems(): Int {
               return items.size
           }

           fun calculateTotalPrice(): Double {
               var totalPrice = 0.0
               for (item in items) {
                   val price = fetchItemPrice(item)
                   totalPrice += price
               }
               return totalPrice
           }

           private fun fetchItemPrice(item: String): Double {
               // Logic to fetch item price from database or API
               // For simplicity, return a random price
               return Random().nextDouble() * 100
           }
       }

       fun main() {
           val cart = ShoppingCart()
           cart.addItem("Item 1")
           cart.addItem("Item 2")
           cart.addItem("Item 3")

           println("Total items in cart: ${'$'}{cart.getTotalItems()}")
           println("Total price of items in cart: ${'$'}{cart.calculateTotalPrice()}")

           cart.removeItem("Item 2")

           println("Total items in cart: ${'$'}{cart.getTotalItems()}")
           println("Total price of items in cart: ${'$'}{cart.calculateTotalPrice()}")
       }

    """
        .trimIndent()

    val antiPatternDetectionResult = code.antiPatternDetection(sourceCode)
    println("Detected Anti-Patterns:")
    println(antiPatternDetectionResult)

    val codeBreakdownResult = code.codeBreakdown(sourceCode)
    println("Code Breakdown:")
    println(codeBreakdownResult)

    val codeDocumentationGenerationResult = code.coreDocumentationGeneration(sourceCode)
    println("Code Documentation Generation:")
    println(codeDocumentationGenerationResult)
  }
}
