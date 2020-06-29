package clienteservidorudp;

import java.io.IOException;
import java.net.*; 

public class Servidor{
    public static void main(String[] args) throws Exception {
        try{
            DatagramSocket socketUDP = new DatagramSocket(2500);
            String r = "Mensaje recibido gracias por contactarnos ";
            byte[] bufer = new byte[3000];
            while(true){
                //Construimos el DatagramPacket para recibir peticiones
                DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
                socketUDP.receive(peticion); //Leemos una peticion del DatagramSocket
                System.out.println("Datagrama recibido del host: " + peticion.getAddress());
                System.out.println("Puerto remoto: " + peticion.getPort());
                r+= "su mensaje fue: " + new String(peticion.getData());
                System.out.println("mensaje recibido: " + new String(peticion.getData()));
                byte[] resp = r.getBytes();
                DatagramPacket respuesta = new DatagramPacket(resp, resp.length, 
                        peticion.getAddress(), peticion.getPort());
                socketUDP.send(respuesta); //Enviamos la respuesta
            }
        }catch(SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }catch(IOException e){
            System.out.println("IO: " + e.getMessage());
        }
    }
}


