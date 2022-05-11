public class Mice extends Thread {
    boolean alive = true;
    static int i = 1;
    private int miceID;
    Room room = null;

    public boolean getAlive(){
        return alive;
    }

    public void getKilled(){
        alive = false;
    }
    public int getMiceID() {
        return miceID;
    }

    public Mice(Room room){
        this.room =room;
        miceID = i++;
    }
    @Override
    public void run() {
        while(alive){
            room.miceGetIn(this);
            System.out.println("Mice "+ getMiceID()+ " is eating!");
            try{
                sleep(3000);
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
            room.miceGetOut(this);
        }
    }
}
