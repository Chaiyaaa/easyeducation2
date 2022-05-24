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




    Context context;
    ArrayList<Test> List;

    public TestAdapter(Context context, ArrayList<Test> list) {
        this.context = context;
        List = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.itemtest,parent,false);
       return new MyViewHolder(v);
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


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            testname =itemView.findViewById(R.id.testname);
            testdate =itemView.findViewById(R.id.datetest);
        }
    }
}
