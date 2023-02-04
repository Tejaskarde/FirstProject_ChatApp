import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.io.*;

class Server {

      ServerSocket server;
      Socket socket;
      BufferedReader br;
      PrintWriter out;



    public Server(){
          
        try{
      server=new ServerSocket(7777);
      System.out.println("server is ready to accept connection");
      System.out.println("waiting...");
      socket=server.accept();
          
      br=new BufferedReader(new InputStreamReader(socket.
      getInputStream()));
      
      out=new PrintWriter(socket.getOutputStream());
       
      startReading();
      startWriting();
    
    }catch(Exception e){
            e.printStackTrace();
        }
    }


public void startReading(){
    // thread - read karke deta rahenga

Runnable r1=()->{
System.out.println("reader started..");
try{
while(true)
{
    
    String msg=br.readLine();
      if(msg.equals("exit"))
      {
        System.out.println("Client terminated the chat");
        socket.close(); 
        
        break;  
    } 

    System.out.println("Client : "+msg);
} 
}catch(Exception e){
    e.printStackTrace();
}


};


new Thread(r1).start();


}
public void startWriting(){
// thread - data user lega and the send karenga client tak;
     Runnable r2=()->{

        System.out.println("writer started...");
     
try{

     while(true && !socket.isClosed())
     {
        
            
            BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));

            String content =br1.readLine();

            out.println(content);
            out.flush();

            if(content.equals("exit")){
                socket.close();
                break;
              }

        }
        

     }catch(Exception e){
        e.printStackTrace();
     }

     } ;

new Thread(r2).start();

 
}

    public static void main(String[] args) {
       
        System.out.println(" i am server");
        new Server();
    }
}
