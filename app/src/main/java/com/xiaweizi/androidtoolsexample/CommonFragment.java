package com.xiaweizi.androidtoolsexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.androidtoolsexample.CommonFragment
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/02/01
 *     desc   :
 * </pre>
 */

public class CommonFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_common, null);
    }
}
