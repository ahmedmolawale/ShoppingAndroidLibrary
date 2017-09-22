package ng.shoppi.androidfrontend.recycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import ng.shoppi.androidfrontend.R;

/**
 * Created by stackcode on 9/21/17.
 */

public class CustomRecyclerAdapterA extends RecyclerView.Adapter<CustomRecyclerAdapterA.RecyclerViewHolder>{

    private Context context;
    private ArrayList<Map<String, String>> items;
    private OnRecyclerClickListener onRecyclerClickListener;
    private boolean loadImage;

    public static final String IMAGE_URL = "image_url";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";



    public CustomRecyclerAdapterA(Context context, ArrayList<Map<String, String>> items, boolean loadImage) {
        this.context = context;
        this.items = items;
        this.onRecyclerClickListener = (OnRecyclerClickListener) context;
        this.loadImage = loadImage;
    }

    @Override
    public CustomRecyclerAdapterA.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CustomRecyclerAdapterA.RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomRecyclerAdapterA.RecyclerViewHolder holder, final int position) {
        Map<String, String> item = this.items.get(position);
        //load the image with picasso
        if(loadImage){
            Picasso.with(context)
                    .load(item.get(IMAGE_URL))
                    .into(holder.imageViewAvatar);
        }
        holder.textViewObjectName.setText(item.get(NAME));
        holder.textViewObjectAddress.setText(item.get(ADDRESS));
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private CircleImageView imageViewAvatar;
        private TextView textViewObjectName;
        private TextView textViewObjectAddress;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
            textViewObjectName = itemView.findViewById(R.id.textViewObjectName);
            textViewObjectAddress = itemView.findViewById(R.id.textViewObjectAddress);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            imageViewAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerClickListener.onImageClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            onRecyclerClickListener.onItemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            return onRecyclerClickListener.onItemLongClick(getAdapterPosition());
        }
    }
}
