public class Mouse extends Thread {
    boolean mouseAlive = true;
    boolean game= true;
    static int i = 1;
    int n;
    private int mouseID;
    Room room = null;



    public synchronized boolean getAlive(){
        return mouseAlive;
    }

    public synchronized void getKilled(){
        mouseAlive = false;
    }

    public int getMouseID() {
        return mouseID;
    }

    public Mouse(Room room, int n){
        this.n = n;
        this.room =room;
        mouseID = i++;
    }
    @Override
    public void run() {
        while(getAlive() && game){

            if(room.mouseGetIn(this)){
                System.out.println("mouse "+ getMouseID()+ " is eating!");
                try{
                    sleep(n*1000);
                }
                catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                room.mouseGetOut(this);
            }
            else{
                game =false;
            }
        }

    }
}
