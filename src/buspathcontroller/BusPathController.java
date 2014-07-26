/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package buspathcontroller;

/**
 *
 * @author Zhaowei
 */
public class BusPathController {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        downloader d= new downloader();
        d.stopRouteListDownload();
        MD5Checksum m = new MD5Checksum();
        //System.out.println(m.getMD5(("/Users/Zhaowei/Desktop/BusPath/Route/RouteList.json")));
        //System.out.println(m.getMD5(("/Users/Zhaowei/Desktop/BusPath/Stop/StopList.json")));
        JSONFileParser j = new JSONFileParser();
        j.generateAllRoutes();
        j.generateAllStops();
        d.routeInfoDownload();
        j.generateRouteStopsAndPath();
    }
    
}
