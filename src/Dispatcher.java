import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Dispatcher implements Runnable {

    private static Socket connection;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private static Scanner scanner;

    public static void main(String[] args) {
        new Thread(new Dispatcher()).start();
    }

    @Override
    public void run() {
        scanner = new Scanner(System.in);

        try {

            while(true) {
//                Next line means connection to this computer
                connection = new Socket(InetAddress.getByName("127.0.0.1"), 6666);

//                TODO: Connection by external IP
//                connection = new Socket(InetAddress.getByName("10.1.1.104"), 6666);

                output = new ObjectOutputStream(connection.getOutputStream());
                output.flush();
                input = new ObjectInputStream(connection.getInputStream());


                sendData(scanner.nextLine());


                System.out.println(input.readObject());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void sendData(Object object)  {
        try {
            output.flush();
            output.writeObject(object);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
