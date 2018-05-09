package com.example.csci4391.databasesample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText textName;
    EditText textMajor;
    Button btnInsert;
    Button btnFetch;
    Button btnUpdate;
    Button btnDelete;

    MyDBHelper db;


    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        textName = view.findViewById(R.id.textName);
        textMajor = view.findViewById(R.id.textMajor);
        btnInsert = view.findViewById(R.id.btnInsert);
        btnFetch = view.findViewById(R.id.btnFetch);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnInsert.setOnClickListener(this);
        btnFetch.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        db = new MyDBHelper(getActivity());

        return  view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onClick(View view) {
        if (view == btnInsert) {
            Student stu = new Student();
            stu.setName(textName.getText().toString());
            stu.setMajor(textMajor.getText().toString());

            db.insertStudent(stu);

            Toast.makeText(getActivity(), "Successfully inserted new student", Toast.LENGTH_SHORT).show();
        }
        else if (view == btnFetch) {
            fetchAndShowAllStudents();
        }
        else if (view == btnUpdate) {
            Student stu = new Student();
            stu.setName(textName.getText().toString());
            stu.setMajor(textMajor.getText().toString());

            db.updateStudent(stu);

            fetchAndShowAllStudents();
        }
        else if (view == btnDelete) {
            String stuName = textName.getText().toString();
            db.deleteStudent(stuName);

            fetchAndShowAllStudents();
        }
    }

    public void fetchAndShowAllStudents() {
        /*
        List<Student> allStudents = db.fetchAllStudents();

        String toastMsg = "";
        for (Student stu : allStudents) {
            toastMsg += stu.getName() + "          " + stu.getMajor() + "\n";
        }

        Toast.makeText(getActivity(), toastMsg, Toast.LENGTH_SHORT).show();
        */

        StudentListFragment stuListFragment = new StudentListFragment();
        stuListFragment.stuCursor = db.fetchAllStudentsCursor();
        ((MainActivity) getActivity()).pushFragment(stuListFragment, true);

    }



    /**
         * This interface must be implemented by activities that contain this
         * fragment to allow an interaction in this fragment to be communicated
         * to the activity and potentially other fragments contained in that
         * activity.
         * <p>
         * See the Android Training lesson <a href=
         * "http://developer.android.com/training/basics/fragments/communicating.html"
         * >Communicating with Other Fragments</a> for more information.
         */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
