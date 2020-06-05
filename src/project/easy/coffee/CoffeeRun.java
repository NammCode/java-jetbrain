package project.easy.coffee;

public class CoffeeRun {

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
        machine.processAction();
    }
}
