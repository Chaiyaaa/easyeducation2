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



    private final RecycleViewInterface recycleViewInterface;
    Context context;
    ArrayList<Cour> List;

    public CourAdapter(Context context, ArrayList<Cour> list ,RecycleViewInterface recycleViewInterface) {
        this.context = context;
        List = list;
        this.recycleViewInterface=recycleViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.itemcour,parent,false);
       return new MyViewHolder(v,recycleViewInterface);
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

        public MyViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {

            super(itemView);
            courname=itemView.findViewById(R.id.courname);
            courdate=itemView.findViewById(R.id.datecour);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recycleViewInterface != null)
                    {
                        int pos= getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION)
                        {
                            recycleViewInterface.OnItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
