package com.cookandroid.a0929.ui.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.cookandroid.a0929.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimePickDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimePickDialog extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TimePickDialog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatePickDialog.
     */
    // TODO: Rename and change types and number of parameters
    public static TimePickDialog newInstance(String param1, String param2) {
        TimePickDialog fragment = new TimePickDialog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private DatePickerDialog.OnDateSetListener listener;
    public Calendar cal = Calendar.getInstance();

    public void setListener(DatePickerDialog.OnDateSetListener listener){
        this.listener = listener;
    }
    ////
    Button btnConfirm;
    Button btnCancel;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialog = inflater.inflate(R.layout.fragment_time_pick_dialog,null);

        btnCancel = dialog.findViewById(R.id.btn_cancel);
        btnConfirm = dialog.findViewById(R.id.btn_confirm);
        final NumberPicker hourPicker = (NumberPicker) dialog.findViewById(R.id.picker_hour);
        final NumberPicker minutePicker = (NumberPicker) dialog.findViewById(R.id.picker_minute);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickDialog.this.getDialog().cancel();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDateSet(null,hourPicker.getValue(),minutePicker.getValue(),0);
                TimePickDialog.this.getDialog().cancel();
            }
        });
        minutePicker.setMinValue(1);
        minutePicker.setMaxValue(60);
        minutePicker.setValue(cal.get(Calendar.HOUR));

        hourPicker.setMinValue(1);
        hourPicker.setMaxValue(24);
        hourPicker.setValue(cal.get(Calendar.MINUTE));

        builder.setView(dialog);

        return builder.create();
    }
    ////
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
        return inflater.inflate(R.layout.fragment_date_pick_dialog, container, false);
    }

}