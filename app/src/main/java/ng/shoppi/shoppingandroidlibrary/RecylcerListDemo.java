package ng.shoppi.shoppingandroidlibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ng.shoppi.androidfrontend.recycleradapters.CustomRecyclerAdapterB;
import ng.shoppi.androidfrontend.listeners.OnRecyclerClickListener;

/**
 * @author Olawale on 9/21/17.
 *
 * A demo of one of the custom recycler view adapters provided by the shopping library
 */

public class RecylcerListDemo extends AppCompatActivity implements OnRecyclerClickListener {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_list);

        recyclerView =(RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        //create an arraylist that contains a map of the way the items should be mapped
        ArrayList<Map<String, String>> items = new ArrayList<>();
        //create ten items
        for(int i = 0; i < 10; i++){
            Map<String, String> item = new HashMap<>();
            item.put(CustomRecyclerAdapterB.IMAGE_URL,"https://cdn4.iconfinder.com/data/icons/iconsimple-logotypes/512/github-512.png");
            item.put(CustomRecyclerAdapterB.TIMES_TAMP,"Ahmed Olawale");
            item.put(CustomRecyclerAdapterB.TITLE,"E268 Ikota Shopping Complex");
            items.add(item);
        }

        CustomRecyclerAdapterB customRecyclerAdapterB = new CustomRecyclerAdapterB(this,items,true);
        recyclerView.setAdapter(customRecyclerAdapterB);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getBaseContext(),"Item @ Position "+position+ " Clicked",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onItemLongClick(int position) {
        Toast.makeText(getBaseContext(),"Item @ Position "+position+ " Long Clicked",Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void onImageClick(int position) {
        Toast.makeText(getBaseContext(),"Image @ Position "+position+ " Clicked",Toast.LENGTH_LONG).show();
    }
}
