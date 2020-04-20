package machine

import java.util.*
import kotlin.system.exitProcess

fun main() {

    val scanner = Scanner(System.`in`)

    /*println("Starting to make a coffee")
    println("Grinding coffee beans")
    println("Boiling water")
    println("Mixing boiled water with crushed coffee beans")
    println("Pouring coffee into the cup")
    println("Pouring some milk into the cup")
    println("Coffee is ready!")*/

    //user interaction
    val myMachine = CoffeeMachine()

    while (true) {
        when(myMachine.state) {
            CoffeeMachine.MachineState.ACTION -> {
                print("Write action (buy, fill, take, remaining, exit): > ")
                myMachine.UserInteraction(scanner.nextLine())
            }
            CoffeeMachine.MachineState.VARIANT -> {
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: >")
                myMachine.UserInteraction(scanner.nextLine())
            }
            CoffeeMachine.MachineState.REFILL -> {
                print("Write how many ml of water do you want to add: > ")
                myMachine.water += scanner.nextInt()
                print("Write how many ml of milk do you want to add: > ")
                myMachine.milk += scanner.nextInt()
                print("Write how many grams of coffee beans do you want to add: > ")
                myMachine.gramsOfBeans += scanner.nextInt()
                print("Write how many disposable cups of coffee do you want to add: > ")
                myMachine.cupsForCoffee += scanner.nextInt()
                println()
                myMachine.state = CoffeeMachine.MachineState.ACTION
            }
        }

    }




}


class CoffeeMachine(var state: MachineState = MachineState.ACTION) {
    //initial state of the coffee machine
    var water = 400
    var milk = 540
    var gramsOfBeans = 120
    var cupsForCoffee = 9
    var money = 550

    fun showState(): MachineState {
        state = MachineState.REFILL
        return state
    }

    fun coffeeMachineInfo() {
        println("The coffee machine has:")
        println("$water of water")
        println("$milk of milk")
        println("$gramsOfBeans of coffee beans")
        println("$cupsForCoffee of disposable cups")
        println("$$money of money")
        println()
    }

    fun coffeeMakingAbility() : Boolean {
        return if (water >= 200 && milk >= 50 && gramsOfBeans >= 15) {
            println("I have enough resources, making you a coffee!")
            true
        } else if (water < 200){
            println("Sorry, not enough water!")
            false
        } else if (milk < 50){
            println("Sorry, not enough milk!")
            false
        } else if (gramsOfBeans < 15){
            println("Sorry, not enough coffee beans!")
            false
        } else {
            false
        }
    }

    fun buyCoffee(coffeVariant: String) {
        when(coffeVariant) {
            "1" -> {
                if (coffeeMakingAbility()) {
                    water -= 250
                    gramsOfBeans -= 16
                    cupsForCoffee --
                    money += 4
                }
            }
            "2" -> {
                if (coffeeMakingAbility()) {
                    water -= 350
                    milk -= 75
                    gramsOfBeans -= 20
                    cupsForCoffee --
                    money +=7
                }

            }
            "3" -> {
                if (coffeeMakingAbility()) {
                    water -= 200
                    milk -= 100
                    gramsOfBeans -= 12
                    cupsForCoffee --
                    money += 6
                }
            }
            "back" -> println()
        }
    }

    fun withdrawMoney() {
        println("I gave you $$money")
        money = 0
        println()
    }

    enum class MachineState() {
        ACTION,
        VARIANT,
        REFILL;
    }

    fun UserInteraction(userInput: String) {

        when (userInput) {
            "buy" -> {
                state = MachineState.VARIANT
            }
            "fill" -> {
                state = MachineState.REFILL
            }
            "take" -> {
                state = MachineState.ACTION
                println("I gave you $$money")
                money = 0
                println()
            }
            "remaining" -> {
                state = MachineState.ACTION
                coffeeMachineInfo()
            }
            "exit" -> {
                exitProcess(1)
            }
            "1" -> {
                state = MachineState.ACTION
                buyCoffee("1")
            }
            "2" -> {
                state = MachineState.ACTION
                buyCoffee("2")

            }
            "3" -> {
                state = MachineState.ACTION
                buyCoffee("3")
            }
            "back" -> {
                state = MachineState.ACTION
                buyCoffee("back")
            }
        }
    }
}
