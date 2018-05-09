package com.uhcl.bmo.assignment5;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView bookTitle;
    TextView bookAuthor;
    TextView bookSubject;
    TextView bookDuedate;
    Button buttonUpdate;
    Button buttonDelete;
    Button buttonAdd;
    MyDBHelper db;

    private OnFragmentInteractionListener mListener;

    Cursor bookCursor;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        bookTitle = view.findViewById(R.id.textTitle);
        bookAuthor = view.findViewById(R.id.textAuthor);
        bookSubject = view.findViewById(R.id.textSubject);
        bookDuedate = view.findViewById(R.id.textDue);
        buttonUpdate = view.findViewById(R.id.buttonUpdate);
        buttonDelete = view.findViewById(R.id.buttonDelete);
        buttonAdd = view.findViewById(R.id.buttonAdd);
        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
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
        if (view == buttonAdd) {
            Book book = new Book();
            book.setTitle(bookTitle.getText().toString());
            book.setAuthor(bookAuthor.getText().toString());
            book.setSubject(bookSubject.getText().toString());
            book.setDue(bookDuedate.getText().toString());

            db.insertBook(book);

            // TO DO
            //Toast.makeText(getActivity(), "Successfully inserted new book", Toast.LENGTH_SHORT).show();

            fetchAndShowAllBooks();
        }
        else if (view == buttonUpdate) {
            Book book = new Book();
            book.setTitle(bookTitle.getText().toString());
            book.setAuthor(bookAuthor.getText().toString());
            book.setSubject(bookSubject.getText().toString());
            book.setDue(bookDuedate.getText().toString());

            db.updateBook(book);

            fetchAndShowAllBooks();
        }
        else if (view == buttonDelete) {
            String bookName = bookTitle.getText().toString();
            db.deleteBook(bookName);

            fetchAndShowAllBooks();
        }
    }

    public void fetchAndShowAllBooks() {
        // create and instantiate the book list fragment (the one with listview)
        BookListFragment bookListFragment = new BookListFragment();
        // set the cursor to get all records in database
        bookListFragment.bookCursor = db.fetchAllBooksCursor();
        // push fragment into view
        ((MainActivity) getActivity()).pushFragment(bookListFragment, true);
    }
    /*@Override
    public void onClick(View v) {

    }*/

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
