import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Grid {
    int width;
    int height;
    Cell[][] grid ;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid= new Cell [width][height];
    }

    /*
    populate method is used to add new cells to the grid by accepting unique names from the user.
    If the name is not unique this methods prompts the user to enter a unique name.
     */
    public void populate(){
        Set<String> nameSet = new HashSet<>();
        Scanner inp = new Scanner(System.in);
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.println("Enter the name of cell "+ j+""+i);
                boolean notUniqueName = true;
                while(notUniqueName){
                    String name = inp.next();
                    if(!nameSet.contains(name)){
                        nameSet.add(name);
                        this.grid[j][i] = new Cell(name);
                        notUniqueName=false;
                        break;
                    }
                    else {
                        System.out.println("Name Already Exists! Enter a unique name:");
                    }
                }
                System.out.println("Enter the state of cell "+ this.grid[j][i].getName()+ ": \n1 for Alive\n0 for dead");
                boolean stateSet = false;
                while (!stateSet){
                    String input = inp.next();
                    try {
                        int state = Integer.parseInt(input);
                        if(state==0){
                            this.grid[j][i].setAlive(false);
                            stateSet = true;
                        }
                        else if(state==1){
                            this.grid[j][i].setAlive(true);
                            stateSet = true;
                        }
                        else
                            System.out.println("Choose a valid state");
                    }
                    catch (Exception E){
                        System.out.println("Enter a valid integer ");
                    }
                }
            }
        }
    }


    /*
    populate clone method fills the grid with names by default using number from "0" to size of cells
     */
    public void populateClone(){
        int count=0;
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                Cell one = new Cell( Integer.toString(count));
                this.grid[j][i] = one;
                count++;
            }
        }
    }

    /*
    setAlive method sets the cell as Alive.
     */
    public void setAlive(int x, int y){
        this.grid[x][y].setAlive(true);
    }

    /*
    setDead method sets the cell as Dead.
     */
    public void setDead(int x, int y){
        this.grid[x][y].setAlive(false);
    }

    /*
    printGrid method prints the Status of the cells in the grid.
    "*" denotes live cells.
    "." denotes dead cells.
     */
    public void printGrid() {
        System.out.println("----");
        for (int i = 0; i < this.height; i++) {
            String line = "|";
            for (int j = 0; j < this.width; j++) {
                if(grid[j][i].isAlive() )
                    line += "* ";
                else
                    line += ". ";
            }
            System.out.print(line+"|\n");
        }
        System.out.println("----");
    }

    /*
    countAliveNeighbours method returns the Integer value of number of Alive neighbour cells for
     the indices of cell given as arguments.
     */
    public int countAliveNeighbours(int x, int y){
        int count=0;

        count += isAlive(x-1,y-1);
        count += isAlive(x,y-1);
        count += isAlive(x+1,y-1);

        count += isAlive(x-1,y);
        count += isAlive(x+1,y);

        count += isAlive(x-1,y+1);
        count += isAlive(x,y+1);
        count += isAlive(x+1,y+1);
        return count;
    }

    /*
    This isAlive method is used by countAliveNeighbours method to check if the status of cell at
    given indices and returns "0" if the cell is 'dead' and "1" if the cell is 'alive'.
     */
    public int isAlive(int x, int y){
        if(x<0 || x >= width){
            return 0;
        }
        if(y<0 || y >= height){
            return 0;
        }
        if(this.grid[x][y].isAlive()){
            return 1;
        }
        else {
            return 0;
        }
    }

    /*
    cloneGrid method takes two grids (i.e 2D Cell Array ) as arguments and clones the
     data of cells in fromGrid to the cells of toGrid .
     */
    public void cloneGrid (Cell[][] fromGrid, Cell[][] toGrid){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                toGrid[x][y].setName(fromGrid[x][y].getName())  ;
                toGrid[x][y].setAlive(fromGrid[x][y].isAlive());
            }
        }
    }

    /*
    tick method represents the change of generation. This method is responsible for the transitions of
    'live' and 'dead' cells through each generation.
     */
    public void tick(){
        Grid copy = new Grid(width,height);
        copy.populateClone();
        cloneGrid(this.grid,copy.grid);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int aliveNeighbours = countAliveNeighbours(x,y);
                if(isAlive(x,y)==1){
                    if(aliveNeighbours<2){
                        copy.setDead(x, y);
                    }
                    else if(aliveNeighbours == 2 || aliveNeighbours ==3){
                        copy.setAlive(x, y);
                    }
                    else if(aliveNeighbours > 3){
                        copy.setDead(x, y);
                    }
                }
                else {
                    if (aliveNeighbours == 3) {
                        copy.setAlive(x, y);
                    }
                }
            }
        }
        cloneGrid(copy.grid, this.grid);
    }

    /*
    getByName method takes a String as input argument and returns the cell with given name.
     */
    public Cell getByName(String name){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(this.grid[x][y].getName().equals(name)){
                    return this.grid[x][y];
                }
            }
        }
        return null;
    }

}
