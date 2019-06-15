package com.domineer.triplebro.bookkeeping.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.domineer.triplebro.bookkeeping.R;
import com.domineer.triplebro.bookkeeping.fragments.MyselfFragment;
import com.domineer.triplebro.bookkeeping.properties.ProjectProperties;

import java.io.File;

public class ChooseUserHeadDialogUtil {
    public static void showDialog(final MyselfFragment fragment, final String username, final long timeStamp) {
        boolean checkPermission = PermissionUtil.checkPermission(fragment.getActivity(), "android.permission.CAMERA");
        if (!checkPermission) {
            Toast.makeText(fragment.getActivity(), "未授权拍照或录像，请设置开启权限", Toast.LENGTH_SHORT).show();
            //PermissionController.gotoMeizuPermission(context);
            return;
        }
        checkPermission = PermissionUtil.checkPermission(fragment.getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE");
        if (!checkPermission) {
            Toast.makeText(fragment.getActivity(), "未授权读写手机存储，请设置开启权限", Toast.LENGTH_SHORT).show();
            //PermissionController.gotoMeizuPermission(context);
            return;
        }
        View view = View.inflate(fragment.getActivity(), R.layout.dialog_select_photo, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
        final AlertDialog dialog = builder.setView(view).create();
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent photo_manager = new Intent(Intent.ACTION_PICK, null);
                //打开文件
                photo_manager.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                fragment.startActivityForResult(photo_manager, ProjectProperties.FROM_GALLERY);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {
            private Uri mUri;

            // 调用照相机
            @Override
            public void onClick(View v) {
                String path = fragment.getActivity().getFilesDir() + File.separator + "images" + File.separator;
                File file = new File(path + username + timeStamp + ".jpg");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //步骤二：Android 7.0及以上获取文件 Uri
                    mUri = FileProvider.getUriForFile(fragment.getActivity(), "com.domineer.triplebro.bookkeeping", file);
                } else {
                    //步骤三：获取文件Uri
                    mUri = Uri.fromFile(file);
                }
                //步骤四：调取系统拍照
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                fragment.startActivityForResult(intent, ProjectProperties.FROM_CAMERA);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
