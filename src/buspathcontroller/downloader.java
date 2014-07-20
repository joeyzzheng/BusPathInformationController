/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package buspathcontroller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Zhaowei
 */
public class downloader {
    
    public downloader(){
        stopRouteDownload();
    }
    
    //download the latest stop and route list so that we can update bus path information later.
    private void stopRouteDownload(){
        try{
            URL routeList = new URL("http://api.ebongo.org/routelist?format=json&api_key=xApBvduHbU8SRYvc74hJa7jO70Xx4XNO");
            URL stopList = new URL("http://api.ebongo.org/stoplist?format=json&api_key=xApBvduHbU8SRYvc74hJa7jO70Xx4XNO");
            HttpURLConnection routeListCon = (HttpURLConnection) routeList.openConnection();
            HttpURLConnection stopListCon = (HttpURLConnection) stopList.openConnection();
            routeListCon.connect();
            stopListCon.connect();
            
            BufferedInputStream in1 = new BufferedInputStream(routeListCon.getInputStream());
            BufferedInputStream in2 = new BufferedInputStream(stopListCon.getInputStream());
            OutputStream out1 = new BufferedOutputStream(new FileOutputStream(new File("/Users/Zhaowei/Desktop/BusPath/Route/RouteList.json")));
            OutputStream out2 = new BufferedOutputStream(new FileOutputStream(new File("/Users/Zhaowei/Desktop/BusPath/Stop/StopList.json")));
            
            byte[] buf1 = new byte[256];
            byte[] buf2 = new byte[256];
            int n=0;
            int m = 0;
            while ((n=in1.read(buf1))>=0) {  
                out1.write(buf1, 0, n);  
            }
            out1.flush();
            out1.close();
            while ((n=in2.read(buf2))>=0) {  
                out2.write(buf2, 0, n);  
            }
            out2.flush();
            out2.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
