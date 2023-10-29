package com.example.womensafetyapplication.guardiandetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.womensafetyapplication.R
import com.example.womensafetyapplication.database.Guardian
import kotlinx.android.synthetic.main.list_view.view.*


class GuardianAdapter(val guardians: List<Guardian>,val context: Context)
    : RecyclerView.Adapter<GuardianAdapter.GuardianViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : GuardianViewHolder {
        val v: View = LayoutInflater.from(context)
            .inflate(R.layout.list_view,parent,false)
        return GuardianViewHolder(v)
    }

    override fun onBindViewHolder(holder: GuardianViewHolder, position: Int) {
        holder.name.text = guardians[position].guardianName
        holder.relation.text = guardians[position].guardianRelation
        holder.phone.text = guardians[position].guardianPhoneNo
        holder.email.text = guardians[position].guardianEmail

    }

    override fun getItemCount(): Int {
        return guardians.size
    }

    class GuardianViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val name = itemView.textName
        val relation = itemView.textRelation
        val phone = itemView.textPhone
        val email = itemView.textEmail
    }

}