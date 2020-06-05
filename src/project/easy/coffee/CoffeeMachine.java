package project.easy.coffee;

import java.util.Scanner;

public class CoffeeMachine{

    private int water;
    private int milk;
    private int coffee;
    private int cup;
    private int money;
    private Scanner scanner;

    public CoffeeMachine(int water, int milk, int coffee, int cup, int money){
        scanner = new Scanner(System.in);
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cup = cup;
        this.money = money;
    }

    public void processAction(){
        String action = "";
        while (!action.equals("exit")) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = scanner.next();
            switch (action) {
                case "buy":
                    buy();
                    break;

                case "fill":
                    fill();
                    break;

                case "take":
                    money = take();
                    break;

                case "remaining":
                    remaining();
                    break;

                case "exit":
                	System.out.println("Have a good day!");
                    break;

                default:
                    break;
            }
        }
    }

    public void buy(){
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu::");
        String option = scanner.next();
        switch (option) {
            case "1":
                buyProcess(250, 0, 16, 4);
                break;

            case "2":
                buyProcess(350, 75, 20, 7);
                break;

            case "3":
                buyProcess(200, 100, 12, 6);
                break;

            case "back":
                break;

            default:
                break;
        }
    }

    public void buyProcess(int waterNeed, int milkNeed, int coffeeNeed, int moneyGet){
        if (water < waterNeed) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < milkNeed) {
            System.out.println("Sorry, not enough milk!");
        } else if (coffee < coffeeNeed) {
            System.out.println("Sorry, not enough coffee!");
        } else if (cup < 1) {
            System.out.println("Sorry, not enough cup!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= waterNeed;
            milk -= milkNeed;
            coffee -= coffeeNeed;
            money += moneyGet;
            cup -= 1;
        }
    }

    public void fill(){
        System.out.println("Write how many ml of water do you want to add:");
        int add = scanner.nextInt();
        water += add;
        System.out.println("Write how many ml of milk do you want to add:");
        add = scanner.nextInt();
        milk += add;
        System.out.println("Write how many grams of coffee beans do you want to add:");
        add = scanner.nextInt();
        coffee += add;
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        add = scanner.nextInt();
        cup += add;
    }

    public void remaining() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffee + " of coffee beans");
        System.out.println(cup + " of disposable cups");
        if (money == 0) {
            System.out.println(money + " of money");
        } else {
            System.out.println("$" + money + " of money");
        }
    }

    public int take() {
        System.out.println("I gave you $" + money);
        return money = 0;
    }

}
