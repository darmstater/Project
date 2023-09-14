package com.socket;

import com.ui.ChatFrame;
import java.io.*;
import java.net.*;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SocketClient implements Runnable{
    
    public int port;
    public String serverAddr;
    public Socket socket;
    public ChatFrame ui;
    public ObjectInputStream In;
    public ObjectOutputStream Out;
    public History hist;
    
    public int a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public Integer pulang,ulang;
    
    public SocketClient(ChatFrame frame) throws IOException{
        pulang=0;ulang=0;
        ui = frame; this.serverAddr = ui.serverAddr; this.port = ui.port;
        socket = new Socket(InetAddress.getByName(serverAddr), port);
            
        Out = new ObjectOutputStream(socket.getOutputStream());
        Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
        
        hist = ui.hist;
    }

    @Override
    public void run() {
        boolean keepRunning = true;
        while(keepRunning){
            try {
                Message msg = (Message) In.readObject();
                System.out.println("Incoming : "+msg.toString());
                
                if(msg.type.equals("message")){
                    if(msg.recipient.equals(ui.username)){
                        ui.jTextArea1.append("["+msg.sender +" > Me] : " + msg.content + "\n");
                    }
                    else{
                        String[] result = msg.content.split(" ");
                        if(result[0].equals("Mulai!")){
                            ulang=0;
                            pulang=0;
                            ui.jButton10.setEnabled(true);
                            ui.jButton12.setEnabled(true);
                            ui.jButton13.setEnabled(true);
                            ui.jButton18.setEnabled(true);
                        }
                        
                        else if(msg.content.equals("Finish")){
                            ui.jButton12.setEnabled(false);
                            ui.jButton10.setEnabled(false);
                            ui.jButton13.setEnabled(false);
                            ui.jButton18.setEnabled(false);
                        }
                        
                        
                        if(msg.content.equals("R")){
                        ulang=1;
                        }else{
                        pulang=1;
                        }
                        if((ulang==1)&&(pulang==1)){ 
                            ui.jButton11.setBounds(111, 180,73,23);
                            ui.jButton9.setBounds(111, 325,73,23);
                            ui.jButton10.setEnabled(true);
                            ui.jButton12.setEnabled(true);
                            ui.jButton13.setEnabled(true);
                            ui.jButton18.setEnabled(true);
                        }
                    
                        
                        else if((msg.sender.equals(ui.jTextField3.getText()))){
//                            //penghalang1atas
//                            if(ui.jButton11.getBounds().x>131 && ui.jButton11.getBounds().y < 165 && ui.jButton11.getBounds().x<201){
//                                ui.jButton10.setEnabled(false);
//                                a+=1;
//                            }else{
//                                ui.jButton10.setEnabled(true);
//                                a=0;
//                            }
//                            
//                            //penghalang2bwh
//                            if(ui.jButton11.getBounds().x>131 && ui.jButton11.getBounds().y > 203 && ui.jButton11.getBounds().x<201){
//                                ui.jButton10.setEnabled(false);
//                                a+=1;
//                            }else{
//                                ui.jButton10.setEnabled(true);
//                                a=0;
//                            }
//                            
//                            //penghalang3atas
//                            if(ui.jButton11.getBounds().x>196 && ui.jButton11.getBounds().y < 207 && ui.jButton11.getBounds().x<276){
//                                ui.jButton10.setEnabled(false);
//                                a+=1;
//                            }else{
//                                ui.jButton10.setEnabled(true);
//                                a=0;
//                            }
//                            
//                            //penghalang4bwh
//                            if(ui.jButton11.getBounds().x>226 && ui.jButton11.getBounds().y > 247 && ui.jButton11.getBounds().x<296){
//                                ui.jButton10.setEnabled(false);
//                                a+=1;
//                            }else{
//                                ui.jButton10.setEnabled(true);
//                                a=0;
//                            }
//                            
//                            //penghalang5bwh
//                            if(ui.jButton11.getBounds().x>291 && ui.jButton11.getBounds().y > 207 && ui.jButton11.getBounds().x<371){
//                                ui.jButton10.setEnabled(false);
//                                a+=1;
//                            }else{
//                                ui.jButton10.setEnabled(true);
//                                a=0;
//                            }

//penghalang1atas
                            if(ui.jButton11.getBounds().x>121 && ui.jButton11.getBounds().y < 165 && ui.jButton11.getBounds().x<201){
                                ui.jButton10.setEnabled(false);
                                a+=1;
                            }else{if(ui.jButton11.getBounds().x>121 && ui.jButton11.getBounds().y > 203 && ui.jButton11.getBounds().x<201){
                                ui.jButton10.setEnabled(false);
                                a+=1;
                            }else{
                                ui.jButton10.setEnabled(true);
                                a=0;
                            }}
                            
                            if(ui.jButton11.getBounds().x>178 && ui.jButton11.getBounds().y < 185 && ui.jButton11.getBounds().x<281){
                                ui.jButton10.setEnabled(false);
                                a+=1;
                            }if(ui.jButton11.getBounds().x>220 && ui.jButton11.getBounds().y > 205 && ui.jButton11.getBounds().x<296){
                                ui.jButton10.setEnabled(false);
                                a+=1;
                            }else{if(ui.jButton11.getBounds().x>285 && ui.jButton11.getBounds().y > 162 && ui.jButton11.getBounds().x<371){
                                ui.jButton10.setEnabled(false);
                                a+=1;
                            }else{
                                ui.jButton10.setEnabled(true);
                                a=0;
                            }}
//================================pembatas bawah=================================================;;;
                            if(ui.jButton11.getBounds().y>235){;
                                ui.jButton12.setEnabled(false);
                                d+=1;
                                
                            }
                            else{
                              ui.jButton12.setEnabled(true);
                              d=0;
                            }
                            
//================================pembatas atas=================================================;;;
                            if(ui.jButton11.getBounds().y<140){;
                                ui.jButton13.setEnabled(false);
                                c+=1;
                                
                            }
                            else{
                              ui.jButton13.setEnabled(true);
                              c=0;
                            }
                        }
                        
                        if(a==1)
                        {
                            a=2;
                            send(new Message("message", msg.sender, "Nabrak pengahalang oi", "All"));
                        }
                        else if(c==1)
                        {
                            c=2;
                            send(new Message("message", msg.sender, "Nabrak atas!", "All"));
                        }
                         else if(d==1)
                        {
                            d=2;
                            send(new Message("message", msg.sender, "Nabrak bawah!", "All"));
                        }
                        
                        String x = Integer.toString(ui.jButton11.getBounds().x);
                        String y = Integer.toString(ui.jButton11.getBounds().y);
                        String gabungan = "X = ".concat(x).concat(", Y = ").concat(y);
                        if((result[0].equals("kiri")) && (ui.jButton9.getBounds().y > 125) && (!msg.sender.equals(ui.jTextField3.getText()))){
                            ui.jButton9.setBounds(ui.jButton9.getBounds().x, ui.jButton9.getBounds().y-5, ui.jButton9.getWidth(), ui.jButton9.getHeight());
                        }
                        else if((result[0].equals("kiri")) && (ui.jButton11.getBounds().y > 125) && (msg.sender.equals(ui.jTextField3.getText()))){
                            ui.jButton11.setBounds(ui.jButton11.getBounds().x, ui.jButton11.getBounds().y-5, ui.jButton11.getWidth(), ui.jButton11.getHeight());
                            ui.jTextField7.setText(gabungan);
                        }
                        
                        else if((result[0].equals("maju")) && (ui.jButton9.getBounds().y > 125) && (!msg.sender.equals(ui.jTextField3.getText()))){
                            ui.jButton9.setBounds(ui.jButton9.getBounds().x+5, ui.jButton9.getBounds().y, ui.jButton9.getWidth(), ui.jButton9.getHeight());
                        }
                        else if((result[0].equals("maju")) && (ui.jButton11.getBounds().y > 125) && (msg.sender.equals(ui.jTextField3.getText()))){
                            ui.jButton11.setBounds(ui.jButton11.getBounds().x+5, ui.jButton11.getBounds().y, ui.jButton11.getWidth(), ui.jButton11.getHeight());
                            ui.jTextField7.setText(gabungan);
                        }
                        
                        else if((result[0].equals("mundur")) && (ui.jButton9.getBounds().y > 125) && (!msg.sender.equals(ui.jTextField3.getText()))){
                            ui.jButton9.setBounds(ui.jButton9.getBounds().x-5, ui.jButton9.getBounds().y, ui.jButton9.getWidth(), ui.jButton9.getHeight());
                        }
                        else if((result[0].equals("mundur")) && (ui.jButton11.getBounds().y > 125) && (msg.sender.equals(ui.jTextField3.getText()))){
                            ui.jButton11.setBounds(ui.jButton11.getBounds().x-5, ui.jButton11.getBounds().y, ui.jButton11.getWidth(), ui.jButton11.getHeight());
                            ui.jTextField7.setText(gabungan);
                        }
                        
                        else if((result[0].equals("kanan")) && (ui.jButton9.getBounds().y > 125) && (!msg.sender.equals(ui.jTextField3.getText()))){
                            ui.jButton9.setBounds(ui.jButton9.getBounds().x, ui.jButton9.getBounds().y+5, ui.jButton9.getWidth(), ui.jButton9.getHeight());
                        }
                        else if((result[0].equals("kanan")) && (ui.jButton11.getBounds().y > 125) && (msg.sender.equals(ui.jTextField3.getText()))){
                            ui.jButton11.setBounds(ui.jButton11.getBounds().x, ui.jButton11.getBounds().y+5, ui.jButton11.getWidth(), ui.jButton11.getHeight());
                            ui.jTextField7.setText(gabungan);
                        }
                        
                        if(msg.sender.equals(ui.jTextField3.getText()))
                        {
                                    ui.jTextField7.setText(gabungan);
                                    if(ui.jButton11.getBounds().x>=426)
                                    {
                                        send(new Message("message", msg.sender, "Finish", "All"));
                                        ui.jButton10.setEnabled(false);
                                        ui.jButton12.setEnabled(false);
                                        ui.jButton13.setEnabled(false);
                                        ui.jButton18.setEnabled(false);
                                    }
                        }
                        
                        if(result[0].equals("Finish")){
                            ui.jButton11.setBounds(111, 180,67,23);
                            ui.jButton9.setBounds(111, 325,73,23);
                            ui.jButton10.setEnabled(false);
                                        ui.jButton12.setEnabled(false);
                                        ui.jButton13.setEnabled(false);
                                        ui.jButton18.setEnabled(false);
                        }
                        
                        ui.jTextArea1.append("["+ msg.sender +" > "+ msg.recipient +"] : " + msg.content + "\n");
                    }
                                            
                    if(!msg.content.equals(".bye") && !msg.sender.equals(ui.username)){
                        String msgTime = (new Date()).toString();
                        
                        try{
                            hist.addMessage(msg, msgTime);
                            DefaultTableModel table = (DefaultTableModel) ui.historyFrame.jTable1.getModel();
                            table.addRow(new Object[]{msg.sender, msg.content, "Me", msgTime});
                        }
                        catch(Exception ex){}  
                    }
                }
                else if(msg.type.equals("login")){
                    if(msg.content.equals("TRUE")){
                        ui.jButton2.setEnabled(false); ui.jButton3.setEnabled(false); 
                        ui.jButton5.setEnabled(true);
                        ui.jTextArea1.append("[SERVER > Me] : Login Successful\n");
                        ui.jTextField3.setEnabled(false); ui.jPasswordField1.setEnabled(false);
                    }
                    else{
                        ui.jTextArea1.append("[SERVER > Me] : Login Failed\n");
                    }
                }
                else if(msg.type.equals("test")){
                    ui.jButton1.setEnabled(false);
                    ui.jButton2.setEnabled(true); ui.jButton3.setEnabled(true);
                    ui.jTextField3.setEnabled(true); ui.jPasswordField1.setEnabled(true);
                    ui.jTextField1.setEditable(false); ui.jTextField2.setEditable(false);
                    ui.jButton7.setEnabled(true);
                }
                else if(msg.type.equals("newuser")){
                    if(!msg.content.equals(ui.username)){
                        boolean exists = false;
                        for(int i = 0; i < ui.model.getSize(); i++){
                            if(ui.model.getElementAt(i).equals(msg.content)){
                                exists = true; break;
                            }
                        }
                        if(!exists){ ui.model.addElement(msg.content); }
                    }
                }
                else if(msg.type.equals("signup")){
                    if(msg.content.equals("TRUE")){
                        ui.jButton2.setEnabled(false); ui.jButton3.setEnabled(false);
                        ui.jButton4.setEnabled(true); ui.jButton5.setEnabled(true);
                        ui.jTextArea1.append("[SERVER > Me] : Singup Successful\n");
                    }
                    else{
                        ui.jTextArea1.append("[SERVER > Me] : Signup Failed\n");
                    }
                }
                else if(msg.type.equals("signout")){
                    if(msg.content.equals(ui.username)){
                        ui.jTextArea1.append("["+ msg.sender +" > Me] : Bye\n");
                        ui.jButton1.setEnabled(true); ui.jButton4.setEnabled(false); 
                        ui.jTextField1.setEditable(true); ui.jTextField2.setEditable(true);
                        
                        for(int i = 1; i < ui.model.size(); i++){
                            ui.model.removeElementAt(i);
                        }
                        
                        ui.clientThread.stop();
                    }
                    else{
                        ui.model.removeElement(msg.content);
                        ui.jTextArea1.append("["+ msg.sender +" > All] : "+ msg.content +" has signed out\n");
                    }
                }
                else if(msg.type.equals("upload_req")){
                    
                    if(JOptionPane.showConfirmDialog(ui, ("Accept '"+msg.content+"' from "+msg.sender+" ?")) == 0){
                        
                        JFileChooser jf = new JFileChooser();
                        jf.setSelectedFile(new File(msg.content));
                        int returnVal = jf.showSaveDialog(ui);
                       
                        String saveTo = jf.getSelectedFile().getPath();
                        if(saveTo != null && returnVal == JFileChooser.APPROVE_OPTION){
                            Download dwn = new Download(saveTo, ui);
                            Thread t = new Thread(dwn);
                            t.start();
                            send(new Message("upload_res", ui.username, (""+dwn.port), msg.sender));
                        }
                        else{
                            send(new Message("upload_res", ui.username, "NO", msg.sender));
                        }
                    }
                    else{
                        send(new Message("upload_res", ui.username, "NO", msg.sender));
                    }
                }
                else if(msg.type.equals("upload_res")){
                    if(!msg.content.equals("NO")){
                        int port  = Integer.parseInt(msg.content);
                        String addr = msg.sender;
                        
                        ui.jButton5.setEnabled(false); ui.jButton6.setEnabled(false);
                        Upload upl = new Upload(addr, port, ui.file, ui);
                        Thread t = new Thread(upl);
                        t.start();
                    }
                    else{
                        ui.jTextArea1.append("[SERVER > Me] : "+msg.sender+" rejected file request\n");
                    }
                }
                else{
                    ui.jTextArea1.append("[SERVER > Me] : Unknown message type\n");
                }
            }
            catch(Exception ex) {
                keepRunning = false;
                ui.jTextArea1.append("[Application > Me] : Connection Failure\n");
                ui.jButton1.setEnabled(true); ui.jTextField1.setEditable(true); ui.jTextField2.setEditable(true);
                ui.jButton4.setEnabled(false); ui.jButton5.setEnabled(false); ui.jButton5.setEnabled(false);
                
                for(int i = 1; i < ui.model.size(); i++){
                    ui.model.removeElementAt(i);
                }
                
                ui.clientThread.stop();
                
                System.out.println("Exception SocketClient run()");
                ex.printStackTrace();
            }
        }
    }
    
    public void send(Message msg){
        try {
            Out.writeObject(msg);
            Out.flush();
            System.out.println("Outgoing : "+msg.toString());
            
            if(msg.type.equals("message") && !msg.content.equals(".bye")){
                String msgTime = (new Date()).toString();
                try{
                    hist.addMessage(msg, msgTime);               
                    DefaultTableModel table = (DefaultTableModel) ui.historyFrame.jTable1.getModel();
                    table.addRow(new Object[]{"Me", msg.content, msg.recipient, msgTime});
                }
                catch(Exception ex){}
            }
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        }
    }
    
    public void closeThread(Thread t){
        t = null;
    }
}
