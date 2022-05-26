package app.easyeducation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {



    private final RecycleViewInterface recycleViewInterface;
    Context context;
    ArrayList<Test> List;

    public TestAdapter(Context context, ArrayList<Test> list,RecycleViewInterface recycleViewInterface) {
        this.context = context;
        List = list;
        this.recycleViewInterface=recycleViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.itemtest,parent,false);
       return new MyViewHolder(v,recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Test test=List.get(position);
        holder.testname.setText(test.getNom());
        holder.testdate.setText(test.getDate());


    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView testname, testdate;


        public MyViewHolder(@NonNull View itemView,RecycleViewInterface recycleViewInterface) {

            super(itemView);
            testname =itemView.findViewById(R.id.testname);
            testdate =itemView.findViewById(R.id.datetest);

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
