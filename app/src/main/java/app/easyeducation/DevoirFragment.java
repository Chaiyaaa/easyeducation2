package app.easyeducation;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DevoirFragment extends Fragment implements RecycleViewInterface{
    Button post;
    RecyclerView devoirlistview;
    DatabaseReference database;
    DevoirAdapter adapter;
    ImageView inbox;
    ArrayList<Devoir> devoir;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_devoir, container, false);

        post=view.findViewById(R.id.postdevoir);
        inbox=view.findViewById(R.id.inbox_devoir);
        database= FirebaseDatabase.getInstance().getReferenceFromUrl("https://easyeducation-80f1b-default-rtdb.firebaseio.com/").child("Devoirs");

        devoirlistview =view.findViewById(R.id.recyclerViewdevoir);



        String type="";

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor=sharedPreferences.edit();

        if (sharedPreferences.contains("type"))
        {

            type=sharedPreferences.getString("type",""); //second is empty cause its the default for String
            if (type.equals("etud"))
            {
                //we need to hide the btn
                //Toast.makeText(getActivity(),"etud",Toast.LENGTH_SHORT).show();
            }
            else if (type.equals("prof"))
            {


                post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent=new Intent(getActivity(),AddNewDevoir.class);
                        startActivity(intent);

                    }
                });

                inbox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog();
                    }
                });



            }

        }






        devoirlistview.setLayoutManager(new LinearLayoutManager(getActivity()));

        devoir=new ArrayList<>();
        adapter =new DevoirAdapter(getActivity(),devoir,this);
        devoirlistview.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snap : snapshot.getChildren())
                {

                    Devoir devoiritem=snap.getValue(Devoir.class);
                    devoir.add(devoiritem);
                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }

    private void showDialog() {
        Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_devoirtest);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.backgroundrounded);
        Button showtest=dialog.findViewById(R.id.tests);
        Button showdevoirs=dialog.findViewById(R.id.devoirs);

        showtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"tests",Toast.LENGTH_SHORT).show();
            }
        });

        showdevoirs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"devoirs",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    @Override
    public void OnItemClick(int position) {


        Intent intent=new Intent(getActivity(),DevoirsCheck.class);

        Devoir selectedDevoir=devoir.get(position);



        intent.putExtra("Devoirname",selectedDevoir.getNom());
        intent.putExtra("Devoirdate",selectedDevoir.getDate());
        intent.putExtra("DevoirLink",selectedDevoir.getLien());
        intent.putExtra("DevoirModule",selectedDevoir.getModule());

        startActivity(intent);


    }
}
