public class Main {
    public static void main(String[] args){
        Room room = new Room();
        for(int i=0;i<5;i++){
            new Cat(room).start();
            new Mice(room).start();
        }
    }
}
