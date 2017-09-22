package ng.shoppi.androidfrontend.recycleradapter;

/**
 *  @author Olawale on 9/21/17.
 */

public interface OnRecyclerClickListener {
    void onItemClick(int position);
    boolean onItemLongClick(int position);
    void onImageClick(int position);
}
