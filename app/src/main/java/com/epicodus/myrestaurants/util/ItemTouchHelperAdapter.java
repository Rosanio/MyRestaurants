package com.epicodus.myrestaurants.util;

/**
 * Created by Matt on 5/8/2016.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
