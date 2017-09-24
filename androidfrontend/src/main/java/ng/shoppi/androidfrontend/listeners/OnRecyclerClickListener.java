package ng.shoppi.androidfrontend.listeners;

/**
 * Implement to handle clicks on item view click
 *
 * @author Olawale
 */

public interface OnRecyclerClickListener {
    void onItemClick(int position);

    boolean onItemLongClick(int position);

    void onImageClick(int position);
}
