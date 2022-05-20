package app.easyeducation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class CourFragment extends Fragment {

    RecyclerView coursListview;
    DatabaseReference database;
    CourAdapter adapter;
    ArrayList<Cour> cours;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        database= FirebaseDatabase.getInstance().getReferenceFromUrl("https://easyeducation-80f1b-default-rtdb.firebaseio.com/").child("Cours");
        View view=inflater.inflate(R.layout.fragment_cour,container,false);
        coursListview=view.findViewById(R.id.recyclerViewCour);
        coursListview.setLayoutManager(new LinearLayoutManager(getActivity()));

        cours=new ArrayList<>();
        adapter =new CourAdapter(getActivity(),cours);
        coursListview.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snap : snapshot.getChildren())
                {

                    Cour cour=snap.getValue(Cour.class);
                    cours.add(cour);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }
}
