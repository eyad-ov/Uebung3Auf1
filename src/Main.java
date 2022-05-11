public class Main {
    public static void main(String[] args){
        Room room = new Room();
        for(int i=0;i<5;i++){
            new Cat(room).start();

        }
        for(int i=0;i<5;i++){
            new Mice(room).start();
        }
    }
}
