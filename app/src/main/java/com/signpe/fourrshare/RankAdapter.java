package com.signpe.fourrshare;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Query.Direction;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.signpe.fourrshare.model.ImageDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
    private Context context;
    private int lastPosition = -1;
    private boolean order;
    private String state;
    private FirebaseFirestore firestore;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    ArrayList<ImageDTO> imageDTOs;
    public ArrayList<String> imageUidList = new ArrayList<>();

    public RankAdapter(Context context,ArrayList<ImageDTO> imageDTOS,boolean order) {

        this.context = context;
        this.imageDTOs=imageDTOS;
        this.order=order;

        if (order){
            state="likeCount";
        }
        else{
            state="timeStamp";
        }
        firestore = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


        firestore.collection("images").whereEqualTo("isUpload",true).orderBy(state,Direction.DESCENDING).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots==null)
                            return;
                        try{
                            for(DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                ImageDTO item = snapshot.toObject(ImageDTO.class);
                                imageDTOS.add(item);
                                imageUidList.add(snapshot.getId());
                            }
                        }
                        catch (Exception e){

                        }
                        notifyDataSetChanged();
                    }
                });
    }

    //뷰 바인딩 부분을 한번만 하도록, ViewHolder 패턴 의무화
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView likeButton;
        TextView textView;
        TextView usr_nickname;
        ImageView usr_icon;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image_view);
            likeButton = (ImageView) view.findViewById(R.id.like_button);
            textView = (TextView) view.findViewById(R.id.text_view);
            usr_nickname = view.findViewById(R.id.usr_nickname);
            usr_icon = view.findViewById(R.id.view_profile_image);
        }
    }

    //새로운 뷰 생성 2
    @NonNull
    @Override
    public RankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //RecyclerView의 getView 부분을 담당 3
    @Override
    public void onBindViewHolder(@NonNull RankAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(imageDTOs.get(position).getImageUri()).into(holder.imageView);
        StorageReference ImageRef = storageRef.child("profile").child(imageDTOs.get(position).getUid()).child(imageDTOs.get(position).getUid()+".png");
        ImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(holder.usr_icon);
            }
        });

        holder.usr_nickname.setText(imageDTOs.get(position).getUserNickname());

        if(imageDTOs.get(position).getLikedPeople().containsKey(FirebaseAuth.getInstance().getCurrentUser().getUid())){
            holder.likeButton.setImageResource(R.drawable.clickheart);
        }
        else {
            holder.likeButton.setImageResource(R.drawable.nonclickheart);
        }
        holder.textView.setText(String.valueOf(imageDTOs.get(position).getLikeCount()) );
        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                Map<String,Boolean> like = new HashMap<>();
                like.put(uid,true);
                DocumentReference tsdoc = firestore.collection("images").document(imageUidList.get(position));
                firestore.runTransaction(new Transaction.Function<Void>() {
                    @Nullable
                    @Override
                    public Void apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                        ImageDTO imageDTO = transaction.get(tsdoc).toObject(ImageDTO.class);
                        if(imageDTO.getLikedPeople().containsKey(uid)){
                            imageDTO.setLikeCount(imageDTO.getLikeCount() - 1);
                            imageDTO.getLikedPeople().remove(uid);

                            firestore.collection("image").whereEqualTo("likedPeople",like).addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    if (value!=null){
                                        holder.likeButton.setImageResource(R.drawable.nonclickheart);
                                        holder.textView.setText(String.valueOf(imageDTO.getLikeCount()));
                                        return ;
                                    }

                                    notifyDataSetChanged();
                                }
                            });
                        }
                        else{
                            imageDTO.setLikeCount(imageDTO.getLikeCount()+1);
                            imageDTO.getLikedPeople().put(uid,true);
                            firestore.collection("images").whereEqualTo("likedPeople",like).addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    if (value!=null){
                                        holder.likeButton.setImageResource(R.drawable.clickheart);
                                        holder.textView.setText(String.valueOf(imageDTO.getLikeCount()));
                                        return ;
                                    }
                                    notifyDataSetChanged();
                                }
                            });
                        }

                        transaction.set(tsdoc,imageDTO);
                        return null;
                    }
                });

            }
        });
        setAnimation(holder.imageView, position);
    }

    //Item 개수 반환
    @Override //1
    public int getItemCount() {
        return imageDTOs.size();
    }

    //View 나올 때 Animation 주기
    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.setAnimation(animation);
            lastPosition = position;
        }
    }

}