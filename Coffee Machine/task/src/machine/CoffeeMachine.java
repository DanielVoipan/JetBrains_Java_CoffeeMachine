package machine;

import java.util.Objects;
import java.util.Scanner;

public class CoffeeMachine {

    private int currentWater;
    private int currentMilk;
    private int currentCoffee;
    private int money;
    private int disposableCups;

    // CoffeeMachine constructor with default values
    CoffeeMachine() {
        this.currentCoffee = 120;
        this.currentMilk = 540;
        this.currentWater = 400;
        this.money = 550;
        this.disposableCups = 9;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine();
        boolean stop = false;
        while (!stop) {
        System.out.println("Write action (buy, fill, take, remaining, exit)");
        String choose = scanner.next();
            switch (choose.toLowerCase()) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                String word = scanner.next();
                if ("back".equalsIgnoreCase(word)) {
                    break;
                }
                String check = machine.buyCoffee(Integer.valueOf(word));
                if (Objects.equals(check,null)) {
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.printf("Sorry, not enough %s!\n", check);
                }
                break;
            case "fill":
                System.out.println("Write how many ml of water you want to add:");
                int waterToAdd = scanner.nextInt();
                System.out.println("Write how many ml of milk you want to add: ");
                int milkToAdd = scanner.nextInt();
                System.out.println("Write how many grams of coffee beans you want to add: ");
                int coffeeToAdd = scanner.nextInt();
                System.out.println("Write how many disposable cups you want to add:");
                int disposableToAdd = scanner.nextInt();
                machine.fillCoffeeMachine(waterToAdd, milkToAdd, coffeeToAdd, disposableToAdd);
                break;
            case "take":
                System.out.printf("I gave you $%d\n", machine.getMoney());
                machine.setMoney(machine.getMoney(), 0);
                System.out.println();
                break;
            case "exit":
                stop = true;
                break;
            case "remaining":
                outputCoffeMachineStatus(machine);
                break;
            default:
                System.out.println("Invalid option");
                break;
            }
        }
    }

    // output CoffeeMachine status with get's methods
    static void outputCoffeMachineStatus(CoffeeMachine machine) {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", machine.getCurrentWater());
        System.out.printf("%d ml of milk\n", machine.getCurrentMilk());
        System.out.printf("%d g of coffee beans\n", machine.getCurrentCoffee());
        System.out.printf("%d disposable cups\n", machine.getDisposableCups());
        System.out.printf("$%d of money\n", machine.getMoney());
    }

    // add values to CoffeeMachine
    void fillCoffeeMachine(int waterToAdd, int milkToAdd, int coffeeToAdd, int disposableToAdd) {
        this.setCurrentCoffee(coffeeToAdd, 1);
        this.setCurrentMilk(milkToAdd, 1);
        this.setCurrentWater(waterToAdd, 1);
        this.setDisposableCups(disposableToAdd, 1);
    }
    static String checkIfEnoughToBuy (CoffeeMachine machine, int coffeePerCup, int waterPerCup, int milkPerCup, int disposableCups) {
        StringBuilder str = new StringBuilder();
        if (disposableCups < 1) {
            str.append("disposableCups ");
        }
        if (machine.getCurrentCoffee() < coffeePerCup) {
            str.append("coffee ");
        }
        if (machine.getCurrentMilk() < milkPerCup) {
            str.append("milk ");
        }
        if (machine.getCurrentWater() < waterPerCup) {
            str.append("water ");
        }
        if (str.length() == 0) {
            return null;
        } else {
            String[] s = str.toString().split(" ");
            String output = "";
            for (int i = 0; i < s.length; i++) {
                if (i < s.length - 1) {
                    output += s[i] + ", ";
                } else {
                    output += s[i];
                }
            }
            return output;
        }
    }
    // buy 1 coffee
    String buyCoffee(int type) {
        String check;
        switch (type) {
            case 1:
                Espresso espresso = new Espresso();
                check = checkIfEnoughToBuy(this, espresso.coffeePerCup, espresso.waterPerCup, 0, this.disposableCups);
                if (!Objects.equals(check, null)) {
                    return check;
                }
                this.setCurrentCoffee(espresso.coffeePerCup, 0);
                this.setCurrentWater(espresso.waterPerCup, 0);
                this.setMoney(espresso.price, 1);
                break;
            case 2:
                Latte latte = new Latte();
                check = checkIfEnoughToBuy(this, latte.coffeePerCup, latte.waterPerCup, latte.waterPerCup, this.disposableCups);
                if (!Objects.equals(check, null)) {
                    return check;
                }
                this.setCurrentCoffee(latte.coffeePerCup, 0);
                this.setCurrentWater(latte.waterPerCup, 0);
                this.setCurrentMilk(latte.milkPerCup, 0);
                this.setMoney(latte.price, 1);
                break;
            case 3:
                Cappucino cappucino = new Cappucino();
                check = checkIfEnoughToBuy(this, cappucino.coffeePerCup, cappucino.waterPerCup, cappucino.waterPerCup, this.disposableCups);
                if (!Objects.equals(check, null)) {
                    return check;
                }
                this.setCurrentCoffee(cappucino.coffeePerCup, 0);
                this.setCurrentMilk(cappucino.milkPerCup, 0);
                this.setCurrentWater(cappucino.waterPerCup, 0);
                this.setMoney(cappucino.price, 1);
                break;
        }
        this.setDisposableCups(1,0);
        return null;
    }

    // get's for CoffeMAchine DATA retrieve
    int getCurrentWater() {
        return currentWater;
    }
    int getCurrentMilk() {
        return currentMilk;
    }

    int getCurrentCoffee() {
        return currentCoffee;
    }
    int getMoney() {
        return money;
    }
    int getDisposableCups() {
        return disposableCups;
    }

    // set's, if action == 0, decrease by value, else sum by value
    void setCurrentCoffee(int value, int action) {
        this.currentCoffee = action == 0 ? this.currentCoffee - value : this.currentCoffee + value;
    }

    void setCurrentWater(int value, int action) {
        this.currentWater = action == 0 ? this.currentWater - value : this.currentWater + value;
    }

    void setCurrentMilk(int value, int action) {
        this.currentMilk = action == 0 ? this.currentMilk - value : this.currentMilk + value;
    }

    void setMoney(int value, int action) {
        this.money = action == 0 ? this.money - value : this.money + value;
    }

    void setDisposableCups(int value, int action) {
        this.disposableCups = action == 0 ? this.disposableCups - value : this.disposableCups + value;
    }

    // base classe for Coffee
    class Coffee {
        int waterPerCup;
        int milkPerCup;
        int coffeePerCup;
        int price = 0;

        public Coffee() {
            waterPerCup = 0;
            milkPerCup = 0;
            coffeePerCup = 0;
            price = 0;
        }
    }

    // latte extends base coffee
    class Latte extends Coffee {

        public Latte() {
            super();
            this.waterPerCup = 350;
            this.milkPerCup = 75;
            this.coffeePerCup = 20;
            this.price = 7;
        }
    }

    // espresso extends base coffee
    class Espresso extends Coffee {
        public Espresso() {
            super();
            this.waterPerCup = 250;
            this.coffeePerCup = 16;
            this.price = 4;
        }
    }

    class Cappucino extends Coffee {

        public Cappucino() {
            super();
            this.waterPerCup = 200;
            this.milkPerCup = 100;
            this.coffeePerCup = 12;
            this.price = 6;
        }
    }
}
