/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package buspathcontroller;

import java.io.FileReader;
import java.io.PrintWriter;
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
            PrintWriter writer=new PrintWriter("/Users/Zhaowei/Desktop/BusPath/Route/allRoutes.txt");
            Object obj = parser.parse(new FileReader("/Users/Zhaowei/Desktop/BusPath/Route/RouteList.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray routes = (JSONArray) jsonObject.get("routes");
            Iterator iterator = routes.iterator();
            while (iterator.hasNext()) {
		JSONObject route=(JSONObject) ((JSONObject) iterator.next()).get("route");
                writer.println(route.get("name")+","+route.get("tag")+","+route.get("agency"));
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
