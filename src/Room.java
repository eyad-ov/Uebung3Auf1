import java.util.ArrayList;

public class Room {
    int catsNum;
    int miceNum;

    private boolean catInside = false;
    private ArrayList<Mouse> miceInRoom = new ArrayList<>();

    public Room(int n, int m){
        catsNum =n;
        miceNum =m;
    }

    public synchronized void catDied(){
        catsNum--;
    }

    public synchronized void mouseDied(){
        miceNum--;
    }

    public synchronized boolean isGameOver(){
        if(catsNum == 0 || miceNum ==0){
            return true;
        }
        return false;
    }

    public synchronized boolean getCatInside () {
        return catInside;
    }

    public synchronized void setCatInside(boolean b){
        catInside = b;
    }

    public synchronized boolean getMiceInside(){
        return !miceInRoom.isEmpty();
    }

    public synchronized void setMouseInside(Mouse mouse){
        miceInRoom.add(mouse);
    }

    public synchronized void setMouseOutside(Mouse mouse){
        miceInRoom.remove(mouse);
    }

    public synchronized boolean mouseGetIn(Mouse mouse){
        if(isGameOver()){
            notifyAll();
            return false;
        }
        while(getCatInside()){
            try{
                wait();
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }

        }
        System.out.println("mouse "+ mouse.getMouseID()+ " gets in the room!");
        setMouseInside(mouse);
        notifyAll();
        return true;

    }
    public synchronized void mouseGetOut(Mouse mouse){
        if(mouse.getAlive()){
            System.out.println("mouse "+ mouse.getMouseID()+ " gets out of the room!");
            setMouseOutside(mouse);
        }
    }

    public synchronized boolean catGetIn(Cat cat){

        while(!isGameOver() && (!getMiceInside() || getCatInside())){
            try{
                wait();
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }

        }
        if(!isGameOver()){
            System.out.println("Cat "+ cat.getCatID()+ " gets in the room!");
            setCatInside(true);
            return true;
        }
        notifyAll();
        return false;

    }

    public synchronized void catEating(Cat cat) {
        if(getMiceInside()){
            System.out.println("Cat "+ cat.getCatID()+ " ate the mouse " + miceInRoom.get(0).getMouseID());
            miceInRoom.get(0).getKilled();
            setMouseOutside(miceInRoom.get(0));
            mouseDied();

        }
        System.out.println("Cat "+ cat.getCatID()+ " gets out of the room!");
        setCatInside(false);
        notifyAll();
    }

}
