package com.evs.jgb.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.evs.jgb.R;
import com.evs.jgb.model.SectionModel;
import com.evs.jgb.retrofit.ApiInterface;
import com.evs.jgb.retrofit.ApiInterfaceService;
import com.evs.jgb.retrofit.ListResponse;
import com.evs.jgb.utils.Global;
import com.evs.jgb.utils.SessionManager;
import com.evs.jgb.utils.Utills;
import com.evs.jgb.viewModels.AuthListener;
import com.evs.jgb.viewModels.UserViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements AuthListener<ListResponse<SectionModel>> {
    @BindView(R.id.edttxt_pwd)
    EditText edttxt_pwd;
    String password;
    SharedPreferences prefs;
    Button btn_CreateAnAccount;
    UserViewModel userViewModel;
    ACProgressFlower progressDialog;
    ArrayList<SectionModel> sectionModelList;
    String newpassword="JBG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        intilaize();
        Utills.StatusBarColour(LoginActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.text_toolbar);
        mTitle.setText("ENTER PASSWORD".toUpperCase());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        btn_CreateAnAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    private void intilaize() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        progressDialog = Global.getProgress(this, "Login...");

    }

    @OnClick(R.id.btn_access)
    public void onClick(View view) {
        if (view.getId() == R.id.btn_access) {
            if (Global.isOnline(this)) {
                getSectionResponse();
                if (validate()) {
                    SessionManager.save_check_login(prefs, true);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
              //      userViewModel.hitParentDetails(this);


                }
            }
        }
    }

    void getSectionResponse() {
        ApiInterface networkService = ApiInterfaceService.getClient().create(ApiInterface.class);
        Call<ListResponse<SectionModel>> arrayListCall = networkService.Modulelist(
                "en-US",
                "m007,m008,m014,m015,m012,m011,m009",
//               R.string.apitoken,
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651"
        );
        arrayListCall.enqueue(new Callback<ListResponse<SectionModel>>() {
            @Override
            public void onResponse(@NonNull Call<ListResponse<SectionModel>> call, @NonNull Response<ListResponse<SectionModel>> response) {
                progressDialog.dismiss();


                if (!response.isSuccessful()) {
                    switch (response.code()) {
                        case 404:
                            Global.showToast(LoginActivity.this, "not found");
                            break;
                        case 500:
                            Global.showToast(LoginActivity.this, "server broken");
                            break;
                        default:
                            Global.showToast(LoginActivity.this, "unknown error");
                            break;
                    }
                }
                ListResponse<SectionModel> loginResponse = response.body();
                sectionModelList = new ArrayList<>();
                if (loginResponse != null) {
//                    for(int i =0; i <loginResponse.getLoginSuccesses().size();i++)
//                    {
//                        sectionModelList.add(loginResponse.getLoginSuccesses().get(i));
//                        Toast.makeText(getActivity(), loginResponse.getLoginSuccesses().get(i).getName_division(), Toast.LENGTH_SHORT).show();
//                    }


                }
            }

            @Override
            public void onFailure(@NonNull Call<ListResponse<SectionModel>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
//                login_btn.setClickable(true);
            }
        });

    }

    private boolean validate() {
        password = edttxt_pwd.getText().toString();
        if (Global.isNullOrEmpty(password)) {
            edttxt_pwd.setError(Html.fromHtml("<font color='red'>Enter  Password</font>"));
            return false;
        }else if(!password.equals(newpassword)){
            Global.showToast(LoginActivity.this,"Password not match");
            return false;
        }
        return true;
    }

    @Override
    public void onStarted() {
        progressDialog.show();
    }

    @Override
    public void onSuccess(LiveData<ListResponse<SectionModel>> data) {
        progressDialog.dismiss();
        data.observe(this, new Observer<ListResponse<SectionModel>>() {
            @Override
            public void onChanged(ListResponse<SectionModel> sectionModelListResponse) {

            }
        });

    }

    @Override
    public void onFailure(String message) {

    }
}