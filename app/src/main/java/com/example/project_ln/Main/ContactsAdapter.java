package com.example.project_ln.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project_ln.Model.Book;
import com.example.project_ln.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public  class ContactsAdapter extends
        RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private Context context;



    // Store a member variable for the contacts
    private List<Book> mContacts;

    //teba3 onclicklistener -*********************
    private OnPostListener mOnPostListener ;

    // Pass in the contact array into the constructor
    public ContactsAdapter(List<Book> contactss, OnPostListener onPostListener) {
        this.mContacts = contactss;
        this.mOnPostListener = onPostListener ;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView,mOnPostListener);
        return (viewHolder);
    }


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    // jdid
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView userID;
        public TextView id;
        public TextView title;
        public TextView textView;
        public TextView price;
        // hetha jdid!!! omour back
        public ImageView imagePath ;


        //teba3 onclicklistener -*********************
        OnPostListener onPostListener;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview

        //teba3 onclicklistener -*********************
        public ViewHolder(View itemView, OnPostListener onPostListener) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            userID = (TextView) itemView.findViewById(R.id.contact_userid);
            id = (TextView) itemView.findViewById(R.id.contact_id);
            title = (TextView) itemView.findViewById(R.id.contact_title);
            textView = (TextView) itemView.findViewById(R.id.contact_text);
            imagePath = (ImageView) itemView.findViewById(R.id.image_view);
            price = (TextView) itemView.findViewById(R.id.contact_price);
            //(jdid)

            //teba3 onclick listner
            this.onPostListener = onPostListener;
            //teba3 onclick listner
            itemView.setOnClickListener(this);

            //jdid
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnPostListener != null ){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mOnPostListener.onPostClick(position);
                        }
                    }
                }
            });*/


        }

        @Override
        public void onClick(View v) {
            onPostListener.onPostClick(getAdapterPosition());
        }
    }
    //(onclick *****************)
    //interface for
    public interface OnPostListener {
        void onPostClick(int position);
    }


    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Book contact = mContacts.get(position);


        String imageUrll = contact.getImagePath();
        String linkinternet = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRjV8c0_P3anjIHE-3dDbpUEgkqREYMewwPq90tWtyQ7xfCHsPN&usqp=CAU";
        String changedimageUrl = imageUrll.substring(17);
        System.out.println("http://10.0.2.2:" + changedimageUrl);
        String finalString = "http://10.0.2.2:" + changedimageUrl;
        // Set item views based on your views and data model
        viewHolder.userID.setText(contact.getCreator());
        viewHolder.id.setText(contact.getId());
        viewHolder.title.setText(contact.getTitle());
        viewHolder.textView.setText(contact.getContent());
        viewHolder.price.setText(contact.getPrice());

        /*viewHolder.imagePath.setText(contact.getImagePath());*/
        /*Picasso.get().load(ApiUtil.photoUrl() +adoption.getPhoto()).into(holder.petIv);
        viewHolder.imagePath.setImageResource(contact.getImagePath());*/

        Picasso.get()
                .load(finalString)
                .fit()
                .centerInside()
                .into(viewHolder.imagePath);

        /*System.out.println(imageUrll);*/



    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}
