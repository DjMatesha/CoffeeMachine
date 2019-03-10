package com.company

import java.util.Scanner

class Coffee(var water: Int = 400, var milk: Int = 540, var beans: Int = 120, var money: Int = 550, var cups: Int = 9) {
    operator fun minus(a: Coffee): Coffee {
        return Coffee(water - a.water, milk - a.milk, beans - a.beans, money + a.money, cups - a.cups)
    }

    operator fun compareTo(a: Coffee): Int {
        if (a.water > water) {
            println("Sorry, not enough water!")
            println()
            return -1
        }
        if (a.milk > milk) {
            println("Sorry, not enough milk!")
            println()
            return -1
        }
        if (a.beans > beans) {
            println("Sorry, not enough beans!")
            println()
            return -1
        }
        if (a.cups > cups) {
            println("Sorry, not enough cups!")
            println()
            return -1
        }
        println("I have enough resources, making you a coffee!")
        println()
        return 1
    }
}

fun printState(state: Coffee) {
    println()
    println("The coffee machine has:")
    println("${state.water} of water")
    println("${state.milk} of milk")
    println("${state.beans} of coffee beans")
    println("${state.cups} of disposable cups")
    println("\$${state.money} of money")
    println()
}

enum class Stage { Main, Buy, FillWater, FillMilk, FillBeans, FillCups }

object CoffeeMachine {
    var state = Coffee(400, 540, 120, 550, 9)
    var stage = Stage.Main

    init {
        print("Write action (buy, fill, take, remaining, exit): ")
    }

    val espresso = Coffee(250, 0, 16, 4, 1)
    val latte = Coffee(350, 75, 20, 7, 1)
    val cappuccino = Coffee(200, 100, 12, 6, 1)
    fun response(cmd: String): Boolean {
        when (stage) {
            Stage.Main -> {
                when (cmd) {
                    "remaining" -> {
                        printState(state)
                        print("Write action (buy, fill, take, remaining, exit): ")
                    }
                    "buy" -> {
                        println()
                        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                        stage = Stage.Buy
                    }
                    "fill" -> {
                        println()
                        print("Write how many ml of water do you want to add: ")
                        stage = Stage.FillWater
                    }
                    "take" -> {
                        println()
                        println("I gave you \$${state.money}")
                        println()
                        state.money = 0
                        print("Write action (buy, fill, take, remaining, exit): ")
                    }
                    "exit" -> return false
                    else -> print("Error command, try again: ")
                }
            }
            Stage.Buy -> {
                when (cmd) {
                    "1" -> if (state > espresso) state -= espresso
                    "2" -> if (state > latte) state -= latte
                    "3" -> if (state > cappuccino) state -= cappuccino
                }
                stage= Stage.Main
                print("Write action (buy, fill, take, remaining, exit): ")
            }
            Stage.FillWater ->{
                state.water += cmd.toInt()
                print("Write how many ml of milk do you want to add: ")
                stage = Stage.FillMilk
            }
            Stage.FillMilk ->{
                state.milk += cmd.toInt()
                print("Write how many grams of coffee beans do you want to add: ")
                stage = Stage.FillBeans
            }
            Stage.FillBeans ->{
                state.beans += cmd.toInt()
                print("Write how many disposable cups of coffee do you want to add: ")
                stage = Stage.FillCups
            }
            Stage.FillCups->{
                state.cups += cmd.toInt()
                stage = Stage.Main
                println()
                print("Write action (buy, fill, take, remaining, exit): ")
            }
        }
        return true
    }
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    while (CoffeeMachine.response(scanner.next())){}
}