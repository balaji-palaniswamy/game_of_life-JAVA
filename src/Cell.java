public class Cell {
    private String name;
    private boolean isAlive;

    /*
    Constructor of Cell takes a String as input and sets the name of cell.
     */
    public Cell( String name) {
        this.name = name;
    }

    /*
    isAlive method returns a boolean "true" if cell is alive and "false" if cell is dead.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /*
    setAlive method sets the status of cell receiving boolean value as argument
     */
    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    /*
    getName method returns the name of cell as String.
     */
    public String getName() {
        return name;
    }

    /*
    setName method sets the name of the cell by receiving a String as input.
     */
    public void setName(String name) {
        this.name = name;
    }
}
