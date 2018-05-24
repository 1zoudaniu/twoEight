package zou.te.happy.com.happyte.fragments;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import zou.te.happy.com.happyte.R;
import zou.te.happy.com.happyte.base.BaseLazyFragment;
import zou.te.happy.com.happyte.permission.AfterPermissionGranted;
import zou.te.happy.com.happyte.permission.EasyPermissions;
import zou.te.happy.com.happyte.utils.MyLog;
import zou.te.happy.com.happyte.utils.ToastUtil;
import zou.te.happy.com.happyte.utils.widgetUtils.MyBottomDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseLazyFragment implements EasyPermissions.PermissionCallbacks {

    private String mFrom;
    private Button viewById;
    private static final int REQUEST_STORAGE = 100;  //存储卡
    private static final int REQUEST_CAMERA = 200;//拍照
    private MyBottomDialog mDialogTake;

    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance(String param) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("from", param);
        fragment.setArguments(args);
        return fragment;
    }

    //    layout_dialog_head_image
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFrom = getArguments().getString("from");
        }
    }


    @Override
    public void doLazyBusiness() {

    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        viewById = (Button) contentView.findViewById(R.id.cl_llll);
        viewById.setOnClickListener(this);
    }

    @Override
    public void onWidgetClick(View view) {
        if (view == viewById) {
            mDialogTake = new MyBottomDialog(getContext(), R.layout.layout_dialog_head_image);
            mDialogTake.findViewById(R.id.dialog_take_photo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    takePhoto();//拍照
                }
            });
            mDialogTake.findViewById(R.id.dialog_take_gal).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getGall();//图库
                }
            });
            mDialogTake.findViewById(R.id.dialog_take_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialogTake.dismiss();//图库
                }
            });
            mDialogTake.show();
        }
    }

    @AfterPermissionGranted(REQUEST_STORAGE)
    private void getGall() {
        if (EasyPermissions.hasPermissions(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            mDialogTake.dismiss();//图库
            // Have permission, do the thing!
            Toast.makeText(getActivity(), "TODO: SMS things11", Toast.LENGTH_LONG).show();
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_camer),
                    REQUEST_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @AfterPermissionGranted(REQUEST_CAMERA)
    private void takePhoto() {
        if (EasyPermissions.hasPermissions(getContext(), Manifest.permission.CAMERA)) {
            mDialogTake.dismiss();//拍照
            // Have permission, do the thing!
            Toast.makeText(getActivity(), "TODO: SMS things", Toast.LENGTH_LONG).show();
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_camer),
                    REQUEST_CAMERA, Manifest.permission.CAMERA);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        MyLog.d("onPermissionsGranted:" + requestCode + ":" + perms.size() + "" + perms.toString());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        MyLog.d("onPermissionsDenied:" + requestCode + ":" + perms.size() + "" + perms.toString());
        ToastUtil.showLongToast(getContext(), "授权失败，请设置手机权限");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


}
