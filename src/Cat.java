public class Cat extends Thread{

    boolean alive = true;
    boolean game = true;
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
        long startTime = System.currentTimeMillis();
        long time = 0;
        boolean hasEaten = false;
        while(alive && game){
            if(room.catGetIn(this)) {

                try {
                    sleep(k*1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                hasEaten = room.catEating(this);

            }

            else{
                game=false;
            }

            if(hasEaten){
                time = 0;
                startTime = System.currentTimeMillis();
            }

            else{
                time = System.currentTimeMillis()-startTime;
                if(time > m*1000){
                    alive = false;
                }
            }
        }
        if(!alive){
            System.out.println("cat " + getCatID() + " has died from hunger!");
            room.catDied();
        }
    }
}
