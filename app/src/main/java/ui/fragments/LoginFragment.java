package ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymconnectapplication.R;

public class LoginFragment extends Fragment {

    public LoginFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Relie ce fragment au layout XML
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}
