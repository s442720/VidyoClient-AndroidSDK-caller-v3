package com.vidyo.vidyoconnector;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ModalCall extends BottomSheetDialogFragment {
    private ActionListener mActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.modal_content, container, false);

        Button btnPick = v.findViewById(R.id.call);
        Button btnCancel = v.findViewById(R.id.cancel);

        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mActionListener != null) {
                    mActionListener.onButtonClick(R.id.call);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mActionListener != null) {
                    mActionListener.onButtonClick(R.id.cancel);
                }
                dismiss();
            }
        });

        return v;
    }

    public void setmActionListener (ActionListener actionListener) {
        mActionListener = actionListener;
    }

    interface ActionListener{
        void onButtonClick(int id);
    }
}
