package ng.shoppi.androidfrontend.recycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

import ng.shoppi.androidfrontend.R;

/**
 * @author Olawale
 */

public class CustomRecyclerAdapterB extends RecyclerView.Adapter<CustomRecyclerAdapterB.RecyclerViewHolder> {

    private Context context;
    private ArrayList<Map<String, String>> items;
    private OnRecyclerClickListener onRecyclerClickListener;
    private boolean loadImage;

    public static final String IMAGE_URL = "image_url";
    public static final String TITLE = "title";
    public static final String TIMES_TAMP = "times_tamp";


    public CustomRecyclerAdapterB(Context context, ArrayList<Map<String, String>> items, boolean loadImage) {
        this.context = context;
        this.items = items;
        this.onRecyclerClickListener = (OnRecyclerClickListener) context;
        this.loadImage = loadImage;
    }

    @Override
    public CustomRecyclerAdapterB.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.champ_alert_item, parent, false);
        return new CustomRecyclerAdapterB.RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomRecyclerAdapterB.RecyclerViewHolder holder, final int position) {
        Map<String, String> item = this.items.get(position);
        if (loadImage) {
            Picasso.with(context)
                    .load(item.get(IMAGE_URL))
                    .into(holder.imageViewAvatar);
        }
        holder.textViewAlertMessage.setText(item.get(TITLE));
        holder.textViewAlertTime.setText(item.get(TIMES_TAMP));
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private ImageView imageViewAvatar;
        private TextView textViewAlertMessage;
        private TextView textViewAlertTime;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            imageViewAvatar = itemView.findViewById(R.id.imageViewMessageIcon);
            textViewAlertMessage = itemView.findViewById(R.id.textViewAlertMessage);
            textViewAlertTime = itemView.findViewById(R.id.textViewAlertTime);
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
