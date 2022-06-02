package app.easyeducation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AffichageAdapter extends RecyclerView.Adapter<AffichageAdapter.MyViewHolder> {



    private final RecycleViewInterface recycleViewInterface;
    Context context;
    ArrayList<Affichage> List;

    public AffichageAdapter(Context context, ArrayList<Affichage> list, RecycleViewInterface recycleViewInterface) {
        this.context = context;
        List = list;
        this.recycleViewInterface=recycleViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.itemaffichage,parent,false);
       return new MyViewHolder(v,recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Affichage affichage=List.get(position);

        holder.affichagename.setText(affichage.getNom());
        holder.affichagedate.setText(affichage.getDate());
        holder.module.setText(affichage.getModule());

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView affichagename, affichagedate,module;


        public MyViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {

            super(itemView);
            affichagename =itemView.findViewById(R.id.affichagename);
            affichagedate =itemView.findViewById(R.id.dateaffichage);
            module=itemView.findViewById(R.id.modulename_affichage);

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
