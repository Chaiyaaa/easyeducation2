package app.easyeducation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNewCour extends AppCompatActivity {

    EditText module_name,cour_name;
    Button add,share,cancel;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    Intent dataHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_cour);
        initWidget();

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://easyeducation-80f1b-default-rtdb.firebaseio.com/").child("Cours");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Selectpdf();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //disable button if there is no data
                //add icon to show that data exists
                UploadPDFToFirebase(dataHolder.getData());

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBacktoCour();
            }
        });

    }

    private void goBacktoCour() {

        Intent intent=new Intent(AddNewCour.this,FragmentsHolder.class);
        startActivity(intent);
        finish();
    }

    private void Selectpdf() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECT"),12);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==12 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            dataHolder=data;

        }
    }

    private void UploadPDFToFirebase(Uri data) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("file is loading ...");
        progressDialog.show();

        StorageReference reference=storageReference.child("upload"+System.currentTimeMillis()+".pdf");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uri=uriTask.getResult();

                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                Cour cour=new Cour(cour_name.getText().toString(),currentDate,uri.toString(),module_name.getText().toString()); //change this
                databaseReference.push().setValue(cour);
                Toast.makeText(getApplicationContext(),"Succes",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();


            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                double progress=(100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("file Uploaded .. "+(int) progress+"%");
            }
        });

    }

    private void initWidget() {
        module_name=findViewById(R.id.module_name);
        cour_name=findViewById(R.id.cours_title);
        add=findViewById(R.id.add_file);
        share=findViewById(R.id.share);
        cancel=findViewById(R.id.cancel);
    }
}