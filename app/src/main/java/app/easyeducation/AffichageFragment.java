package app.easyeducation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class AffichageFragment extends Fragment implements RecycleViewInterface{
    Button post;
    RecyclerView affichagelistview;
    DatabaseReference database;
    AffichageAdapter adapter;
    ArrayList<Affichage> affichages;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_affichage, container, false);
        post=view.findViewById(R.id.postaffichage);


        database= FirebaseDatabase.getInstance().getReferenceFromUrl("https://easyeducation-80f1b-default-rtdb.firebaseio.com/").child("Affichages");

        affichagelistview =view.findViewById(R.id.recyclerViewaffichage);



        String type="";

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor=sharedPreferences.edit();


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),AddNewAffichage.class);
                startActivity(intent);

            }
        });





        affichagelistview.setLayoutManager(new LinearLayoutManager(getActivity()));

        affichages=new ArrayList<>();
        adapter =new AffichageAdapter(getActivity(),affichages,this);
        affichagelistview.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snap : snapshot.getChildren())
                {

                    Affichage affichageitem=snap.getValue(Affichage.class);
                    affichages.add(affichageitem);

                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent=new Intent(getActivity(),AffichageCheck.class);
        Affichage selectedAffichage=affichages.get(position); //getting selected cour from list

        intent.putExtra("Affichagename",selectedAffichage.getNom());
        intent.putExtra("Affichagedate",selectedAffichage.getDate());
        intent.putExtra("AffichageLink",selectedAffichage.getLien());
        intent.putExtra("AffichageModule",selectedAffichage.getModule());

        startActivity(intent);
    }
}
