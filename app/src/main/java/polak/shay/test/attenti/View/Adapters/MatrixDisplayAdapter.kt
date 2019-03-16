package polak.shay.test.attenti.View.Adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import polak.shay.test.attenti.Model.Matrix
import polak.shay.test.attenti.R

class MatrixDisplayAdapter (

    private val mContext : Context,
    var mData : Matrix) : RecyclerView.Adapter<MatrixDisplayAdapter.ViewHolder>() {
    var mStopClick = false

    override fun onCreateViewHolder(parent : ViewGroup, type : Int) = ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.brick, parent, false))

    override fun getItemCount() = mData.getCol() * mData.getRow()

    override fun onBindViewHolder(holder : ViewHolder, position : Int) {
        var row = if(position >= mData.getCol()) position / mData.getCol() else 0
        var col = if(position >= mData.getRow()) position - row * mData.getCol() else position

        holder.bind(mData.get(col, row), position)
    }

    fun updateDate(matrix: Matrix) {
        mData = matrix
        notifyDataSetChanged()
    }

    fun RemoveClickListener() {
mStopClick = true
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , OnClickListener {

        var dataColor : View

        init {
          dataColor = itemView.findViewById(R.id.data_color)
        }

        fun bind(data : Int, position: Int)
        {
            if(data < 2)
            {
                if(data == 0) {
                    dataColor.tag = position
                    dataColor.setOnClickListener(if(mStopClick) null else this)
                    dataColor.setBackgroundColor(Color.WHITE)
                }
                else if(data == 1) {
                    dataColor.setBackgroundColor(Color.BLACK)
                }
            }
            else
            {
                dataColor.setBackgroundColor(Color.rgb(100 / data!!, 255 / data!!, 100 / data!!))
            }
        }

        override fun onClick(v: View?) {
            val position = v?.tag as Int
            var row = if(position >= mData.getCol()) position / mData.getCol() else 0
            var col = if(position >= mData.getRow()) position - row * mData.getCol() else position
            if(mData.get(col, row) == 0) {
                mData.set(col, row, 1)
            }
        }
    }
}