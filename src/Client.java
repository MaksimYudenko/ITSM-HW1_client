import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private static String[] arguments;
    //  private Map<String, String> messages = new HashMap<>();
    private static final String NAME = "Max";

    private Client() {}

    public static void main(String args[]) {
        arguments = args;
        if (args.length == 0) {
            System.out.println("Send a message to the server");
            System.exit(1);
        } else new Client().run();
    }

    private void run() {
        try {
            clientSocket = new Socket("localhost", 1234);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());

            StringBuilder msg = new StringBuilder();
            for (String s : arguments) msg.append(s).append(" ");
            String message = "{\n\"name\": \"" + NAME + "\", \n\"message\": " + "\"" + msg.toString().trim() + "\"\n}";
            //using Gson:
            /*
            messages.put("name", msg);
            messages.put("message", "Hello, server");
            Gson gson = new Gson();
            String clientGson = gson.toJson(messages);
            out.writeObject(clientGson);*/
            out.writeObject(message);
            out.flush();
            System.out.println((String) in.readObject());
        } catch (RuntimeException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

}