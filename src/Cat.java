public class Cat extends Thread{
    boolean alive = true;
    static int i = 1;
    private int catID;
    Room room = null;

    public int getCatID() {
        return catID;
    }

    public Cat(Room room){
        this.room =room;
        catID = i++;
    }
    @Override
    public void run() {
        while(alive){
            room.catGetIn(this);

        }
        System.out.println("cat " + getCatID() + " has died from hunger!");
    }
}