package cn.edu.xmu.jingshuisanqian.utils;

/**
 * Created by hd_chen on 2016/8/31.
 */
public interface OnMoveAndSwipedListener {
    boolean onItemMove(int fromPosition , int toPosition);
    void onItemDismiss(int position);
}
