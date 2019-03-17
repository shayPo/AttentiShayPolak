package polak.shay.test.attenti.View.Adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import polak.shay.test.attenti.Model.Matrix
import polak.shay.test.attenti.R

class ColDisplayAdapter(
    private val mContext: Context,
    var mData: Array<Int>?
) : RecyclerView.Adapter<ColDisplayAdapter.ViewHolder>() {
    var mStopClick = false

    override fun onCreateViewHolder(parent: ViewGroup, type: Int) =
        ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.brick, parent, false))

    override fun getItemCount() = mData!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData!!.get(position), position)
    }

    fun updateDate(data: Array<Int>?) {
        mData = data
        notifyDataSetChanged()
    }

    fun RemoveClickListener() {
        mStopClick = true
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var dataColor: View

        init {
            dataColor = itemView.findViewById(R.id.data_color)
        }

        fun bind(data: Int, position: Int) {
            if (data < 2) {
                if (data == 0) {
                    dataColor.tag = position
                    dataColor.setOnClickListener(if (mStopClick) null else this)
                    dataColor.setBackgroundColor(Color.WHITE)
                } else if (data == 1) {
                    dataColor.setBackgroundColor(Color.BLACK)
                }
            } else {
                dataColor.setBackgroundColor(Color.rgb(100 / data!!, 255 / data!!, 100 / data!!))
            }
        }

        override fun onClick(v: View?) {
            val position = v?.tag as Int
            if (mData!!.get(position) == 0) {
                mData!!.set(position, 1)
                notifyDataSetChanged()
            }
        }
    }
}