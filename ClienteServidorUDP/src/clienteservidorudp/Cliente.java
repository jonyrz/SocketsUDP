package ClienteServidorUDP;

import java.io.*;  
import java.net.*; 

public class Cliente{
    public static void main(String[] args) throws Exception {
        try{
            DatagramSocket socketUDP = new DatagramSocket();
            String sms = "Este es el mensaje del cliente";
            byte[] mensaje = sms.getBytes();
            InetAddress hostServidor = InetAddress.getByName("127.0.0.1");
            int puertoServidor = 2500;
            //Construimos un datagrama para enviar el mensaje al servidor
            DatagramPacket dataG = new DatagramPacket(mensaje, mensaje.length, hostServidor, puertoServidor);
            socketUDP.send(dataG); //Enviamos el datagrama
            byte[] buffer = new byte[3000];
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(respuesta);
            System.out.println("Respuesta recibida en cliente: "+new String(respuesta.getData()));
            socketUDP.close();
        }catch (SocketException e){
            System.out.println("Socket: "+e.getMessage());
        }catch(IOException e){
            System.out.println("IO: "+e.getMessage());
        }
    }
}