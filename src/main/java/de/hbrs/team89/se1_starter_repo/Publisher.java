package de.hbrs.team89.se1_starter_repo;

import java.util.ArrayList;
import java.util.List;

public abstract class Publisher {
    private List<View> views = new ArrayList<View>();

    public void addView(View view){
        this.views.add(view);
    }

    public void update(){
        for (View view:views) {
            view.update();
        }
    }
}
