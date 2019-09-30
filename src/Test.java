public class Test {

    public static void main(String[] args) {


        ProgramThread programThread = new ProgramThread();

        Thread thread = new Thread(programThread);

        thread.start();
    }
}
