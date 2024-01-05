import com.fazecast.jSerialComm.SerialPort;
import java.io.*;

public class ArduinoCommunication {
    private static final int BAUD_RATE = 9900;
    private static final String PORT_NAME = "COM3";

    public static void main(String[] args) {
        SerialPort serialPort = SerialPort.getCommPort(PORT_NAME);
        serialPort.setBaudRate(BAUD_RATE);

        if (serialPort.openPort()) {
            System.out.println("Port opened successfully.");
            try {
                String messageToSend = "nazariiPastukh.ki47";
                OutputStream output = serialPort.getOutputStream();
                output.write(messageToSend.getBytes());
                output.flush();
                System.out.println("Sent:" + messageToSend);

                InputStream input = serialPort.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String receivedMessage = reader.readLine();
                System.out.println("Received:" + receivedMessage);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error opening the port: " + e.getMessage());
            } finally {
                serialPort.closePort();
                System.out.println("Port closed.");
            }
        } else {
            System.err.println("Error opening the port.");
        }
    }
}