package com.example.firebaseapps.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseapps.Model.Student;
import com.example.firebaseapps.R;
import com.example.firebaseapps.StudentData;
import com.example.firebaseapps.StudentRegister;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<Student> listStudent;

    private ArrayList<Student> getListStudent() {
        return listStudent;
    }

    public void setListStudent(ArrayList<Student> listStudent) {
        this.listStudent = listStudent;
    }

    public StudentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StudentAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_adapter, parent, false);
        return new StudentAdapter.CardViewViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder (@NonNull final StudentAdapter.CardViewViewHolder holder, int position) {
        final Student student = getListStudent().get(position);
        holder.cv_name_student.setText(student.getName());
        holder.cv_nim_student.setText(student.getNim());
        holder.cv_email_student.setText(student.getEmail());
        holder.cv_gender_student.setText(student.getGender());
        holder.cv_age_student.setText(student.getAge());
        holder.cv_address_student.setText(student.getAddress());

    }

    @Override
    public int getItemCount() {
        return getListStudent().size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView cv_name_student, cv_nim_student, cv_email_student, cv_gender_student,cv_age_student, cv_address_student;
        OnCardListener onCardListener;
        ImageButton imageButton_edit, imageButton_delete;

        CardViewViewHolder(View itemView) {
            super(itemView);
            cv_name_student = itemView.findViewById(R.id.cv_name_student);
            cv_nim_student = itemView.findViewById(R.id.cv_nim_student);
            cv_email_student = itemView.findViewById(R.id.cv_email_student);
            cv_gender_student = itemView.findViewById(R.id.cv_gender_student);
            cv_age_student = itemView.findViewById(R.id.cv_age_student);
            cv_address_student = itemView.findViewById(R.id.cv_address_student);

            imageButton_edit = itemView.findViewById(R.id.imageButton_edit);
            imageButton_delete = itemView.findViewById(R.id.imageButton_delete);

            imageButton_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.d("tes", "masuk lagi di edit");
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, StudentRegister.class);
                    intent.putExtra("action", "edit");
                    intent.putExtra("data_student", listStudent.get(position));
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });

            imageButton_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.d("tes", "masuk lagi di delete");
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, StudentData.class);
                    intent.putExtra("action", "delete");
                    intent.putExtra("data_student", listStudent.get(position));
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        }

        //IF IT WORKS, IT WOKRS!!!!
        @Override
        public void onClick(View v) {
            onCardListener.onCardClick(getAdapterPosition());
        }
    }

    public interface OnCardListener {
        void onCardClick(int position);
    }

}