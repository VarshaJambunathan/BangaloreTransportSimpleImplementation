package com.example.user.bangaloretransport21;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Modules.DirectionFinder;
import Modules.Route;
import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;

public class ModesMock extends AppCompatActivity {

    public String ok= "OK";
    TextView text;
    String response;
    String Origin,Destination;
    int i,j;
    String result_route = "";
    String route_unavailable = " Sorry !! Something went wrong, the requested route is not available !!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes_mock);

        text = (TextView) findViewById(R.id.route_text);
        text.setText(route_unavailable);

        this.response = DirectionFinder.Response();

        try {

            JSONObject jsonData = new JSONObject(response);
            JSONArray jsonRoutes = jsonData.getJSONArray("routes");
            //Extract “features” JSONArray
            String statusValue = jsonData.getString("status");

            if(statusValue.equals(ok)){

                for(i=0;i<jsonRoutes.length();i++){
                    JSONObject jsonRoute = jsonRoutes.getJSONObject(i);
                    Route route = new Route();
                    JSONArray jsonLegs = jsonRoute.getJSONArray("legs");
                    JSONObject jsonLeg = jsonLegs.getJSONObject(0);
                    JSONObject jsonDistance = jsonLeg.getJSONObject("distance");
                    JSONObject jsonDuration = jsonLeg.getJSONObject("duration");
                    String distance = jsonDistance.getString("text");
                    String duration = jsonDuration.getString("text");
                    result_route = result_route.concat("ROUTE " + (i+1) +"\nDistance :" + distance + "\nDuration"
                            + duration + "\n\n Steps: \n");

                    JSONArray jsonSteps = jsonLeg.getJSONArray("steps");
                    for(j=0;j<jsonSteps.length();j++){

                        JSONObject jsonStep = jsonSteps.getJSONObject(j);
                        JSONObject stepDistance =  jsonStep.getJSONObject("distance");
                        String sDistance = stepDistance.getString("text");
                        String s_instructions = jsonStep.getString("html_instructions");

                        result_route = result_route.concat((j+1) + "]  For" + sDistance + "  " + Html.fromHtml(s_instructions) + "\n");
                    }
                }
                Log.e("Route Data", result_route);
                text.setText(result_route);
            }
            else{
                text.setText(route_unavailable);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("For loops", "Problem parsing the earthquake JSON results", e);
        }

    }

}
