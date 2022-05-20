package app.easyeducation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourAdapter extends RecyclerView.Adapter<CourAdapter.MyViewHolder> {




    Context context;
    ArrayList<Cour> List;

    public CourAdapter(Context context, ArrayList<Cour> list) {
        this.context = context;
        List = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.itemcour,parent,false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cour cour=List.get(position);
        holder.courname.setText(cour.getNom());
        holder.courdate.setText(cour.getDate());

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView courname,courdate;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            courname=itemView.findViewById(R.id.courname);
            courdate=itemView.findViewById(R.id.datecour);
        }
    }
}
