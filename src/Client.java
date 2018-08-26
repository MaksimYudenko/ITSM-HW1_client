import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 54321;
    private static final String NAME = "Max";
    private Socket clientSocket;
    private static String[] arguments;

    private Client() {}

    public static void main(String args[]) {
        arguments = args;
        if (args.length == 0) {
            System.err.println("Send a message to the server");
            System.exit(1);
        } else new Client().run();
    }

    private void run() {
        try {
            clientSocket = new Socket(HOST, PORT);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            StringBuilder msg = new StringBuilder();
            for (String s : arguments) msg.append(s).append(" ");
            String message = "{\n\"name\": \"" + NAME + "\", \n\"message\": " + "\"" + msg.toString().trim() + "\"\n}";
            out.writeObject(message);
            out.flush();
            System.out.println((String) in.readObject());
        } catch (RuntimeException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {clientSocket.close();}
            catch (IOException ioe) {ioe.printStackTrace();}
        }
    }

}