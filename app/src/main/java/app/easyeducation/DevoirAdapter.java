package app.easyeducation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DevoirAdapter extends RecyclerView.Adapter<DevoirAdapter.MyViewHolder> {




    Context context;
    ArrayList<Devoir> List;

    public DevoirAdapter(Context context, ArrayList<Devoir> list) {
        this.context = context;
        List = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.itemdevoir,parent,false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Devoir devoir=List.get(position);
        holder.devoirname.setText(devoir.getNom());
        holder.devoirdate.setText(devoir.getDate());


    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView devoirname,devoirdate;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            devoirname=itemView.findViewById(R.id.devoirname);
            devoirdate=itemView.findViewById(R.id.datedevoir);
        }
    }
}
