package ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.gymconnectapplication.R;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Constructeur vide requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnLogin = view.findViewById(R.id.button2); // bouton "Sign In"
        Button btnSignUp = view.findViewById(R.id.button); // bouton "Sign Up"

        // ✅ Navigation vers LoginFragment
        btnLogin.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_home_to_signin);
        });

        // ✅ Navigation vers SignupFragment
        btnSignUp.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_home_to_signup);
        });

        return view;
    }
}
