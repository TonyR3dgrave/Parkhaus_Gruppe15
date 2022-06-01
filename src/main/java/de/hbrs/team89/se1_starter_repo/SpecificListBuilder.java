package de.hbrs.team89.se1_starter_repo;

import java.util.ArrayList;
import java.util.List;

public class SpecificListBuilder {
    private final List<?> cars;
    private final String attribute;

    public SpecificListBuilder(List<?> cars, String attribute) {
        this.cars = cars;
        this.attribute = attribute;
    }

    public List<?> makeSpecificList(){

        switch (attribute){
            case "Gender":
                List<String> attributeList = new ArrayList<>();
                for (Object car : cars) {
                    attributeList.add(((Car) car).gender());
                }
                return attributeList;

        }
        return null;
    }

}
