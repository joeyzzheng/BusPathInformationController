/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package buspathcontroller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Zhaowei
 */
public class JSONFileParser {
    public void generateAllRoutes(){
        JSONParser parser=new JSONParser();
        try{
            PrintWriter writer=new PrintWriter("/Users/Zhaowei/Desktop/BusPath/allRoutes.txt");
            Object obj = parser.parse(new FileReader("/Users/Zhaowei/Desktop/BusPath/RawJSON/RouteList.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray routes = (JSONArray) jsonObject.get("routes");
            Iterator iterator = routes.iterator();
            while (iterator.hasNext()) {
		JSONObject route=(JSONObject) ((JSONObject) iterator.next()).get("route");
                writer.println(route.get("name")+";"+route.get("tag")+";"+route.get("agency"));
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    public void generateAllStops(){
        JSONParser parser=new JSONParser();
        try{
            PrintWriter writer=new PrintWriter("/Users/Zhaowei/Desktop/BusPath/allStops.txt");
            Object obj = parser.parse(new FileReader("/Users/Zhaowei/Desktop/BusPath/RawJSON/StopList.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray routes = (JSONArray) jsonObject.get("stops");
            Iterator iterator = routes.iterator();
            while (iterator.hasNext()) {
		JSONObject stop=(JSONObject) ((JSONObject) iterator.next()).get("stop");
                writer.println(stop.get("stopnumber")+";"+stop.get("stoptitle")+";"+stop.get("stoplat")+";"+stop.get("stoplng"));
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    public void generateRouteStopsAndPath(){
        JSONParser parser=new JSONParser();
        ArrayList<String> routeList = new ArrayList<String>();
        try{
            FileReader reader = new FileReader("/Users/Zhaowei/Desktop/BusPath/allRoutes.txt");
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null){
                String routeName=line.split(";")[1];
                routeList.add(routeName);
            }
            br.close();
            reader.close();
            
            Iterator<String> it = routeList.iterator();
            while (it.hasNext()){
                String routeName = it.next();
                PrintWriter writer=new PrintWriter("/Users/Zhaowei/Desktop/BusPath/routeInfo/stops/"+routeName+".txt");
                Object obj = parser.parse(new FileReader("/Users/Zhaowei/Desktop/BusPath/RawJSON/route/"+routeName+".json"));
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject route = (JSONObject) jsonObject.get("route");
                JSONArray directions = (JSONArray) route.get("directions");
                for (int i = 0; i<directions.size();i++){
                    JSONObject direction = (JSONObject) directions.get(i);
                    writer.println(direction.get("direction"));
                    JSONArray stops = (JSONArray) direction.get("stops");
                    Iterator iter = stops.iterator();
                    while (iter.hasNext()){
                        JSONObject stop = (JSONObject) iter.next();
                        writer.println(stop.get("stopnumber")+";"+stop.get("stoptitle")+";"+stop.get("stoplat")+";"+stop.get("stoplng"));
                    }
                }
                writer.close();
            }
            
            
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
