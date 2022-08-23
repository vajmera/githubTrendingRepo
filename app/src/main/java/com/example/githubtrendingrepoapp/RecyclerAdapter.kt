package com.example.githubtrendingrepoapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerAdapter(val modelList:List<Model>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var username:TextView=itemView.findViewById(R.id.textView)
        var repoName:TextView=itemView.findViewById(R.id.textView2)
        var description:TextView=itemView.findViewById(R.id.expand_text)
        var image:ImageView=itemView.findViewById(R.id.image_view)

        var constraintLayout: ConstraintLayout =itemView.findViewById(R.id.constraintLayout)
        var expandableLayout:RelativeLayout=itemView.findViewById(R.id.expandable_view_layout)

        //var img_android: ImageView=itemView.findViewById<View>(R.id.image_view) as ImageView


            //img_android =


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View=LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:Model=modelList[position]
        holder.username.text=model.username
        holder.repoName.text=model.reponame
        holder.description.text=model.detailed
        //var imageView:ImageView=findViewById(R.id.image_view)

       // holder.image.setImageResource(model.image)
      Picasso.get().load(model.imageURL).placeholder(R.drawable.ic_action_name).error(R.drawable.ic_action_name).into(holder.image)
//        Log.d("imageurl is ",data.avatar.toString())

        val isExpandable:Boolean=modelList[position].expandable
        holder.expandableLayout.visibility=if(isExpandable) View.VISIBLE else View.GONE

        holder.constraintLayout.setOnClickListener{
            val model=modelList[position]
            model.expandable=!model.expandable
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }
}