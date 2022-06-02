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



    private final RecycleViewInterface recycleViewInterface;
    Context context;
    ArrayList<Devoir> List;

    public DevoirAdapter(Context context, ArrayList<Devoir> list,RecycleViewInterface recycleViewInterface) {
        this.context = context;
        List = list;
        this.recycleViewInterface=recycleViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.itemdevoir,parent,false);
       return new MyViewHolder(v,recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Devoir devoir=List.get(position);
        holder.devoirname.setText(devoir.getNom());
        holder.devoirdate.setText(devoir.getDate());
        holder.module.setText(devoir.getModule());

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView devoirname,devoirdate,module;


        public MyViewHolder(@NonNull View itemView,RecycleViewInterface recycleViewInterface) {

            super(itemView);
            devoirname=itemView.findViewById(R.id.devoirname);
            devoirdate=itemView.findViewById(R.id.datedevoir);
            module=itemView.findViewById(R.id.modulename_devoir);

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
