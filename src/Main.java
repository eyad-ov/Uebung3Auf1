public class Main {
    public static void main(String[] args){
        Room room = new Room(5,5);
        try{
            int n = Integer.parseInt(args[0]);
            int k = Integer.parseInt(args[1]);
            int m = Integer.parseInt(args[2]);
            for(int i=0;i<5;i++){
                new Cat(room,k,m).start();
                new Mouse(room,n).start();
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
