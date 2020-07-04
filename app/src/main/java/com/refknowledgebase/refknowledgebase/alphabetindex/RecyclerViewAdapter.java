package com.refknowledgebase.refknowledgebase.alphabetindex;

/**
 * Created by MyInnos on 01-02-2017.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.directory_tab.Directory_one_Fragment;
import com.refknowledgebase.refknowledgebase.home_tab.Assistance_detail;
import com.refknowledgebase.refknowledgebase.model.Contact_form_entities_Model;
import com.refknowledgebase.refknowledgebase.model.Directory_List_entitiesModel;
import com.refknowledgebase.refknowledgebase.model.Hashtags_entities_Model;
import com.refknowledgebase.refknowledgebase.model.Home_Content_engitiesModel;
import com.refknowledgebase.refknowledgebase.model.Phone_Model;
import com.refknowledgebase.refknowledgebase.model.Service_Category_Model;
import com.refknowledgebase.refknowledgebase.model.Working_Hour_Model;
import com.refknowledgebase.refknowledgebase.myinterface.DirectListClickListner;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements SectionIndexer {

    private List<Directory_List_entitiesModel> mDataArray;
    private List<Service_Category_Model> mServiceArray;
//    private List<Contact_form_entities_Model> mContactArray;
//    private List<Service_Category_Model> mServiceArray;
//    private List<Phone_Model> mPhoneArray;
//    private List<Working_Hour_Model> mWorkingHourArray;
//    private List<Hashtags_entities_Model> mHashtagsArray;

    private DirectListClickListner directListClickListner;
    private String mSections = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private HashMap<Integer, Integer> sectionsTranslator = new HashMap<>();
    private ArrayList<Integer> mSectionPositions;

    public RecyclerViewAdapter(Context context, DirectListClickListner _directListClickListner) {
        mDataArray = new ArrayList<>();
        mServiceArray = new ArrayList<>();
//        mContactArray = new ArrayList<>();
//        mServiceArray = new ArrayList<>();
//        mPhoneArray = new ArrayList<>();
//        mWorkingHourArray = new ArrayList<>();
//        mHashtagsArray = new ArrayList<>();

        directListClickListner = _directListClickListner;
    }

    @Override
    public int getItemCount() {
        return mDataArray.size();
    }

    @NotNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view_layout, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(mDataArray.get(position).getacronym() != null){
            holder.mTextView.setText(""+mDataArray.get(position).getname() + " ("+mDataArray.get(position).getacronym()+")");
        }else {
            holder.mTextView.setText(""+mDataArray.get(position).getname());
        }

        holder.tv_servicename.setText(""+mServiceArray.get(position).getName());
        String Country_list = "";
        for (int country_i = 0; country_i < mDataArray.get(position).getcountries().length; country_i ++){
            Country_list += " " + mDataArray.get(position).getcountries()[country_i];
        }
        holder.tv_country.setText("Country: " + Country_list);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            directListClickListner.recyclerViewListClicked(v, position);
//                mBuffer.Directory_Phone_number = "+" + mPhoneArray.get(position).getCountry_code() + " " + mPhoneArray.get(position).getArea_code() + " " + mPhoneArray.get(position).getPhone();
//                mBuffer.Directory_eMail = mContactArray.get(position).getEmail();
//                mBuffer.Directory_site = mContactArray.get(position).getWebsite();
//                mBuffer.Directory_Landmark = mContactArray.get(position).getLandmark();
//                mBuffer.Directory_Metro = mContactArray.get(position).getMetro();
//                mBuffer.Directory_PlusCode = mContactArray.get(position).getPluscode();
//                mBuffer.Directory_Country = mContactArray.get(position).getCountry();
//                mBuffer.Directory_BuildingName = mContactArray.get(position).getBuilding_name();
//                mBuffer.Directory_PlusCode = mContactArray.get(position).getPluscode();

//                mBuffer.Directory_Sunday_From = mWorkingHourArray.get(position).getFrom();
//                mBuffer.Directory_Sunday_To = mWorkingHourArray.get(position).getPluscode();
//                mBuffer.Directory_Monday_From = mWorkingHourArray.get(position).getPluscode();
//                mBuffer.Directory_Monday_To = mWorkingHourArray.get(position).getPluscode();
//                mBuffer.Directory_THURSDAY_From = mWorkingHourArray.get(position).getPluscode();
//                mBuffer.Directory_THURSDAY_To = mWorkingHourArray.get(position).getPluscode();
//                mBuffer.Directory_WEDNESDAY_From = mWorkingHourArray.get(position).getPluscode();
//                mBuffer.Directory_WEDNESDAY_To = mWorkingHourArray.get(position).getPluscode();
//                mBuffer.Directory_THURSDAY_From = mWorkingHourArray.get(position).getPluscode();
//                mBuffer.Directory_THURSDAY_To = mWorkingHourArray.get(position).getPluscode();
//                mBuffer.Directory_FRIDAY_From = mWorkingHourArray.get(position).getPluscode();
//                mBuffer.Directory_FRIDAY_To = mWorkingHourArray.get(position).getPluscode();
//                mBuffer.Directory_SATURDAY_From = mWorkingHourArray.get(position).getPluscode();
//                mBuffer.Directory_SATURDAY_To = mWorkingHourArray.get(position).getPluscode();

//                mBuffer.workingHourModelList = mWorkingHourArray;
//                for (int i = 0; i < mHashtagsArray.size(); i++){
//                    mBuffer.Directory_Hashtags = "#"+mHashtagsArray.get(i).getHashtag() + "\t";
//                }

//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                Fragment myFragment = new Directory_one_Fragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.contact_container, myFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    @Override
    public Object[] getSections() {

        List<String> sections = new ArrayList<>(27);
        ArrayList<String> alphabetFull = new ArrayList<>();

        mSectionPositions = new ArrayList<>();
//        Log.e("mDataArray.size", String.valueOf(mDataArray.size()));
        for (int i = 0, size = mDataArray.size(); i < size; i++) {
            String section = String.valueOf(mDataArray.get(i).getname().charAt(0)).toUpperCase();
//            Log.e("section", section);
            if (!sections.contains(section)) {
                sections.add(section);
                mSectionPositions.add(i);
            }
        }
        for (int i = 0; i < mSections.length(); i++) {
            alphabetFull.add(String.valueOf(mSections.charAt(i)));
        }

        sectionsTranslator = Helpers.Companion.sectionsHelper(sections, alphabetFull);

//        Log.e("sections, alphabetFull", ""+sections+"<>"+ alphabetFull);

        return alphabetFull.toArray(new String[0]);
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
//        Log.e("getPositionForSection", ""+mSectionPositions.get(sectionsTranslator.get(sectionIndex)));
        return mSectionPositions.get(sectionsTranslator.get(sectionIndex));
    }

    public void add(Directory_List_entitiesModel r){
        mDataArray.add(r);
        notifyItemInserted(mDataArray.size() - 1);
    }

    public void addAll(List<Directory_List_entitiesModel> moveResults) {
        for (Directory_List_entitiesModel result : moveResults) {
            add(result);
        }
    }

    public Directory_List_entitiesModel getItem(int position) {
        return mDataArray.get(position);
    }

    public void clear() {
        while (getItemCount() > 0){
            remove(getItem(0));
        }
    }

    public void remove(Directory_List_entitiesModel r) {
        int position = mDataArray.indexOf(r);
        if (position > -1) {
            mDataArray.remove(position);
            notifyItemRemoved(position);
        }
    }

//    public void addContactAll(List<Contact_form_entities_Model> contactFormEntitiesModelList) {
//        for (Contact_form_entities_Model result: contactFormEntitiesModelList){
//            addContact(result);
//        }
//    }

//    private void addContact(Contact_form_entities_Model result) {
//        mContactArray.add(result);
//    }
//
    public void addServiceAll(List<Service_Category_Model> serviceCategoryModelList) {
        for (Service_Category_Model result: serviceCategoryModelList){
            addService(result);
        }
    }

    private void addService(Service_Category_Model serviceCategoryModelList) {
        mServiceArray.add(serviceCategoryModelList);
    }
//
//    public void addPhoneAll(List<Phone_Model> phoneModelsList) {
//        for (Phone_Model result: phoneModelsList){
//            addPhone(result);
//        }
//    }
//
//    private void addPhone(Phone_Model result) {
//        mPhoneArray.add(result);
//    }
//
//    public void addWorkingHourAll(List<Working_Hour_Model> workingHourModelList) {
//        for (Working_Hour_Model result: workingHourModelList){
//            addWorkingHour(result);
//        }
//    }
//
//    private void addWorkingHour(Working_Hour_Model result) {
//        mWorkingHourArray.add(result);
//    }

//    public void addHashtagAll(List<Hashtags_entities_Model> hashtagsEntitiesModelList) {
//        for (Hashtags_entities_Model result: hashtagsEntitiesModelList){
//            addHashtagsHour(result);
//        }
//    }
//
//    private void addHashtagsHour(Hashtags_entities_Model result) {
//        mHashtagsArray.add(result);
//    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        RelativeLayout rl_item_contact;
        TextView tv_servicename, tv_country;

        ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_alphabet);
            rl_item_contact = itemView.findViewById(R.id.rl_item_contact);
            tv_servicename = itemView.findViewById(R.id.tv_servicename);
            tv_country = itemView.findViewById(R.id.tv_country);

        }
    }
}