package com.example.user.bangaloretransport21;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.gigamole.navigationtabbar.ntb.NavigationTabBar;

import java.util.ArrayList;

import Modules.DirectionFinder;

public class VariousModes extends AppCompatActivity {

    EditText mOrigin,mDestination;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_various_modes);

        mOrigin=(EditText)findViewById(R.id.etOrigin1);
        mDestination=(EditText)findViewById(R.id.etDestination1);

       // initUI();
    }

    public void initUI() {
        viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.item_vp_list, null, false);

                final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(
                                getBaseContext(), LinearLayoutManager.VERTICAL, false
                        )
                );
                //recyclerView.setAdapter(new RecycleAdapter());

                container.addView(view);
                return view;
            }
        });

        final String[] colors = getResources().getStringArray(R.array.colors);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.car),
                        Color.parseColor(colors[0]))
                        .title("car")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.bus),
                        Color.parseColor(colors[1]))
                        .title("bus")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ride),
                        Color.parseColor(colors[2]))
                        .title("ride")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.walk),
                        Color.parseColor(colors[3]))
                        .title("walk")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 2);

        navigationTabBar.post(new Runnable() {
            @Override
            public void run() {
                final View viewPager = findViewById(R.id.vp_horizontal_ntb);
                ((ViewGroup.MarginLayoutParams) viewPager.getLayoutParams()).topMargin =
                        (int)navigationTabBar.getBadgeMargin();
                viewPager.requestLayout();
            }
        });

        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {

            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
                model.hideBadge();
            }
        });
    }
        /*
    public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            final View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            int item = viewPager.getCurrentItem();
            /*
            switch(item){

                // item= car,walk,cycle
                // position = position of the card clicked
                case 0:
                    //cycle
                    Log.v("Various Modes","car");
                    holder.txt.setText(String.format(" Distance : " + DirectionFinder.routesCycle.get(position).duration.text + "Time :" +
                            DirectionFinder.routesCycle.get(position).distance.text));
                    break;
                case 1:
                    //walk
                    Log.v("Various Modes","walk");
                    holder.txt.setText(String.format(" Distance : " + DirectionFinder.routesWalk.get(position).duration.text + "Time :" +
                            DirectionFinder.routesWalk.get(position).distance.text));
                    break;
                case 2:
                    //car
                    Log.v("Various Modes","cycle");
                    holder.txt.setText(String.format(" Distance : " + DirectionFinder.routes.get(position).duration.text + "Time :" +
                            DirectionFinder.routesCar.get(position).distance.text));
                    break;
                case 3:
                    //bus
                    holder.txt.setText("No Results !!");
                    break;
                default:
                    holder.txt.setText("Default !!");
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //item,position
                    // new intent to display the text view conataining routes
                }
            });
        }

        @Override
        public int getItemCount() {
            int item = viewPager.getCurrentItem();
            Log.d("TAG", "getItemCount: "+item);
            Log.d("TAG", "getItemCount: "+DirectionFinder.routesCar);
            // switch case count of each list array
            switch(item){
                case 0: return 4;
                case 1: return 3;
                case 2: return 2;
                case 3: return 1; //Bus
                default: return 1;
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView txt;

            public ViewHolder(final View itemView) {
                super(itemView);
                txt = (TextView) itemView.findViewById(R.id.txt_vp_item_list);
            }
        }
    }*/
}
