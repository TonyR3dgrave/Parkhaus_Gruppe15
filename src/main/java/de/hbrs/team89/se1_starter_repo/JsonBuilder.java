package de.hbrs.team89.se1_starter_repo;

import javax.json.*;
import java.util.List;

public class JsonBuilder {
    //Elements for the x-axis
    private final List<?> x;
    //Elements for the y-axis
    private final List<?> y;
    private String name;

    public JsonBuilder(List<?> x, List<?> y){
        this.x = x;
        this.y = y;
    }

    public JsonObject makeBar(){
        return Json.createObjectBuilder()
                .add("data", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("x", makeJsonArray(x) )
                                .add("y", makeJsonArray(y))
                                .add("type", "bar")
                        )
                ).build();
    }

    public JsonArray makeJsonArray(List<?> input){
        JsonArrayBuilder arr = Json.createArrayBuilder();
        for (Object o : input) {
                arr.add( Json.createValue(String.valueOf(o)));
        }
        return arr.build();
    }
}
