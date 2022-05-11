import java.util.ArrayList;

public class Room {
    private boolean catInside = false;
    private ArrayList<Mice> miceInRoom = new ArrayList<>();

    public synchronized boolean getCatInside () { return catInside;}
    public synchronized void setCatInside(boolean b){catInside = b;}
    public synchronized boolean getMiceInside(){return !miceInRoom.isEmpty();}
    public synchronized void setMiceInside(Mice mice){miceInRoom.add(mice);}

    public synchronized void miceGetIn(Mice mice){
        while(getCatInside()){
            try{
                wait();
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }

        }
        System.out.println("Mice "+ mice.getMiceID()+ " gets in the room!");
        miceInRoom.add(mice);
        setMiceInside(mice);
        notifyAll();
        System.out.println("Mice "+ mice.getMiceID()+ " is eating!");
        try{wait(3000);}
        catch(InterruptedException ex){
            ex.printStackTrace();
        }
       if(mice.getAlive()){
            System.out.println("Mice "+ mice.getMiceID()+ " gets out of the room!");
            miceInRoom.remove(mice);
        }
        else{
            System.out.println("Mice "+ mice.getMiceID()+ " was eaten!");
        }
    }

    public synchronized void catGetIn(Cat cat){
        while(!getMiceInside() || getCatInside()){
            try{
                wait();
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }

        }
        System.out.println("Cat "+ cat.getCatID()+ " gets in the room!");
        setCatInside(true);
        try{
            wait(2000);
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }
        if(miceInRoom.size()!=0){
            miceInRoom.get(0).getKilled();
            System.out.println("Cat "+ cat.getCatID()+ " ate the mice " + miceInRoom.get(0).getMiceID());
        }

        System.out.println("Cat "+ cat.getCatID()+ " gets out of the room!");
        setCatInside(false);
        notifyAll();
    }


}
