import java.util.Scanner;

public class Simulation {
    public static void main(String[] args) {

        int width = 0, height = 0;
        boolean startGrid = true;
        Scanner input = new Scanner(System.in);


        while (startGrid) {

                /*
                Initailizing the grid of size requested by user
                */

            System.out.println("Welcome to GAME OF LIFE\n");
            System.out.println("Enter the grid height: ");
            boolean heightIsSet = false;
            while (!heightIsSet) {
                String value = input.next();
                try {
                    height = Integer.parseInt(value);
                    if (height >= 3) {
                        heightIsSet = true;
                    } else
                        System.out.println("Enter a number greater than 3 for height");


                } catch (Exception E) {
                    System.out.println("Not a number! Enter a valid number for height of the grid: ");
                }
            }
            System.out.println("Enter the grid width:");
            boolean widthIsSet = false;
            while (!widthIsSet) {
                String value = input.next();
                try {
                    width = Integer.parseInt(value);
                    if (width >= 3) {
                        widthIsSet = true;
                    } else
                        System.out.println("Enter a number greater than 3 for width");

                } catch (Exception E) {
                    System.out.println("Not a number! Enter a valid number for width of grid: ");
                }
            }

            Grid active = new Grid(width, height);


            /*
            code to accept a valid input for naming mode.
             */
            boolean named = false;
            System.out.println("How would you like to name cells \n1. User Input \n2. Default names\nEnter 1 or 2:");
            while (!named) {
                String namingMode = input.next();
                try {
                    int naming = Integer.parseInt(namingMode);
                    switch (naming) {
                        case 1: {
                            active.populate();
                            named = true;
                            break;
                        }
                        case 2: {
                            active.populateClone();
                            named = true;
                            break;
                        }
                        default: {
                            System.out.println("Not an Option! Enter a valid option!");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Not an integer ! Enter a valid integer!");
                }
            }
            active.tick();
            active.printGrid();
            boolean menu = true;
            int tickCount = 1;


            while (menu) {
                System.out.println("Tick " + tickCount + "\nMenu \n Choose an option below\n1. Display Grid\n2. Find cell by name" +
                        "\n3. Proceed to next tick" + "\n4. Reset Grid\n5. Exit");
                String menuChoice = input.next();
                try {
                    int menuOption = Integer.parseInt(menuChoice);
                    switch (menuOption) {
                        case 1: {
                            active.printGrid();
                            break;
                        }
                        case 2: {
                            System.out.println("Input cell name:");
                            boolean nameValid = false;
                            while (!nameValid) {
                                String cellName = input.next();
                                try {
                                    System.out.println("Cell " + cellName + " is " + (active.getByName(cellName).isAlive() ? "Alive" : "Dead") + "\n");
                                    nameValid = true;
                                } catch (Exception E) {
                                    System.out.println("Not a cell name! Enter a valid name:");
                                }

                            }
                            break;
                        }
                        case 3: {
                            active.tick();
                            active.printGrid();
                            menu = true;
                            tickCount += 1;
                            break;
                        }

                        case 4: {
                            System.out.println("Resetting Grid... \n\n\n\n\n");
                            startGrid = true;
                            menu = false;
                            break;
                        }

                        case 5: {
                            System.out.println("Closing Game of Life");
                            startGrid = false;
                            menu = false;
                            break;
                        }
                        default: {
                            System.out.println("Not an option! Enter a valid option:");
                        }
                    }
                } catch (Exception E) {
                    System.out.println("Not an integer! Enter a valid integer:");
                }
            }
        }
    }
}