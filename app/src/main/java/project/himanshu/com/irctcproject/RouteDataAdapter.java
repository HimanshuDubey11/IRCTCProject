package project.himanshu.com.irctcproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RouteDataAdapter extends RecyclerView.Adapter<RouteDataAdapter.Data> {

    ArrayList<RouteData> routeDataArrayList;

    public RouteDataAdapter(ArrayList<RouteData> routeDataArrayList) {
        this.routeDataArrayList = routeDataArrayList;
    }

    @NonNull
    @Override
    public RouteDataAdapter.Data onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_route_data_adapter,null);
        return new Data(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteDataAdapter.Data data, int i) {

        data.num.setText(routeDataArrayList.get(i).number);
        data.name.setText(routeDataArrayList.get(i).name);
        data.code.setText(routeDataArrayList.get(i).code);
        data.arr.setText(routeDataArrayList.get(i).scharr);
        data.dep.setText(routeDataArrayList.get(i).schdep);
        data.day.setText(routeDataArrayList.get(i).day);

    }

    @Override
    public int getItemCount() {
        return routeDataArrayList.size();
    }

    public class Data extends RecyclerView.ViewHolder {

        TextView num, name, code, arr, dep, day;
        public Data(@NonNull View itemView) {
            super(itemView);

            num = itemView.findViewById(R.id.routenumber);
            name = itemView.findViewById(R.id.name);
            code = itemView.findViewById(R.id.code);
            arr = itemView.findViewById(R.id.scharr);
            dep = itemView.findViewById(R.id.schdep);
            day = itemView.findViewById(R.id.day);

        }
    }
}
