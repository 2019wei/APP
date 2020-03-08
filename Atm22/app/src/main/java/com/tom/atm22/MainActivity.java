package com.tom.atm22;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUSET_LOGIN = 100;
    private static final String TAG = MainActivity.class.getSimpleName();
    boolean logon = false;
    private List<Fuction> functions;

    //String[] functions = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(!logon){
            Intent intent = new Intent(this,LoginActivity.class);
//            startActivity(intent);
            startActivityForResult(intent,REQUSET_LOGIN);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFunctios();


        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        //Adapter
        //functions = getResources().getStringArray(R.array.functions);
        //FunctionAdapter adapter = new FunctionAdapter(this);
        IconAdapter adapter = new IconAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setupFunctios() {
        functions = new ArrayList<>();
        String[] funcs = getResources().getStringArray(R.array.functions);
        functions.add(new Fuction(funcs[0],R.drawable.func_transaction));
        functions.add(new Fuction(funcs[1],R.drawable.func_balance));
        functions.add(new Fuction(funcs[2],R.drawable.func_finances));
        functions.add(new Fuction(funcs[3],R.drawable.func_contacts));
        functions.add(new Fuction(funcs[4],R.drawable.func_exit));
    }

    public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconHolder> {
        @NonNull
        @Override
        public IconHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_icon,parent,false);

            return new IconHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull IconHolder holder, final int position) {
            final Fuction function = functions.get(position);
            //holder.nameText.setText(function.getName()); 上下寫法都可以，選擇一個最習慣的
            holder.nameText.setText(functions.get(position).getName());
            holder.iconImage.setImageResource(function.getIcon());
            //holder.iconImage.setImageResource(functions.get(position).getIcon());
            //事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClicked(function);
                }
            });
        }

        @Override
        public int getItemCount() {
            return functions.size();
        }

        public  class  IconHolder extends RecyclerView.ViewHolder{
            ImageView iconImage;
            TextView nameText;
            public IconHolder(@NonNull View itemView) {
                super(itemView);
                iconImage = itemView.findViewById(R.id.item_icon);
                nameText = itemView.findViewById(R.id.item_name);
            }
        }
    }

    private void itemClicked(Fuction function) {
        Log.d(TAG, "itemClicked: " + function.getName());
        Intent intent = new Intent(this,LoopActivity.class);
        switch (function.getIcon()){
            case R.drawable.func_transaction:
                intent.putExtra("Name",function.getName());
                startActivity(intent);
                break;
            case  R.drawable.func_balance:
                intent.putExtra("Name",function.getName());
                startActivity(intent);
                break;
            case R.drawable.func_finances:
                intent.putExtra("Name",function.getName());
                startActivity(intent);
                break;
            case R.drawable.func_contacts:
                Intent contacts = new Intent(this,ContactActivity.class);
                startActivity(contacts);
                break;
            case R.drawable.func_exit:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUSET_LOGIN){
            if (resultCode != RESULT_OK){
                finish();
            }
        }

    }
}
