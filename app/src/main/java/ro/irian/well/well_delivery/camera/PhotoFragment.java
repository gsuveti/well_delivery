package ro.irian.well.well_delivery.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ro.irian.well.well_delivery.R;

/**
 * A fragment representing a list of Items.
 */
public class PhotoFragment extends Fragment {

    private static final int CAMERA_INTENT_REQUEST_CODE = 10;
    private static final int GALLERY_INTENT_REQUEST_CODE = 11;
    private static final String TAG = "PhotoFragment";

    List<ImageData> imageList = new ArrayList<>();
    private PhotoRecyclerViewAdapter mAdapter;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();


    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.submitPhotos)
    Button mSubmitButton;

    Uri cameraFileUri;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PhotoFragment() {
    }

    public static PhotoFragment newInstance() {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_list, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new PhotoRecyclerViewAdapter(imageList);
        Context context = view.getContext();

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @OnClick(R.id.camera)
    public void camera() {
        Intent chooseImageIntent = ImagePicker.getPickImageIntent(getActivity());
        cameraFileUri = chooseImageIntent.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
        startActivityForResult(chooseImageIntent, CAMERA_INTENT_REQUEST_CODE);
    }

    @OnClick(R.id.gallery)
    public void gallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        pickPhoto.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        startActivityForResult(pickPhoto, GALLERY_INTENT_REQUEST_CODE);
    }

    @OnClick(R.id.submitPhotos)
    public void submitPhotos() {
        for (ImageData imageData : imageList) {
            Uri file = imageData.getUri();
            StorageReference photoRef = storageRef.child("images/" + file.getLastPathSegment());
            UploadTask uploadTask = photoRef.putFile(file);
            Task<Uri> urlTask = uploadTask.continueWithTask(task -> {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return photoRef.getDownloadUrl();
            }).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    Log.d("TAG", downloadUri.toString());
                } else {
                    // Handle failures
                    // ...
                }
            });
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap;
        Uri uri;
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CAMERA_INTENT_REQUEST_CODE:
                    uri = cameraFileUri;
                    bitmap = ImagePicker.getBitmapFromUri(getActivity(), uri);
                    imageList.add(0, new ImageData(uri, bitmap));
                    mAdapter.notifyDataSetChanged();
                    break;

                case GALLERY_INTENT_REQUEST_CODE:

                    List<Uri> newUriList = ImagePicker.getImageUriListFromResult(getActivity(), resultCode, data);

                    List<ImageData> newImageList = newUriList.stream().map(
                            newUri -> {
                                Bitmap newBitmap = ImagePicker.getBitmapFromUri(getActivity(), newUri);
                                return new ImageData(newUri, newBitmap);
                            }
                    ).collect(Collectors.toList());

                    newImageList = newImageList
                            .stream()
                            .map(
                                    (ImageData myImage) -> {
                                        Uri myUri = myImage.getUri();
                                        Bitmap myBitmap = myImage.getBitmap();
                                        String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.ImageColumns.ORIENTATION};
                                        Cursor cursor = getActivity().getContentResolver().query(myUri, columns, null, null, null);
                                        if (cursor == null) {
                                            Log.d(TAG, "Query failed");
                                            return null;
                                        }

                                        cursor.moveToFirst();
                                        int columnIndex;

                                        columnIndex = cursor.getColumnIndex(columns[0]);
                                        String path = cursor.getString(columnIndex);

                                        cursor.close();
                                        Uri newUri = Uri.fromParts("file", path, "");

                                        return new ImageData(myUri, newUri, myBitmap);
                                    })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());

                    for (ImageData imageData : newImageList) {
                        boolean found = false;
                        for (ImageData oldImageData : imageList) {
                            if (imageData.getNewUri().equals(oldImageData.getNewUri())) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) imageList.add(0, imageData);
                    }


                    mAdapter.notifyDataSetChanged();

                    break;
            }
        }
    }
}
