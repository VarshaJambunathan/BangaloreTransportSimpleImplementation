package Modules;

import java.util.List;

/**
 * Created by user on 21-08-2016.
 */
public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
