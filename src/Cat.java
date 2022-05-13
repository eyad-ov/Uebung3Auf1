public class Cat extends Thread{

    boolean alive = true;
    boolean game = true;
    int time = 0;
    int dieTime = 10000;
    int k;
    int m;
    static int i = 1;
    private int catID;
    Room room = null;

    public int getCatID() {
        return catID;
    }

    public Cat(Room room,int k, int m){
        this.k = k;
        this.m = m;
        this.room =room;
        catID = i++;
    }
    @Override
    public void run() {
        while(alive && game){
            if(room.catGetIn(this)) {

                try {
                    sleep(k*1000); // add this everytime
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                room.catEating(this);// must know if cat has eaten a mouse
            }
            else{
                game=false;
            }
        }
        if(!alive){
            System.out.println("cat " + getCatID() + " has died from hunger!");
            room.catDied();
        }
    }
}
