package polak.shay.test.attenti.View.Adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import polak.shay.test.attenti.Model.Matrix
import polak.shay.test.attenti.R

class MatrixDisplayAdapter(
    val mContext : Context,
    var mData : Matrix) : RecyclerView.Adapter<MatrixDisplayAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent : ViewGroup, type : Int) = ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.brick, parent, false))

    override fun getItemCount() = mData.getCol() * mData.getRow()

    override fun onBindViewHolder(holder : ViewHolder, position : Int) {
        var row = if(position >= mData.getCol()) position / mData.getCol() else 0
        var col = if(position >= mData.getRow()) position - row * mData.getCol() else position

        holder.bind(mData.get(col, row))
    }

    fun updateDate(matrix: Matrix) {
        mData = matrix
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var dataColor : View

        init {
          dataColor = itemView.findViewById(R.id.data_color)
        }

        fun bind(data : Int?)
        {
            if(data == 0) {
                dataColor.setBackgroundColor(Color.WHITE)
            }
            else if(data == 1) {
                dataColor.setBackgroundColor(Color.BLACK)
            }
            else {
                dataColor.setBackgroundColor(Color.rgb(100 / data!!, 255 / data!!, 100 / data!!))
            }
        }
    }
}