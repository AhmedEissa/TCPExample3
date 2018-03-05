package tcpexample3;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author Matthew
 */
class TCPServer {

    public static void main(String argv[]) throws IOException, ClassNotFoundException {
        System.out.println("Server Ready!");
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            boolean closeConnection = true;
            ObjectInputStream inStream = new ObjectInputStream(connectionSocket.getInputStream());
            ObjectOutputStream outStream = new ObjectOutputStream(connectionSocket.getOutputStream());

            while (closeConnection) {

                TextProtocol clientMessage = (TextProtocol) inStream.readObject();
                switch (clientMessage.getHeader()) {
                    case "saveMessage":
                        writeToFile(clientMessage.getMessage());
                        outStream.writeObject(new TextProtocol("textSaved"));
                        System.out.println("Message saved to a file");
                        break;
                    case "readFile":
                        ArrayList<String> fileData = readFromFile();
                        outStream.writeObject(new TextProtocol("textFile", fileData));
                        System.out.println("File read and sent to client.");
                        break;
                    case "closeConnection":
                        connectionSocket.close();
                        closeConnection = false;
                        break;
                }
            }
        }
    }

    public static void writeToFile(String textFromClient) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("resources/myfile.txt", true));
        writer.write(textFromClient + "\n");
        writer.close();
        System.out.println("Message " + textFromClient + " Saved!");
    }

    public static ArrayList<String> readFromFile() throws IOException {
        ArrayList<String> fileText = new ArrayList<>();
        String line;
        try (BufferedReader bufferedReader
                = new BufferedReader(
                        new FileReader("resources/myfile.txt"))) {
            while ((line = bufferedReader.readLine()) != null) {
                fileText.add(line);
            }
        }
        return fileText;
    }
}
