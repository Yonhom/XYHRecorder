package com.xuyonghong.xyhrecorder.listener;

import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 *
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 *
 * Created by xuyonghong on 2017/5/27.
 */

public interface FragmentInteractionListener {
    /**
     * report the fragment that use this listener's interaction to the
     * activity listening to it
     * @param fragment
     * @param uri
     */
    void onFragmentInteraction(Fragment fragment, Uri uri);
}
