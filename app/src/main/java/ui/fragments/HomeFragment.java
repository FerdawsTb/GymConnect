package ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gymconnectapplication.R;
import com.example.gymconnectapplication.LoginActivity;
import com.example.gymconnectapplication.SignUpActivity;


public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnLogin = view.findViewById(R.id.button2); // SignIn
        Button btnSignUp = view.findViewById(R.id.button);  // SignUp

        btnLogin.setOnClickListener(v -> {
            // Remplace LoginActivity par ton activité de login
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        btnSignUp.setOnClickListener(v -> {
            // Remplace SignUpActivity par ton activité d'inscription
            Intent intent = new Intent(getActivity(), SignUpActivity.class);
            startActivity(intent);
        });

        return view;
    }

}
